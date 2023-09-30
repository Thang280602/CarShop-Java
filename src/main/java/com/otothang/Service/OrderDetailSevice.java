package com.otothang.Service;

import java.util.List;

import com.otothang.models.Order;
import com.otothang.models.OrderDetail;

public interface OrderDetailSevice {
	Boolean add(OrderDetail orderDetail);
	List<OrderDetail> getByOrderId(Order order);
}
