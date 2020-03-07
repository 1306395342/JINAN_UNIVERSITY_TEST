package com.example.demo.RestContoller;

import com.example.demo.Bean.Sender;
import com.example.demo.Services.ManagerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest")
@RestController
public class RestManagerController {
    @Resource
    private ManagerService managerService; //管理员api


    //查询订单请求
    @RequestMapping("/showorders")
    public Map<String,Object> showOrders (int code,int oid){
        Map<String,Object> responses = new HashMap();
        //若存在订单号，则查询指定订单号的订单
        if(code==0||oid==-1){
            List<Map<String,Object>> orders= managerService.findAllOrdersService();
            responses.put("data",orders);
            responses.put("count",10);
            responses.put("code",0);
            return responses;
        }
        //若没有要查询的订单号，则整体查询
         if(code==1) {
             List<Map<String,Object>> order = managerService.findOrderByIdService(oid);
            responses.put("data",order);
            responses.put("count",10);
            responses.put("code",0);
            return responses;
        }
        return responses;
    }

   //删除订单，只是为了符合笔试的增删查 实际上所有订单不准删除
     @RequestMapping("/deleteorder")
    public Map<String,Object> deleteOrder (int oid) {
        Map<String,Object> responses = new HashMap<>();
         int result = managerService.deleteOrderByOid(oid);
         if(result!=1){
             responses.put("code",1);
             return responses;
         }
         responses.put("code",0);
        return responses;
    }

    //注册快递员
    @RequestMapping("/addsender")
    public Map<String,Object>  registerSender(String username,String password,String phone,String realname){
        Map<String,Object> responses = new HashMap<>();
        //判断用户名是否重复
        Sender sameSender = managerService.findSenderBySenderName(username);
        //信息没填写完整
        if(username.equals("")||password.equals("")||phone.equals("")||realname.equals("")){
            responses.put("code",-1);
            return responses;
        }
        //用户名重复
        else if(sameSender!=null){
            responses.put("code",-2);
            return responses;
        }
        Sender newSender = new Sender();
        newSender.setCurrentoid(-1);//即刚注册的id，默认当前发送的订单号为-1，即系统暂不配送订单
        newSender.setSphone(phone);
        newSender.setSrealname(realname);
        newSender.setSusername(username);
        newSender.setSpassword(password);
        newSender.setSstatus(0);
        int result=managerService.saveSender(newSender);
        System.out.print("lyyyyyyyyyyyyyyyyyyyyy "+result);
        //注册成功
        responses.put("code",1);
        return responses;
    }


}
