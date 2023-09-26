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
	
	public void insertOrder(OrderDTO order, String uid) {
		dao.insertOrder(order, uid);
	}
	public OrderDTO selectOrder(int ordNo, String uid) {
		return dao.selectOrder(ordNo, uid);
	}
	public int selectLastOrdNo(String uid) {
		return dao.selectLastOrdNo(uid);
	}
	public void selectOrders() {}
	public void updateOrder(OrderDTO order, int ordNo) {
		dao.updateOrder(order, ordNo);
	}
	public void deleteOrder() {}
	public int selectOrderCount(String uid) {
		return dao.selectOrderCount(uid);
	}
	public void deleteBeforeOrder(String uid) {
		dao.deleteBeforeOrder(uid);
	}
	
	
	public void insertOrderItems(List<CartDTO> carts, int ordNo) {
		for(CartDTO orderItem : carts) {
			dao.insertOrderItems(orderItem, ordNo);
		}
	}
	public void insertOrderItem(OrderItemDTO orderItem, int ordNo) {
		dao.insertOrderItem(orderItem, ordNo);
	}
	public List<OrderItemDTO> selectOrderItems(String uid, int ordNo) {
		return dao.selectOrderItems(uid, ordNo);
	}
	public void deleteBeforeOrderItems(String uid) {
		dao.deleteBeforeOrder(uid);
	}
	
	
	
	public void insertPoint(String uid, int ordNo, String savePoint) {
		dao.insertPoint(uid, ordNo, savePoint);
	}
}
