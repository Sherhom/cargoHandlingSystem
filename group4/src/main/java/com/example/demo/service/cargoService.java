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
 */
@Service
public class cargoService {
    @Resource
    cargoMapper cargoMapper;

    // 添加货物
    public void saveCargo(String cargoName) throws AddException {
        try {
            cargoMapper.saveCargo(cargoName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AddException(e);
        }
    }

    // 删除货物
    public void deleteCargo(String cargoName) throws DeleteException {
        try {
            cargoMapper.deleteCargo(cargoName);
        } catch (Exception e) {
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
        try {
            cargoBean cargoBean = cargoMapper.selectCargo2(keyword);
            return cargoBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SelectException(e);
        }

    }

    // 修改货物
    public void changeCargo(String cargoName, String newName) throws UpdateException {
        try {
            cargoMapper.changeCargoName(cargoName, newName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e);
        }
    }

    /**
     * judge if the CARGO object is available
     *
     * @param cargoId
     * @return the current state of this CARGO
     * @throws InnerException
     */
    public boolean isCargoExist(int cargoId) throws InnerException {
        try {
            int exis = cargoMapper.cargoEixs(cargoId);
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
     * get target CARGO object with it's name
     *
     * @param cargoName
     * @return target CARGO object id
     * @throws InnerException
     */
    public int getCargoIdByName(String cargoName) throws InnerException {
        int cargoId = 0;
        try {
            cargoId = cargoMapper.getCargoIdByName(cargoName);
        } catch (Exception e) {
            throw new InnerException(e);
        }
        return cargoId;
    }

    /**
     * get all CARGO objects from database
     *
     * @return all CARGO objects
     * @throws SelectException
     */
    public List<cargoBean> getAllCargo() throws SelectException {
        List<cargoBean> cargoAll = null;
        try {
            cargoAll = cargoMapper.getAllCargo();
        } catch (Exception e) {
            throw new SelectException(e);
        }
        return cargoAll;
    }
}
