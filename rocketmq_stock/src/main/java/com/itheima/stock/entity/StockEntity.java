package com.itheima.stock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_stock")
public class StockEntity {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField("goods_id")
    private int goodsId;

    @TableField("goods_name")
    private String goodsName;

    @TableField("stock_number")
    private int stockNumber;

    @TableField("version")
    private int version;
}
