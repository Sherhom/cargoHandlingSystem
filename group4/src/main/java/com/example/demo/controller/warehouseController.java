package com.example.demo.controller;

import com.example.demo.Const.FIXED_SETTING;
import com.example.demo.Const.MESSAGE;
import com.example.demo.Const.URL;
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
    @GetMapping(value = URL.ADD_WARE, produces = FIXED_SETTING.JSON_PRODUCE)
    public String addWarehouse(@Param(FIXED_SETTING.WAREHOUSE_NAME) String whName, @Param(FIXED_SETTING.WAREHOUSE_ADDR) String whAddress) throws SelectException {
        JSONObject jo = new JSONObject();
        try {
            if (warehouseService.selectWarehouse2(whName) == null) {
                warehouseService.addWarehouse(whName, whAddress);
                return jo.fromObject(generator.getSuccessResult(MESSAGE.INSERT_SUC)).toString();
            } else {
                return jo.fromObject(generator.getFailResult(MESSAGE.ALREADY_EXIS)).toString();
            }
        } catch (AddException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.INSERT_ERR)).toString();
        }
    }

    // 关闭仓库
    @ResponseBody
    @GetMapping(value = URL.DEL_WARE, produces = FIXED_SETTING.JSON_PRODUCE)
    public String deleteWarehouse(@Param(FIXED_SETTING.WAREHOUSE_NAME) String whName) {
        JSONObject jo = new JSONObject();
        try {
            warehouseService.closeWarehouse(whName);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.FROZE_SUC)).toString();
        } catch (DeleteException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.FROZE_ERR)).toString();
        }
    }

    // 修改仓库信息
    @ResponseBody
    @GetMapping(value = URL.UPDT_WARE, produces = FIXED_SETTING.JSON_PRODUCE)
    public String changeWarehouse(@Param(FIXED_SETTING.NEW_NAME) String newName, @Param(FIXED_SETTING.WAREHOUSE_NAME) String whName,
                                  @Param(FIXED_SETTING.NEW_ADDR) String newAddress) {
        JSONObject jo = new JSONObject();
        try {
            warehouseService.changeWarehouse(newName, whName, newAddress);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.MODIFY_SUC)).toString();
        } catch (UpdateException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.MODIFY_ERR)).toString();
        }
    }

    // 查询仓库
    @ResponseBody
    @GetMapping(value = URL.QUEY_WARE, produces = FIXED_SETTING.JSON_PRODUCE)
    public String selectWarehouse(@Param(FIXED_SETTING.KEY) String keyword) {
        JSONObject jo = new JSONObject();
        List<warehouseBean> list = null;
        try {
            list = warehouseService.selectWarehouse(keyword);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.QUERY_SUC, list)).toString();
        } catch (SelectException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.QUERY_ERR)).toString();
        }
    }
}
