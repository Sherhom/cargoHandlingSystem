package com.example.demo.service;

import com.example.demo.dao.warehouseMapper;
import com.example.demo.domain.warehouseBean;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓库service类
 *
 * @author 杨添
 */
@Service
public class warehouseService {
    @Resource
    warehouseMapper warehouseMapper;

    // 创建仓库
    public void addWarehouse(String whName, String whAddress) throws AddException {
        try {
            warehouseMapper.addWarehouse(whName, whAddress);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AddException(e);
        }
    }

    // 关闭仓库
    public void closeWarehouse(String whName) throws DeleteException {
        try {
            warehouseMapper.closeWarehouse(whName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e);
        }
    }

    // 修改仓库
    public void changeWarehouse(String newName, String whName, String newAddress) throws UpdateException {
        try {
            warehouseMapper.changeWarehouse(newName, whName, newAddress);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e);
        }
    }

    // 模糊查询仓库
    public List<warehouseBean> selectWarehouse(String keyword) throws SelectException {
        List<warehouseBean> list = null;
        try {
            list = warehouseMapper.selectWarehouse(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SelectException(e);
        }
        return list;
    }

    // 精确查询仓库
    public warehouseBean selectWarehouse2(String keyword) throws SelectException {
        warehouseBean warehouseBean;
        try {
            warehouseBean = warehouseMapper.selectWarehouse2(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SelectException(e);
        }
        return warehouseBean;
    }

    /**
     * judge weather the WAREHOUSE object is available or not
     *
     * @param wareId
     * @return the current state of the selected WAREHOUSE object
     * @throws InnerException
     */
    public boolean isWareExist(int wareId) throws InnerException {
        try {
            int exis = warehouseMapper.warehouseEixs(wareId);
            if (exis != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new InnerException(e);
        }
    }

    /**
     * try to get the id of the target WAREHOUSE object
     *
     * @param wareName
     * @return WAREHOUSE object's id
     * @throws InnerException
     */
    public int getWareIdByName(String wareName) throws InnerException {
        int wareId = 0;
        try {
            wareId = warehouseMapper.getWareIdByName(wareName);
        } catch (Exception e) {
            throw new InnerException(e);
        }
        return wareId;
    }

    /**
     * get all WAREHOUSE objects out of database
     *
     * @return get all WAREHOUSE objects
     * @throws SelectException
     */
    public List<warehouseBean> getAllWare() throws SelectException {
        List<warehouseBean> wareAll = null;
        try {
            wareAll = warehouseMapper.getAllWarehouse();
        } catch (Exception e) {
            throw new SelectException(e);
        }
        return wareAll;
    }
}
