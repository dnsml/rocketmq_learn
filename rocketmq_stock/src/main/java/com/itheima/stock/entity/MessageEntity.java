package com.itheima.stock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_message")
public class MessageEntity {

    @TableId(value = "message_id",type = IdType.NONE)
    private String messageId;
}
