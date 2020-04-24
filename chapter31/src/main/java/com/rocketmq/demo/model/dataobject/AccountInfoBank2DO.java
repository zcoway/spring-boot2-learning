package com.rocketmq.demo.model.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ganguowei
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account_info2")
public class AccountInfoBank2DO extends Model<AccountInfoBank2DO> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 户主姓名
     */
    private String accountName;

    /**
     * 银行卡号
     */
    private String accountNo;

    /**
     * 帐户密码
     */
    private String accountPassword;

    /**
     * 帐户余额
     */
    private Double accountBalance;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
