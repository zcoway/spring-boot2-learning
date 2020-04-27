package com.rocketmq.demo.service;

import com.rocketmq.demo.model.dto.AccountChangeDTO;

public interface IAccountInfoBank2Service {
    public void addAccountInfoBalance(AccountChangeDTO accountChangeEvent);
}
