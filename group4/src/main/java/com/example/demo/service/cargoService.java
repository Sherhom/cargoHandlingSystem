package com.example.demo.service;

import com.example.demo.dao.cargoMapper;
import com.example.demo.domain.cargoBean;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 货物service类
 *
 * @author 杨添
 *
 */
@Service
public class cargoService {
    @Resource
    cargoMapper cargoMapper;

    // 添加货物
    public void saveCargo(String cargoName) throws AddException {
        try{
            cargoMapper.saveCargo(cargoName);
        }catch (Exception e){
            e.printStackTrace();
            throw new AddException(e);
        }
    }

    // 删除货物
    public void deleteCargo(String cargoName) throws DeleteException {
        try{
            cargoMapper.deleteCargo(cargoName);
        } catch (Exception e){
            e.printStackTrace();
            throw new DeleteException(e);
        }

    }

    // 模糊查询货物
    public List<cargoBean> selectCargo(String keyword) throws SelectException {
        List<cargoBean> list = null;
        list = cargoMapper.selectCargo(keyword);
        return list;
    }

    // 精确查询货物
    public cargoBean selectCargo2(String keyword) throws SelectException {
        try{
            cargoBean cargoBean = cargoMapper.selectCargo2(keyword);
            return cargoBean;
        } catch (Exception e){
            e.printStackTrace();
            throw new SelectException(e);
        }

    }

    // 修改货物
    public void changeCargo(String cargoName, String newName)throws UpdateException{
        try{
            cargoMapper.changeCargoName(cargoName,newName);
        } catch (Exception e){
            e.printStackTrace();
            throw new UpdateException(e);
        }
    }
}
