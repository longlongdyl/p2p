package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.eneity.FinanceAccount;
import com.sh2004.p2p.eneity.LoanInfo;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/18 11:53
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class BidInfoController {
    @Reference(interfaceClass = BidInfoService.class,timeout = 20000,version = "1.0.0")
    private BidInfoService bidInfoService;
    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 15000)
    private LoanInfoService loanInfoService;
    @Reference(interfaceClass = FinanceAccountService.class,timeout = 20000,version = "1.0.0")
    private FinanceAccountService financeAccountService;
    @Reference(interfaceClass = IncomeRecordService.class,timeout = 20000,version = "1.0.0")
    private IncomeRecordService incomeRecordService;
    @Reference(interfaceClass = RechargeRecordService.class,timeout = 20000,version = "1.0.0")
    private RechargeRecordService rechargeRecordService;

    @RequestMapping("/loan/loanInfo")
    public String bidInfo( Model model,Integer id){
        List<BidInfo> bidInfoList =bidInfoService.selectByLoanId(id);
        model.addAttribute("bidInfoList",bidInfoList);
        LoanInfo loanInfo =loanInfoService.selectByLoanId(id);
        model.addAttribute("loanInfo",loanInfo);
        return "loanInfo";
    }

    @RequestMapping("/investLoan")
    public String investLoan(String money,String id,String uid,String incomeMoney,HttpSession session){
        Integer bidId = bidInfoService.investLoan(money,id,uid);
        FinanceAccount financeAccount = financeAccountService.investLoan(money, uid);
        User user = (User) session.getAttribute("user");
        user.setFinanceAccount(financeAccount);
        session.setAttribute("user",user);
        incomeRecordService.investLoan(bidId,money,id,uid,incomeMoney);
        loanInfoService.investLoan(id,money);
        return "redirect:/index";

    }
}
