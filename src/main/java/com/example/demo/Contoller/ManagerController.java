package com.example.demo.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    //默认页面，添加快递员
    @RequestMapping("/index")
    public String Index (){
        return "Index";
    }
    //查看所有快递员的订单
    @RequestMapping("/showallorders")
    public String showAllOrders (){
        return "showorders";
    }
}
