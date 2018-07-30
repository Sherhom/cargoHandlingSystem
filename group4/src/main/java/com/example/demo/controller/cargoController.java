package com.example.demo.controller;

import com.example.demo.exception.AddException;
import com.example.demo.exception.DeleteException;
import com.example.demo.exception.SelectException;
import com.example.demo.exception.UpdateException;
import com.example.demo.service.cargoService;
import com.example.demo.util.ResultGenerator;
import net.sf.json.JSONObject;
import com.example.demo.domain.cargoBean;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 货物相关的控制类
 *
 * @author 杨添
 *
 */
@CrossOrigin
@RestController
public class cargoController {
    @Autowired
    private cargoService cargoService;

    @Autowired
    private ResultGenerator generator;

    @SuppressWarnings("static-access")
    // 添加货物
    @ResponseBody
    @GetMapping(value = "/savaCargo", produces = "application/json; charset=utf-8")
    public String saveCargo(@Param("cargoName") String cargoName)throws SelectException{
        JSONObject jo = new JSONObject();
        try{
            if(cargoService.selectCargo2(cargoName)==null){
                cargoService.saveCargo(cargoName);
                return jo.fromObject(generator.getSuccessResult("添加成功")).toString();
            } else {
                return jo.fromObject(generator.getFailResult("货物已存在")).toString();
            }
        }catch(AddException e){
            return jo.fromObject(generator.getFailResult("添加异常")).toString();
        }
    }

    // 查询货物
    @ResponseBody
    @GetMapping(value = "/selectCargo", produces = "application/json; charset=utf-8")
    public String selectCargo(@Param("keyword") String keyword){
        JSONObject jo = new JSONObject();
        System.out.println(keyword);
        try{
            List<cargoBean> list = null;
            list = cargoService.selectCargo(keyword);
            return jo.fromObject(generator.getSuccessResult("搜索成功",list)).toString();
        }catch(SelectException e){
            return jo.fromObject(generator.getFailResult("搜索异常")).toString();
        }
    }

    // 删除货物
    @ResponseBody
    @GetMapping(value = "/deleteCargo", produces = "application/json; charset=utf-8")
    public String deleteCargo(@Param("cargoName") String cargoName){
        JSONObject jo = new JSONObject();
        try{
            cargoService.deleteCargo(cargoName);
            return jo.fromObject(generator.getSuccessResult("删除成功")).toString();
        }catch (DeleteException e){
            return jo.fromObject(generator.getFailResult("删除异常")).toString();
        }
    }

    // 修改货物
    @ResponseBody
    @GetMapping(value = "/updateCargo", produces = "application/json; charset=utf-8")
    public String updateCargo(@Param("cargoName") String cargoName,@Param("newName") String newName){
        JSONObject jo = new JSONObject();
        try{
            cargoService.changeCargo(cargoName,newName);
            return jo.fromObject(generator.getSuccessResult("修改成功")).toString();
        }catch (UpdateException e){
            return jo.fromObject(generator.getFailResult("修改异常")).toString();
        }
    }
}
