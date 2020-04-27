package me.chanjar.simple.callback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static me.chanjar.simple.SpringCallbackLogger.LOGGER;

/**
 * Created by qianjia on 2016/10/2.
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    /**
     * 构造方法必须接收这两个参数
     *
     * @param springApplication
     * @param args
     */
    public MySpringApplicationRunListener(SpringApplication springApplication, String[] args) {
        LOGGER.info("*** {} constructor", getClass().getSimpleName());
    }

    @Override
    public void starting() {
        LOGGER.info("*** {} starting", getClass().getSimpleName());
    }

    public void started(ConfigurableApplicationContext context) {
        LOGGER.info("*** {} started", getClass().getSimpleName());
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        LOGGER.info("*** {} environmentPrepared", getClass().getSimpleName());
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        LOGGER.info("*** {} contextPrepared", getClass().getSimpleName());
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        LOGGER.info("*** {} contextLoaded", getClass().getSimpleName());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        LOGGER.info("*** {} running", getClass().getSimpleName());
    }
}
