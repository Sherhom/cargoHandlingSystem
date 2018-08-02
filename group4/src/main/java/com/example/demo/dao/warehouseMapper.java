package com.example.demo.dao;

import org.apache.ibatis.annotations.*;
import com.example.demo.domain.warehouseBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 与仓库相关的数据库映射
 *
 * @author 杨添
 */
@Component
@Mapper
public interface warehouseMapper {
    /**
     * 创建仓库
     *
     * @param whName,whAddress
     * @return
     */
    @Insert("insert into warehouseInfo(whName,whAddress) values(#{whName},#{whAddress})")
    public int addWarehouse(@Param("whName") String whName, @Param("whAddress") String whAddress);

    /**
     * 关闭仓库
     *
     * @param whName
     * @return
     */
    @Update("update warehouseInfo set status = 0 where whName = #{whName}")
    public int closeWarehouse(@Param("whName") String whName);

    /**
     * 修改仓库信息
     *
     * @param whName,newName,newAddress
     * @return
     */
    @Update("update warehouseInfo set whName=#{newName} ,whAddress = #{newAddress} where whName=#{whName}")
    public int changeWarehouse(@Param("newName") String newName, @Param("whName") String whName,
                               @Param("newAddress") String newAddress);

    /**
     * 查询仓库(模糊查询)
     *
     * @param keyword
     * @return
     */
    @Select("select * from warehouseInfo where whName Like '%${keyword}%'")
    public List<warehouseBean> selectWarehouse(@Param("keyword") String keyword);

    /**
     * 查询仓库(精确查询)
     *
     * @param keyword
     * @return
     */
    @Select("select * from warehouseInfo where whName = #{keyword}")
    public warehouseBean selectWarehouse2(@Param("keyword") String keyword);

    /**
     * judge the existence of WAREHOUSE object
     *
     * @param wareId
     * @return
     */
    @Select("SELECT * FROM warehouseInfo WHERE whId = #{wareId} AND status = 1 LIMIT 1")
    public int warehouseEixs(@Param("wareId") int wareId);

    /**
     * get the id with the selected WAREHOUSE object's name
     *
     * @param wareName
     * @return
     */
    @Select("SELECT whId FROM warehouseInfo WHERE whName = #{wareName}")
    public int getWareIdByName(@Param("wareName") String wareName);

    /**
     * get all WAREHOUSE objects
     *
     * @return
     */
    @Select("SELECT * FROM warehouseInfo")
    public List<warehouseBean> getAllWarehouse();

    @Select("SELECT count(*) FROM warehouseInfo")
    public int getCount();

}
