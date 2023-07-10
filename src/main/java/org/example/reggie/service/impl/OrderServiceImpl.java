package org.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.reggie.entity.Orders;
import org.example.reggie.mapper.OrderMapper;
import org.example.reggie.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
}
