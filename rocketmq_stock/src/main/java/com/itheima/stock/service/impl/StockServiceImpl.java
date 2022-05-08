package com.itheima.stock.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.stock.entity.StockEntity;
import com.itheima.stock.mapper.StockMapper;
import com.itheima.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, StockEntity> implements StockService  {

    @Autowired
    private StockMapper stockMapper;

    @Override
    @Transactional
    public void reduceStock(int goodsId, int number, int version) {
        stockMapper.reduceStock(goodsId, number, version);
    }

    @Override
    @Transactional
    public void revertStock(int goodsId, int number, int version) {
        stockMapper.revertStock(goodsId, number, version);
    }
}
