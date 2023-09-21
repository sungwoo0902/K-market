package kr.co.kmarket.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.kmarket.dao.OrderDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.dto.OrderItemDTO;

public enum OrderService {

	INSTANCE;
	private OrderDAO dao = new OrderDAO();
	
	public void insertOrder(OrderDTO order) {
		dao.insertOrder(order);
	}
	public OrderDTO selectOrder(int ordNo, String uid) {
		return dao.selectOrder(ordNo, uid);
	}
	public int selectLastOrdNo(String uid) {
		return dao.selectLastOrdNo(uid);
	}
	public void selectOrders() {}
	public void updateOrder() {}
	public void deleteOrder() {}
	
	
	public void insertOrderItems(List<CartDTO> carts, int ordNo) {
		for(CartDTO orderItem : carts) {
			dao.insertOrderItem(orderItem, ordNo);
		}
	}
	public List<OrderItemDTO> selectOrderItems(int ordNo) {
		return dao.selectOrderItems(ordNo);
	}
}
