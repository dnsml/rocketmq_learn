package com.itheima.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.stock.entity.StockEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockMapper extends BaseMapper<StockEntity> {

    @Update("update tb_stock set stock_number=stock_number-#{stockNumber},version=version+1 where goods_id=#{goodsId} and version = #{version}")
    void reduceStock(@Param("goodsId") int goodsId, @Param("stockNumber") int stockNumber, @Param("version") int version);

    @Update("update tb_stock set stock_number=stock_number+#{stockNumber},version=version+1 where goods_id=#{goodsId} and version = #{version}")
    void revertStock(@Param("goodsId") int goodsId, @Param("stockNumber") int stockNumber, @Param("version") int version);
}
