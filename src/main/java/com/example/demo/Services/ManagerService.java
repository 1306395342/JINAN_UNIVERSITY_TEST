package com.example.demo.Services;

import com.example.demo.Bean.Sender;
import com.example.demo.Mapper.ManagerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ManagerService {
    @Resource
    private ManagerMapper managerMapper;
    //查找所有订单服务
    public List<Map<String,Object>> findAllOrdersService(){return managerMapper.findAllOrders();}
    //通过订单号查找订单
    public List<Map<String,Object>> findOrderByIdService(int oid){return managerMapper.findOrderById( oid);}
    //查找快递员通过用户名
    public Sender findSenderBySenderName(String susername){return managerMapper.findSenderBySendername(susername);}
    //注册新的快递员 Transaction 修改删除要事务操作，不能中断
    @Transactional
    public int saveSender(Sender sender){return managerMapper.insertSender(sender);}
    //删除快递员
    @Transactional
    public int deleteOrderByOid(int oid ){return managerMapper.delOrderByOid(oid);}
}
