package com.example.demo.dao;

import com.example.demo.domain.skuBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 与sku相关的数据库映射
 *
 * @author 杨添
 */
@Component
@Mapper
public interface skuMapper {
    /**
     * 添加sku信息
     *
     * @param
     * @return
     */
    @Insert("insert into skuInfo(skuColor,skuType,cargoId,whId,count) values(#{skuColor},#{skuType},#{cargoId},#{whId},#{amount})")
    public int addSku(@Param("skuColor") String skuColor, @Param("skuType") String skuType,
                      @Param("cargoId") int cargoId, @Param("whId") int whId, @Param("amount") int amount);

    /**
     * 删除一条sku
     *
     * @param
     * @return
     */
    @Delete("delete from skuInfo where skuId = #{skuId}")
    public int deleteSkuById(int skuId);

    /**
     * 删除对应货物下的sku
     *
     * @param
     * @return
     */
    @Delete("delete from skuInfo where cargoId = #{cargoId}")
    public int deleteSkuByCargo(int cargoId);

    /**
     * 修改sku
     *
     * @param
     * @return
     */
    @Update("update skuInfo set skuColor = #{skuColor},skuType = #{skuType},cargoId = #{cargoId},whId = #{whId}," +
            "count = #{amount} where skuId = #{skuId}")
    public int changeSku(int skuId, String skuColor, String skuType, int cargoId, int whId, int amount);

    /**
     * 查询sku
     *
     * @param
     * @return
     */
    @Select("select * from skuInfo where cargoId = #{cargoId}")
    public List<skuBean> selectSkuByCargo(int cargoId);

    /**
     * delete all SKU objects that linked with the target WAREHOUSE object
     *
     * @param wareId
     * @return
     */
    @Delete("DELETE FROM skuInfo WHERE whId = #{wareId}")
    public int delSkuByWareId(@Param("wareId") int wareId);

    /**
     * get all SKU objects
     *
     * @return
     */
    @Select("SELECT * FROM skuInfo")
    public List<skuBean> getAllSku();

}
