package org.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.reggie.common.BaseContext;
import org.example.reggie.common.R;
import org.example.reggie.entity.ShoppingCart;
import org.example.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {

        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        if (dishId != null) {
            lambdaQueryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart shoppingCartServiceOne = shoppingCartService.getOne(lambdaQueryWrapper);


        if (shoppingCartServiceOne != null) {
            // item has been added to the shopping cart
            Integer num = shoppingCartServiceOne.getNumber();
            shoppingCartServiceOne.setNumber(num + 1);
            shoppingCartService.updateById(shoppingCartServiceOne);
        } else {
            // item is new to the shopping cart
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            shoppingCartServiceOne = shoppingCart;

        }

        return R.success(shoppingCartServiceOne);
    }

    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> delete() {

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);
        return R.success("delete success");
    }

}
