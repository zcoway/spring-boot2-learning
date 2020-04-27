package com.rocketmq.demo.controller;

import com.rocketmq.demo.model.dto.AccountChangeDTO;
import com.rocketmq.demo.service.IAccountInfoBank1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class AccountInfoController {
    @Autowired
    private IAccountInfoBank1Service accountInfoService;

    @GetMapping(value = "/transfer")
    public String transfer(@RequestParam("accountNo")String accountNo, @RequestParam("amount") Double amount){
        String tx_no = UUID.randomUUID().toString();
        AccountChangeDTO accountChangeEvent = new AccountChangeDTO(accountNo,amount,tx_no);
        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功";
    }
}
