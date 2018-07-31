package com.example.demo.service;

import com.example.demo.dao.skuMapper;
import com.example.demo.domain.skuBean;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * sku service类
 *
 * @author 杨添
 */
@Service
public class skuService {
    @Resource
    skuMapper skuMapper;

    // 添加sku
    public void addSku(String skuColor, String skuType, int cargoId, int whId, int count) throws AddException {
        try {
            skuMapper.addSku(skuColor, skuType, cargoId, whId, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AddException(e);
        }
    }

    // 删除一条sku
    public void deleteSkuById(int skuId) throws DeleteException {
        try {
            skuMapper.deleteSkuById(skuId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e);
        }
    }

    // 根据商品删除对应sku
    public void deleteSkuByCargo(int cargoId) throws DeleteException {
        try {
            skuMapper.deleteSkuByCargo(cargoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e);
        }
    }

    // 修改sku信息
    public void changeSku(int skuId, String skuColor, String skuType, int cargoId, int whId, int count) throws UpdateException {
        try {
            skuMapper.changeSku(skuId, skuColor, skuType, cargoId, whId, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e);
        }
    }

    // 按货物查询sku
    public List<skuBean> selectSku(int cargoId) throws SelectException {
        List<skuBean> list = null;
        try {
            list = skuMapper.selectSkuByCargo(cargoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SelectException(e);
        }
        return list;
    }

    /**
     * delete all SKU objects which linked with the target WAREHOUSE object
     *
     * @param wareId
     * @throws InnerException
     */
    public void delSkuByWareId(int wareId) throws InnerException {
        int delNum = 0;
        try {
            delNum = skuMapper.delSkuByWareId(wareId);
        } catch (Exception e) {
            throw new InnerException(e);
        }
    }

    /**
     * get all SKU objects out of database
     *
     * @return all SKU objects
     * @throws SelectException
     */
    public List<skuBean> getAllSku() throws SelectException {
        List<skuBean> skuAll = null;
        try {
            skuAll = skuMapper.getAllSku();
        } catch (Exception e) {
            throw new SelectException(e);
        }
        return skuAll;
    }
}