package com.example.demo.Mapper;

import com.example.demo.Bean.Order;
import com.example.demo.Bean.Sender;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ManagerMapper {

    //查询所有订单
    @Select("select * from orders")
// @Results(id="ordersMap", value={
//         @Result(column="oid", property="oid", jdbcType=JdbcType.INTEGER, id=true),
//    })
    List<Map<String,Object>> findAllOrders();

    //按照订单号查询所有订单
    @Select("select * from orders where oid = #{oid}")
    List<Map<String,Object>> findOrderById(@Param("oid")int oid);

    //添加快递员
    @Insert("INSERT INTO sender(susername,spassword,sphone,srealname) VALUES(#{susername}, #{spassword},#{sphone}, #{srealname})")
    @Options(useGeneratedKeys = true,keyProperty = "sid",keyColumn = "sid")
    int insertSender(Sender sender);

    //判断注册快递员的用户名是否重合
    @Select("select * from sender where susername = #{susername}")
    Sender findSenderBySendername(String susername);

    //删除订单
    @Delete("delete from orders where oid = #{oid}")
    int delOrderByOid(Integer oid);
}
