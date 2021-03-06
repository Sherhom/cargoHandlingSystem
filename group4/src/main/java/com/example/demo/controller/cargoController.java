package com.example.demo.controller;

import com.example.demo.Const.FIXED_SETTING;
import com.example.demo.Const.MESSAGE;
import com.example.demo.Const.URL;
import com.example.demo.domain.ResultMsg;
import com.example.demo.exception.*;
import com.example.demo.service.cargoService;
import com.example.demo.service.skuService;
import com.example.demo.util.ResultGenerator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.example.demo.domain.cargoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 货物相关的控制类
 *
 * @author 杨添
 */
@CrossOrigin
@RestController
public class cargoController {
    @Autowired
    private cargoService cargoService;

    @Autowired
    private skuService skuService;

    @Autowired
    private ResultGenerator generator;

    private ResultMsg resultMsg;

    @SuppressWarnings(FIXED_SETTING.VIS_PERM)
    // 添加货物
    @ResponseBody
    @GetMapping(value = URL.ADD_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String saveCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName) throws SelectException {
        JSONObject jo = new JSONObject();
        try {
            // CARGO objects should not share the same key
            if (cargoService.selectCargo2(cargoName) == null) {
                cargoService.saveCargo(cargoName);
                return jo.fromObject(generator.getSuccessResult(MESSAGE.INSERT_SUC)).toString();
            } else {
                return jo.fromObject(generator.getFailResult(MESSAGE.ALREADY_EXIS)).toString();
            }
        } catch (AddException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.INSERT_ERR)).toString();
        }
    }

    // 查询货物
    @ResponseBody
    @GetMapping(value = URL.QUEY_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public ResultMsg selectCargo(@Param(FIXED_SETTING.KEY) String keyword) {
        try {
            List<cargoBean> list = null;
            list = cargoService.selectCargo(keyword);
            int count = cargoService.getCount();
            resultMsg = new ResultMsg(0,"",count,list);
            System.out.println(resultMsg);
            return resultMsg;
        } catch (SelectException e) {
            resultMsg = new ResultMsg(1,"wrong",0,null);
            return resultMsg;
        }
    }

    // 删除货物
    @ResponseBody
    @GetMapping(value = URL.DEL_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String deleteCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName) {
        JSONObject jo = new JSONObject();
        try {
            // delete all the SKU objects that bind with this CARGO object
            System.out.println(cargoName);
            int target = cargoService.getCargoIdByName(cargoName);
            System.out.println(target);
            cargoService.deleteCargo(cargoName);
            skuService.deleteSkuByCargo(target);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.DEL_SUC)).toString();
        } catch (DeleteException e) {
            e.printStackTrace();
            return jo.fromObject(generator.getFailResult(MESSAGE.DEL_ERR)).toString();
        } catch (InnerException e) {
            e.printStackTrace();
            return jo.fromObject(generator.getFailResult(MESSAGE.DEL_ERR)).toString();
        }
    }

    // 修改货物
    @ResponseBody
    @GetMapping(value = URL.UPDT_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String updateCargo(@Param(FIXED_SETTING.CARGO_NAME) String cargoName, @Param("newName") String newName) {
        JSONObject jo = new JSONObject();
        try {
            System.out.println(cargoName);
            System.out.println(newName);
            cargoService.changeCargo(cargoName, newName);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.MODIFY_SUC)).toString();
        } catch (UpdateException e) {
            e.printStackTrace();
            return jo.fromObject(generator.getFailResult(MESSAGE.MODIFY_ERR)).toString();
        }
    }

    /**
     * get all CARGO objects
     *
     * @return all CARGO objects (json)
     */
    @ResponseBody
    @GetMapping(value = URL.SHOW_CARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String listAllCargo() {
        JSONObject jojo = new JSONObject();
        try {
            List<cargoBean> allCargo = null;
            allCargo = cargoService.getAllCargo();
            return jojo.fromObject(generator.getSuccessResult(MESSAGE.QUERY_SUC, allCargo)).toString();
        } catch (SelectException e) {
            return jojo.fromObject(generator.getFailResult(MESSAGE.QUERY_ERR)).toString();
        }
    }


}
