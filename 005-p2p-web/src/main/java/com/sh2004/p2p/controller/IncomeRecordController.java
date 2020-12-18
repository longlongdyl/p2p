package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.eneity.IncomeRecord;
import com.sh2004.p2p.eneity.RechargeRecord;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 19:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class IncomeRecordController {
    @Reference(interfaceClass = IncomeRecordService.class,timeout = 20000,version = "1.0.0")
    private IncomeRecordService incomeRecordService;
    @Reference(interfaceClass = BidInfoService.class,timeout = 20000,version = "1.0.0")
    private BidInfoService bidInfoService;
    @Reference(interfaceClass = RechargeRecordService.class,timeout = 20000,version = "1.0.0")
    private RechargeRecordService rechargeRecordService;
    @Reference(interfaceClass = FinanceAccountService.class,timeout = 20000,version = "1.0.0")
    private FinanceAccountService financeAccountService;


    @RequestMapping("/myCenter")
    public String myCenter(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<IncomeRecord> incomeRecord = incomeRecordService.selectByUserId(user.getId());
        model.addAttribute("incomeRecord",incomeRecord);
        List<Map<String,String>> bidInfoList = bidInfoService.selectByUserId(user.getId());
        model.addAttribute("bidInfoList",bidInfoList);
        List<RechargeRecord> rechargeRecordList = rechargeRecordService.selectByUserId(user.getId());
        model.addAttribute("rechargeRecordList",rechargeRecordList);
        Double userMoney = financeAccountService.selectByUserId(user.getId());
        model.addAttribute("userMoney",userMoney);
        return "myCenter";
    }
}
