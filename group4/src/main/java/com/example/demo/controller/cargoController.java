package com.example.demo.controller;

import com.example.demo.Const.FIXED_SETTING;
import com.example.demo.Const.MESSAGE;
import com.example.demo.Const.URL;
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

    @SuppressWarnings(FIXED_SETTING.VIS_PERM)
    // 添加货物
    @ResponseBody
    @GetMapping(value = URL.ADD_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String saveCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName)throws SelectException{
        JSONObject jo = new JSONObject();
        try{
            if(cargoService.selectCargo2(cargoName)==null){
                cargoService.saveCargo(cargoName);
                return jo.fromObject(generator.getSuccessResult(MESSAGE.INSERT_SUC)).toString();
            } else {
                return jo.fromObject(generator.getFailResult(MESSAGE.ALREADY_EXIS)).toString();
            }
        }catch(AddException e){
            return jo.fromObject(generator.getFailResult(MESSAGE.INSERT_ERR)).toString();
        }
    }

    // 查询货物
    @ResponseBody
    @GetMapping(value = URL.QUEY_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String selectCargo(@Param(FIXED_SETTING.KEY) String keyword){
        JSONObject jo = new JSONObject();
        System.out.println(keyword);
        try{
            List<cargoBean> list = null;
            list = cargoService.selectCargo(keyword);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.QUERY_SUC,list)).toString();
        }catch(SelectException e){
            return jo.fromObject(generator.getFailResult(MESSAGE.QUERY_SUC)).toString();
        }
    }

    // 删除货物
    @ResponseBody
    @GetMapping(value = URL.DEL_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String deleteCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName){
        JSONObject jo = new JSONObject();
        try{
            cargoService.deleteCargo(cargoName);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.DEL_SUC)).toString();
        }catch (DeleteException e){
            return jo.fromObject(generator.getFailResult(MESSAGE.DEL_ERR)).toString();
        }
    }

    // 修改货物
    @ResponseBody
    @GetMapping(value = URL.UPDT_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String updateCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName,@Param("newName") String newName){
        JSONObject jo = new JSONObject();
        try{
            cargoService.changeCargo(cargoName,newName);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.MODIFY_SUC)).toString();
        }catch (UpdateException e){
            return jo.fromObject(generator.getFailResult(MESSAGE.MODIFY_ERR)).toString();
        }
    }
}
