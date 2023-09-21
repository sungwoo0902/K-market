package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.dto.OrderItemDTO;

public class OrderDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertOrder(OrderDTO order) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER);
			
			System.out.println("1 : "+order.getOrdUid());
			System.out.println("2 : "+order.getOrdCount());
			System.out.println("3 : "+order.getOrdPrice());
			System.out.println("4 : "+order.getOrdDiscount());
			System.out.println("5 : "+order.getOrdDelivery());
			System.out.println("6 : "+order.getOrdDelivery());
			System.out.println("7 : "+order.getOrdTotPrice());
			System.out.println("8 : "+order.getOrdComplete());
			
			
			psmt.setString(1, order.getOrdUid());
			psmt.setInt(2, order.getOrdCount());
			psmt.setInt(3, order.getOrdPrice());
			psmt.setInt(4, order.getOrdDiscount());
			psmt.setInt(5, order.getOrdDelivery());
			psmt.setInt(6, order.getSavePoint());
			psmt.setInt(7, order.getOrdTotPrice());
			psmt.setInt(8, order.getOrdComplete());
			psmt.setString(9, order.getRecipName());
			psmt.setString(10, order.getRecipHp());
			psmt.setString(11, order.getRecipZip());
			psmt.setString(12, order.getRecipAddr1());
			psmt.setString(13, order.getRecipAddr2());
			psmt.setInt(14, order.getOrdPayment());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertOrder() error :"+e.getMessage());
		}
	}
	
	public String selectOrdNo(String uid) {
		
		String ordNo = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
				
			if(rs.next()) {
				ordNo = rs.getString(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectOrder() error :"+e.getMessage());
		}
		return ordNo;
	}
	public void selectOrders() {}
	public void updateOrder() {}
	public void deleteOrder() {}
	
	
	/////////////////////////////////////////////////
	
	public void insertOrderItem(CartDTO orderItem, String ordNo) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER_ITEM);
			
			
		} catch (Exception e) {
			logger.error("insertOrderItem() error :"+e.getMessage());
		}
	}
	public List<OrderItemDTO> selectOrderItems(String ordNo) {
		
		List<OrderItemDTO> items = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER_ITEMS);
			psmt.setString(1, ordNo);
			
			while(rs.next()) {
				OrderItemDTO dto = new OrderItemDTO();
				dto.setOrdNo(rs.getString(1));
				dto.setProdNo(rs.getString(2));
				dto.setCount(rs.getString(3));
				dto.setPrice(rs.getString(4));
				dto.setDiscount(rs.getString(5));
				dto.setPoint(rs.getString(6));
				dto.setDelivery(rs.getString(7));
				dto.setThumb1(rs.getString(9));
				dto.setProdCate1(rs.getString(10));
				dto.setProdCate2(rs.getString(11));
				dto.setProdName(rs.getString(12));
				dto.setDescript(rs.getString(13));
				
				items.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectOrderItems() error :"+e.getMessage());
		}
		
		return items;
	}
	
}