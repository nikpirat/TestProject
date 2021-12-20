package com.example.TestProject.service.impl;

import com.example.TestProject.model.Coupon;
import com.example.TestProject.model.Luggage;
import com.example.TestProject.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Service
public class CouponServiceImpl implements CouponService {
    private final List<Coupon> coupons = Stream.of(new Coupon(1), new Coupon(2), new Coupon(3))
            .collect(toCollection(ArrayList::new));

    @Override
    public Coupon getById(long id) {
        return coupons
                .stream()
                .filter(coupon -> id == coupon.getId())
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public double setNewPrice(double currentPrice) {
        double price = 0;
        short min = 1;
        short max = 3;
        Random random = new Random();
        switch (random.nextInt(max-min+1)+min) {
            case 1:
                price = currentPrice / 100 * 10;
                break;
            case 2:
                price = currentPrice / 100 * 50;
                break;
            case 3:
                price = currentPrice / 100 * 60;
                break;
        }
        return currentPrice - price;
    }
}
