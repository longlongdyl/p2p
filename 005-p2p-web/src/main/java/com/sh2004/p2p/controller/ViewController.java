/*
package com.sh2004.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

*/
/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 19:29
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 *//*

@Controller
public class ViewController {

    @RequestMapping({"/toView/{modalView}/{view}","/toView/{view}",
            "/toView/{modalView}/{view}/{view1}", "/toView/{modalView}/{view}/{view1}/{view2}"})
    public String toView(
            @PathVariable(value = "modalView",required = false) String modalView,
            @PathVariable(value = "view",required = false) String view,
            @PathVariable(value = "view1",required = false) String view1,
            @PathVariable(value = "view2",required = false) String view2){

        if (view1 != null && modalView !=null && view !=null && view2!=null){

            return File.separator + modalView + File.separator + view + File.separator + view1+ File.separator + view2;

        }

        if (view1 != null && modalView !=null && view !=null){

            return File.separator + modalView + File.separator + view + File.separator + view1;
        }
        if(modalView != null && view !=null){

            return File.separator +modalView + File.separator + view;
        }else{
            return File.separator +view;
        }
    }
}
*/
