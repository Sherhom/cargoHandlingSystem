package com.example.demo.controller;

import com.example.demo.exception.AddException;
import com.example.demo.service.cargoService;
import com.example.demo.util.ResultGenerator;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
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
    @ResponseBody
    @GetMapping(value = "/savaCargo", produces = "application/json; charset=utf-8")
    public String saveCargo(@Param("cargoName") String cargoName){
        JSONObject jo = new JSONObject();
        System.out.println(cargoName);
        try{
            cargoService.saveCargo(cargoName);
            return jo.fromObject(generator.getSuccessResult("添加成功")).toString();
        }catch(AddException e){
            return jo.fromObject(generator.getFailResult("添加异常")).toString();
        }
    }

}
