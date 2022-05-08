package com.itheima.order.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_order")
public class OrderEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("goods_id")
    private int goodsId;

    @TableField("goods_name")
    private String goodsName;

    @TableField("number")
    private int number;

    @TableField("is_del")
    private int isDel;
}
