package com.rocketmq.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rocketmq.demo.model.dataobject.AccountInfoDO;
import com.rocketmq.demo.model.dto.AccountChangeDTO;
import com.rocketmq.demo.repository.AccountInfoBank2Mapper;
import com.rocketmq.demo.repository.AccountInfoMapper;
import com.rocketmq.demo.service.IAccountInfoBank1Service;
import com.rocketmq.demo.service.IAccountInfoBank2Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountInfoBank2ServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfoDO> implements IAccountInfoBank2Service {

    @Autowired
    private AccountInfoBank2Mapper accountInfoDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 消费消息，更新本地事务，添加金额
     * @param accountChangeEvent
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAccountInfoBalance(AccountChangeDTO accountChangeEvent) {
        log.info("bank2更新本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //幂等校验
        int existTx = accountInfoDao.isExistTx(accountChangeEvent.getTxNo());
        if(existTx<=0){
            //执行更新
            accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
            //添加事务记录
            accountInfoDao.addTx(accountChangeEvent.getTxNo());
            log.info("更新本地事务执行成功，本次事务号: {}", accountChangeEvent.getTxNo());
        }else{
            log.info("更新本地事务执行失败，本次事务号: {}", accountChangeEvent.getTxNo());
        }

    }
}
