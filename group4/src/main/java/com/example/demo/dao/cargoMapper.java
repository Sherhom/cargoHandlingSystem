package com.example.demo.dao;

import com.example.demo.domain.cargoBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 与货物相关的数据库映射
 *
 * @author 杨添
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

    @Select("Select * from cargoinfo ")
    public List<cargoBean> selectCargoAll();

    /**
     * 查询货物（精确查询）
     *
     * @param name
     * @return map
     */
    @Select("Select * from cargoinfo where cargoName = #{name} ")
    public cargoBean selectCargo2(@Param("name") String name);

    /**
     * judge if the CARGO object is available
     *
     * @param cargoId
     * @return
     */
    @Select("SELECT * FROM cargoinfo WHERE cargoId = #{cargoId} AND status = 1 LIMIT 1")
    public int cargoEixs(@Param("cargoId") int cargoId);

    /**
     * get the Id of the selected CARGO object
     *
     * @param cargoName
     * @return
     */
    @Select("SELECT cargoId FROM cargoinfo WHERE cargoName = #{cargoName}")
    public int getCargoIdByName(@Param("cargoName") String cargoName);

    /**
     * get all CARGO objects
     *
     * @return
     */
    @Select("SELECT * FROM cargoinfo")
    public List<cargoBean> getAllCargo();

    /**
     * get all CARGO objects
     *
     * @return
     */
    @Select("SELECT count(*) FROM cargoinfo")
    public int getCount();
}
