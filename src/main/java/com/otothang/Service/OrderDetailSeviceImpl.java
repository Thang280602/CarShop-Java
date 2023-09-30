package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otothang.Repository.OrderDetailRepository;
import com.otothang.models.Order;
import com.otothang.models.OrderDetail;

@Service
public class OrderDetailSeviceImpl implements OrderDetailSevice{
	@Autowired
	private OrderDetailRepository detailRepository;
	@Override
	public Boolean add(OrderDetail orderDetail) {
		try {
			this.detailRepository.save(orderDetail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	@Override
	public List<OrderDetail> getByOrderId(Order order) {
		// TODO Auto-generated method 
		return this.detailRepository.findByOrder(order);
	}

}
