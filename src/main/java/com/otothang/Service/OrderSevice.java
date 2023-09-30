package com.otothang.Service;

import java.util.List;

import com.otothang.models.Order;
import com.otothang.models.Product;

public interface OrderSevice {
	List<Order> getAll();
	Boolean create(Order Order);
	Order findById(Integer id);
}
