package com.example.demo.dao;

import com.example.demo.domain.cargo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 与货物相关的数据库映射
 *
 * @author 杨添
 *
 */
@Component
@Mapper
public interface cargoMapper {
    /**
     * 添加货物
     *
     * @param cargo
     * @return
     */
    @Insert("insert into cargoinfo(cargoName) values(#{cargoName})")
    public int saveCargo(cargo cargo);
}
