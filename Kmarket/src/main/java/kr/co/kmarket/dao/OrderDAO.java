package kr.co.kmarket.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.OrderDTO;

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
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertOrder() error :"+e.getMessage());
		}
	}
	public void selectOrder() {}
	public void selectOrders() {}
	public void updateOrder() {}
	public void deleteOrder() {}
}
