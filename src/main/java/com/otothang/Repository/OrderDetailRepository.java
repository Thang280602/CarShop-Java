package com.otothang.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otothang.models.Order;
import com.otothang.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);
}
