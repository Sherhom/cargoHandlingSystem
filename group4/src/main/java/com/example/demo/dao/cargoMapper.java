package com.example.demo.dao;

import com.example.demo.domain.cargoBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
     * @param cargoName
     * @return
     */
    @Insert("insert into cargoinfo(cargoName) values(#{cargoName})")
    public int saveCargo(String cargoName);

    /**
     * 删除货物（将状态置于0）
     *
     * @param cargoName
     * @return map
     */
    @Update("update cargoinfo set status = 0 where cargoName=#{cargoName}")
    public int deleteCargo(@Param("cargoName") String cargoName);

    /**
     * 修改货物名
     *
     * @param cargoName,newName
     * @return map
     */
    @Update("update cargoinfo set cargoName = #{newName} where cargoName=#{cargoName}")
    public void changeCargoName(@Param("cargoName") String cargoName, @Param("newName") String newName);

    /**
     * 查询货物（模糊查询）
     *
     * @param keyword
     * @return map
     */
    @Select("Select * from cargoinfo where cargoName Like '%${keyword}%' ")
    public List<cargoBean> selectCargo(@Param("keyword") String keyword);

    /**
     * 查询货物（精确查询）
     *
     * @param name
     * @return map
     */
    @Select("Select * from cargoinfo where cargoName = #{name} ")
    public cargoBean selectCargo2(@Param("name") String name);
}
