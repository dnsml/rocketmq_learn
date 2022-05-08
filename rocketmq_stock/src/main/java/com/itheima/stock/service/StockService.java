package com.itheima.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.stock.entity.StockEntity;

public interface StockService extends IService<StockEntity> {
    void reduceStock(int goodsId, int number,int version);

    void revertStock(int goodsId, int number,int version);
}
