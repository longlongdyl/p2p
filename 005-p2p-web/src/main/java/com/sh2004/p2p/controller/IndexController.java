package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.eneity.LoanInfo;
import com.sh2004.p2p.service.BidInfoService;
import com.sh2004.p2p.service.LoanInfoService;
import com.sh2004.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 13:56
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class IndexController {

    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 15000)
    private LoanInfoService loanInfoService;
    @Reference(interfaceClass = UserService.class, version = "1.0.0", timeout = 15000)
    private UserService userService;
    @Reference(interfaceClass = BidInfoService.class, version = "1.0.0", timeout = 15000)
    private BidInfoService bidInfoService;

    @RequestMapping("/")
    public String index(Model model){
        Double avgRate = loanInfoService.queryAvgRate();
        model.addAttribute("avgRate",avgRate);
        Long userCount = userService.queryUserCount();
        model.addAttribute("userCount",userCount);
        Double totalMoney = bidInfoService.queryTotalMoney();
        model.addAttribute("totalMoney",totalMoney);

        List<LoanInfo> loanInfoList = loanInfoService.queryAllLoanInfo();
        model.addAttribute("loanInfoList",loanInfoList);
        return "index";
    }
}
