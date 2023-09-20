package kr.co.kmarket.service;

import kr.co.kmarket.dao.OrderDAO;
import kr.co.kmarket.dto.OrderDTO;

public enum OrderService {

	INSTANCE;
	private OrderDAO dao = new OrderDAO();
	
	public void insertOrder(OrderDTO order) {
		dao.insertOrder(order);
	}
	public void selectOrder() {}
	public void selectOrders() {}
	public void updateOrder() {}
	public void deleteOrder() {}
}
