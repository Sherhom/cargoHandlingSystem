package com.example.demo.controller;

import com.example.demo.domain.warehouseBean;
import com.example.demo.exception.AddException;
import com.example.demo.exception.DeleteException;
import com.example.demo.exception.SelectException;
import com.example.demo.exception.UpdateException;
import com.example.demo.service.warehouseService;
import com.example.demo.util.ResultGenerator;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 仓库相关的控制类
 *
 * @author 杨添
 *
 */
@CrossOrigin
@RestController
public class warehouseController {
    @Autowired
    warehouseService warehouseService;
    @Autowired
    private ResultGenerator generator;

    // 创建仓库
    @ResponseBody
    @GetMapping(value = "/saveWarehouse", produces = "application/json; charset=utf-8")
    public String addWarehouse(@Param("whName")String whName,@Param("whAddress") String whAddress)throws SelectException {
        JSONObject jo = new JSONObject();
        try {
            if(warehouseService.selectWarehouse2(whName)==null){
                warehouseService.addWarehouse(whName,whAddress);
                return jo.fromObject(generator.getSuccessResult("添加成功")).toString();
            } else {
                return jo.fromObject(generator.getFailResult("仓库已存在")).toString();
            }
        }catch(AddException e){
            return jo.fromObject(generator.getFailResult("创建异常")).toString();
        }
    }

    // 关闭仓库
    @ResponseBody
    @GetMapping(value = "/deleteWarehouse", produces = "application/json; charset=utf-8")
    public String deleteWarehouse(@Param("whName")String whName){
        JSONObject jo = new JSONObject();
        try{
            warehouseService.closeWarehouse(whName);
            return jo.fromObject(generator.getSuccessResult("关闭成功")).toString();
        }catch (DeleteException e){
            return jo.fromObject(generator.getFailResult("关闭异常")).toString();
        }
    }

    // 修改仓库信息
    @ResponseBody
    @GetMapping(value = "/changeWarehouse", produces = "application/json; charset=utf-8")
    public String changeWarehouse(@Param("newName")String newName,@Param("whName") String whName,
                                  @Param("newAddress") String newAddress){
        JSONObject jo = new JSONObject();
        try{
            warehouseService.changeWarehouse(newName,whName,newAddress);
            return jo.fromObject(generator.getSuccessResult("修改成功")).toString();
        }catch (UpdateException e){
            return jo.fromObject(generator.getFailResult("修改异常")).toString();
        }
    }

    // 查询仓库
    @ResponseBody
    @GetMapping(value = "/selectWarehouse", produces = "application/json; charset=utf-8")
    public String selectWarehouse(@Param("keyword")String keyword){
        JSONObject jo = new JSONObject();
        List<warehouseBean> list = null;
        try{
            list = warehouseService.selectWarehouse(keyword);
            return jo.fromObject(generator.getSuccessResult("查询成功",list)).toString();
        }catch (SelectException e){
            return jo.fromObject(generator.getFailResult("查询异常")).toString();
        }
    }
}
