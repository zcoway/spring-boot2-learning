## 防止form表单重复提交

### 解决方案 
对请求增加唯一id,然后对处理过的请求缓存一段时间，后端处理请求是判断id 如果已处在则表示已处理过。
具体实现请查看当前项目

**1、** 前端form表单加载的时候，由前端生成formId，值要求具有唯一性，如uuid。

**2、** 前端在form表单提交的时候在请求头中携带一个formId的参数。

**3、** 后端基于AOP拦截请求，判断请求头是否携带formId，并判断formId 是否在缓存中，如果formId存在（表示已处理过），则忽略当前请求返回异常信息给前端；
如果formId不存在（表示未处理），则将formId加入缓存中并设置缓存过期时间，执行方法处理。
        

### 解决方案 2

**1、** 前端form表单加载的时候，去请求后端的生成formId的接口

**2、** 后端在生成formId接口将formId存储到缓存中，再返回给前端

**3、** 前端在form表单提交的时候在请求头中携带一个formId的参数，值为加载时获取到的formId

**4、** 后端基于AOP拦截请求，判断请求头是否携带formId，并通过删除操作，如果返回true表示该
formId存在，如果返回false 表示该formId不合法。（这里不使用select + delete 两步操作可能会发生并发）返回true
则执行方法 否则返回异常信息给前端。

### 解决方案 2 后端实现 

**1、** 自定义一个注解`Form` 

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {
}
```

**2、** 在所有的form表单提交的方法上加上`Form注解`

```java
    @RequestMapping(value = "commit")
    @Form
    public String commit() {
        return "success";
    }
```

**3、** 定义一个获取formId的接口
```java
    @RequestMapping(value = "getFormId")
    public String getKey() {
        String key = UUID.randomUUID().toString();
        //将key存储到缓存中，有效期10分钟
        redisTools.save(key,key,10);
        return key;
    }
```
**3、** 定义一个切面，用来拦截所有被`Form`注解修饰的方法

这里我做的比较简单，因为是demo 这里我没有用全局异常来做，具体根据自身业务来。
```java
    @Before("@annotation(cn.isuyu.form.repeat.commit.annotations.Form)")
    public void formBefore(){
        String formId = request.getHeader("formId");

        if (null == formId) {
            throw new RuntimeException("请在头信息中携带formId");
        //删除缓存中的formId 这里直接使用delete是避免并发情况  使用seect + delete 存在并发问题
        } else if (!redisTools.delete(formId)){
            throw new RuntimeException("非法formId,请获取后重试");
        }
    }
```