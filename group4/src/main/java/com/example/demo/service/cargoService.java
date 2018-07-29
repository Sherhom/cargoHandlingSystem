package com.example.demo.service;

import com.example.demo.dao.cargoMapper;
import com.example.demo.domain.cargo;
import com.example.demo.exception.AddException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public void saveCargo(String cargoName) throws AddException {
        cargo cargo = new cargo(cargoName);
        try{
            cargoMapper.saveCargo(cargo);
        }catch (Exception e){
            e.printStackTrace();
            throw new AddException(e);
        }
    }
}
