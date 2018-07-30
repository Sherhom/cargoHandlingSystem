package com.example.demo.service;

import com.example.demo.dao.warehouseMapper;
import com.example.demo.domain.warehouseBean;
import com.example.demo.exception.AddException;
import com.example.demo.exception.DeleteException;
import com.example.demo.exception.SelectException;
import com.example.demo.exception.UpdateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓库service类
 *
 * @author 杨添
 *
 */
@Service
public class warehouseService {
    @Resource
    warehouseMapper warehouseMapper;

    // 创建仓库
    public void addWarehouse(String whName,String whAddress) throws AddException{
        try{
            warehouseMapper.addWarehouse(whName,whAddress);
        } catch (Exception e){
            e.printStackTrace();
            throw new AddException(e);
        }
    }

    // 关闭仓库
    public void closeWarehouse(String whName) throws DeleteException{
        try{
            warehouseMapper.closeWarehouse(whName);
        }catch (Exception e){
            e.printStackTrace();
            throw new DeleteException(e);
        }
    }

    // 修改仓库
    public void changeWarehouse(String newName,String whName,String newAddress)throws UpdateException{
        try{
            warehouseMapper.changeWarehouse(newName,whName,newAddress);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateException(e);
        }
    }

    // 模糊查询仓库
    public List<warehouseBean> selectWarehouse(String keyword)throws SelectException{
        List<warehouseBean> list = null;
        try {
            list = warehouseMapper.selectWarehouse(keyword);
        }catch (Exception e){
            e.printStackTrace();
            throw new SelectException(e);
        }
        return list;
    }

    // 精确查询仓库
    public warehouseBean selectWarehouse2(String keyword)throws SelectException{
        warehouseBean warehouseBean;
        try {
            warehouseBean = warehouseMapper.selectWarehouse2(keyword);
        }catch (Exception e){
            e.printStackTrace();
            throw new SelectException(e);
        }
        return warehouseBean;
    }

}
