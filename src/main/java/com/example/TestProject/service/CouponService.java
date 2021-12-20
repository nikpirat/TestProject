package com.example.TestProject.service;

import com.example.TestProject.model.Coupon;

/**
 * Service class for {@link com.example.TestProject.model.Coupon}
 *
 */

public interface CouponService {
    Coupon getById(long id);


    double setNewPrice(double currentPrice);

}
