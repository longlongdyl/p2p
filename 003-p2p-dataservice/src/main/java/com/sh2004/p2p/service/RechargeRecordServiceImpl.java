package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/16 13:52
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service(interfaceClass = RechargeRecordService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class RechargeRecordServiceImpl implements RechargeRecordService {
}
