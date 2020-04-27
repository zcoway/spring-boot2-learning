package com.rocketmq.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rocketmq.demo.model.dataobject.AccountInfoDO;
import com.rocketmq.demo.model.dto.AccountChangeDTO;
import com.rocketmq.demo.repository.AccountInfoMapper;
import com.rocketmq.demo.service.IAccountInfoBank1Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ganguowei
 * @since 2020-04-09
 */
@Service
@Slf4j
public class AccountInfoBank1ServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfoDO> implements IAccountInfoBank1Service {

    @Autowired
    private AccountInfoMapper accountInfoDao;

    public AccountInfoBank1ServiceImpl() {
        super();
    }

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 更新帐号余额-发送消息
     * producer向MQ Server发送消息
     *
     * @param accountChangeDTO
     */
    @Override
    public void sendUpdateAccountBalance(AccountChangeDTO accountChangeDTO) {
        //构建消息体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange",accountChangeDTO);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1", "topic_txmsg", message, null);

        log.info("send transcation message body={},result={}",message.getPayload(),sendResult.getSendStatus());
    }

    /**
     * 更新帐号余额-本地事务
     * producer发送消息完成后接收到MQ Server的回应即开始执行本地事务
     *
     * @param accountChangeEvent
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doUpdateAccountBalance(AccountChangeDTO accountChangeEvent) {
        log.info("开始更新本地事务，事务号：{}",accountChangeEvent.getTxNo());
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount() * -1);
        //为幂等作准备
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 2){
            throw new RuntimeException("bank1更新本地事务时抛出异常");
        }
        log.info("结束更新本地事务，事务号：{}",accountChangeEvent.getTxNo());
    }


}
