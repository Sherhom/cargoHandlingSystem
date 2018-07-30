package com.example.demo.controller;

import com.example.demo.Const.FIXED_SETTING;
import com.example.demo.Const.MESSAGE;
import com.example.demo.Const.URL;
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
    @GetMapping(value = URL.ADD_SKU, produces = FIXED_SETTING.JSON_PRODUCE)
    public String addSku(@Param(FIXED_SETTING.SKU_COLOR) String skuColor, @Param(FIXED_SETTING.SKU_TYPE) String skuType,
                         @Param(FIXED_SETTING.CARGO_ID) int cargoId, @Param(FIXED_SETTING.WAREHOUSE_ID) int whId, @Param(FIXED_SETTING.AMOUNT) int amount) {
        JSONObject jo = new JSONObject();
        try {
            skuService.addSku(skuColor, skuType, cargoId, whId, amount);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.INSERT_SUC)).toString();
        } catch (AddException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.INSERT_ERR)).toString();
        }
    }

    // 删除一条sku
    @ResponseBody
    @GetMapping(value = URL.DEL_SKU, produces = FIXED_SETTING.JSON_PRODUCE)
    public String deleteSku(@Param(FIXED_SETTING.SKU_ID) int skuId) {
        JSONObject jo = new JSONObject();
        try {
            skuService.deleteSkuById(skuId);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.DEL_SUC)).toString();
        } catch (DeleteException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.DEL_ERR)).toString();
        }
    }

    // 删除商品对应sku
    @ResponseBody
    @GetMapping(value = URL.DEL_SKU_BYCARGO, produces = FIXED_SETTING.JSON_PRODUCE)
    public String deleteSkuByCargo(@Param(FIXED_SETTING.CARGO_ID) int cargoId) {
        JSONObject jo = new JSONObject();
        try {
            skuService.deleteSkuByCargo(cargoId);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.DEL_SUC)).toString();
        } catch (DeleteException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.DEL_ERR)).toString();
        }
    }

    // 修改sku信息
    @ResponseBody
    @GetMapping(value = URL.UPDT_SKU, produces = FIXED_SETTING.JSON_PRODUCE)
    public String changeSku(@Param(FIXED_SETTING.SKU_ID) int skuId, @Param(FIXED_SETTING.SKU_COLOR) String skuColor, @Param(FIXED_SETTING.SKU_TYPE) String skuType,
                            @Param(FIXED_SETTING.CARGO_ID) int cargoId, @Param(FIXED_SETTING.WAREHOUSE_ID) int whId, @Param(FIXED_SETTING.AMOUNT) int amount) {
        JSONObject jo = new JSONObject();
        try {
            skuService.changeSku(skuId, skuColor, skuType, cargoId, whId, amount);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.MODIFY_SUC)).toString();
        } catch (UpdateException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.MODIFY_ERR)).toString();
        }
    }

    // 查询sku
    @ResponseBody
    @GetMapping(value = URL.QUEY_SKU, produces = FIXED_SETTING.JSON_PRODUCE)
    public String selectSku(@Param(FIXED_SETTING.CARGO_ID) int cargoId) {
        JSONObject jo = new JSONObject();
        try {
            List<skuBean> list = null;
            list = skuService.selectSku(cargoId);
            return jo.fromObject(generator.getSuccessResult(MESSAGE.QUERY_SUC, list)).toString();
        } catch (SelectException e) {
            return jo.fromObject(generator.getFailResult(MESSAGE.QUERY_ERR)).toString();
        }
    }
}
