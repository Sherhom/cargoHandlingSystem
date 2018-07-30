package com.example.demo.controller;

import com.example.demo.domain.skuBean;
import com.example.demo.exception.AddException;
import com.example.demo.exception.DeleteException;
import com.example.demo.exception.SelectException;
import com.example.demo.exception.UpdateException;
import com.example.demo.service.skuService;
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
 * sku相关的控制类
 *
 * @author 杨添
 *
 */
@CrossOrigin
@RestController
public class skuController {
    @Autowired
    skuService skuService;
    @Autowired
    private ResultGenerator generator;

    // 添加sku
    @ResponseBody
    @GetMapping(value = "/saveSku", produces = "application/json; charset=utf-8")
    public String addSku(@Param("skuColor")String skuColor, @Param("skuType")String skuType,
                       @Param("cargoId") int cargoId, @Param("whId") int whId,@Param("amount")int amount){
        JSONObject jo = new JSONObject();
        try{
            skuService.addSku(skuColor,skuType,cargoId,whId,amount);
            return jo.fromObject(generator.getSuccessResult("添加成功")).toString();
        }catch (AddException e){
            return jo.fromObject(generator.getFailResult("创建异常")).toString();
        }
    }

    // 删除一条sku
    @ResponseBody
    @GetMapping(value = "/deleteSku", produces = "application/json; charset=utf-8")
    public String deleteSku(@Param("skuId")int skuId){
        JSONObject jo = new JSONObject();
        try{
            skuService.deleteSkuById(skuId);
            return jo.fromObject(generator.getSuccessResult("删除成功")).toString();
        }catch (DeleteException e){
            return jo.fromObject(generator.getFailResult("删除异常")).toString();
        }
    }

    // 删除商品对应sku
    @ResponseBody
    @GetMapping(value = "/deleteSkuByCargo", produces = "application/json; charset=utf-8")
    public String deleteSkuByCargo(@Param("cargoId")int cargoId){
        JSONObject jo = new JSONObject();
        try{
            skuService.deleteSkuByCargo(cargoId);
            return jo.fromObject(generator.getSuccessResult("删除成功")).toString();
        }catch (DeleteException e){
            return jo.fromObject(generator.getFailResult("删除异常")).toString();
        }
    }

    // 修改sku信息
    @ResponseBody
    @GetMapping(value = "/changeSku", produces = "application/json; charset=utf-8")
    public String changeSku(@Param("skuId")int skuId, @Param("skuColor") String skuColor, @Param("skuType") String skuType,
                            @Param("cargoId") int cargoId,@Param("whId")int whId,@Param("amount")int amount){
        JSONObject jo = new JSONObject();
        try{
            skuService.changeSku(skuId,skuColor,skuType,cargoId,whId,amount);
            return jo.fromObject(generator.getSuccessResult("修改成功")).toString();
        }catch (UpdateException e){
            return jo.fromObject(generator.getFailResult("修改异常")).toString();
        }
    }

    // 查询sku
    @ResponseBody
    @GetMapping(value = "/SelectSku", produces = "application/json; charset=utf-8")
    public String selectSku(@Param("cargoId")int cargoId){
        JSONObject jo = new JSONObject();
        try{
            List<skuBean> list = null;
            list = skuService.selectSku(cargoId);
            return jo.fromObject(generator.getSuccessResult("查询成功",list)).toString();
        }catch (SelectException e){
            return jo.fromObject(generator.getFailResult("查询异常")).toString();
        }
    }
}
