package com.rocketmq.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rocketmq.demo.model.dataobject.AccountInfoDO;
import com.rocketmq.demo.model.dto.AccountChangeDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ganguowei
 * @since 2020-04-09
 */
public interface IAccountInfoBank1Service extends IService<AccountInfoDO> {


    /**
     * 更新帐号余额-发送消息
     * producer向MQ Server发送消息
     *
     * @param accountChangeDTO
     */
    void sendUpdateAccountBalance(AccountChangeDTO accountChangeDTO);

    /**
     * 更新帐号余额-本地事务
     * producer发送消息完成后接收到MQ Server的回应即开始执行本地事务
     *
     * @param accountChangeEvent
     */
    void doUpdateAccountBalance(AccountChangeDTO accountChangeEvent);



}
