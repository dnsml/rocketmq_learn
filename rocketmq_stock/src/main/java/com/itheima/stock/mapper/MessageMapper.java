package com.itheima.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.stock.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {
}
