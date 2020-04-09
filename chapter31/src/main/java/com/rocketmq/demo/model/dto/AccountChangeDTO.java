package com.rocketmq.demo.model.dto;

import lombok.Data;

/**
 * @author ganguowei@wtoip.com
 * @Date 2020/4/9 0009 16:10
 */
@Data
public class AccountChangeDTO {

    public AccountChangeDTO(String accountNo, Double amount, String tx_no) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.txNo = tx_no;
    }

    private Long id;

    /**
     * 户主姓名
     */
    private String accountName;

    private String txNo;
    /**
     * 银行卡号
     */
    private String accountNo;

    /**
     * 帐户密码
     */
    private Double amount;

    /**
     * 帐户余额
     */
    private Double accountBalance;


}
