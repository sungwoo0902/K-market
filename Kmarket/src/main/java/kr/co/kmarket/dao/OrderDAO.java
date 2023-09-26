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
	
	public void insertOrder(OrderDTO order, String uid) {
		logger.debug("here");
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER_COUNT);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				logger.debug("here...2");
				psmt = conn.prepareStatement(SQL.DELETE_BEFORE_ORDER_ITEMS);
				psmt.setString(1, uid);
				psmt.executeUpdate();
				logger.debug("here...3");
				psmt = conn.prepareStatement(SQL.DELETE_BEFORE_ORDER);
				psmt.setString(1, uid);
				psmt.executeUpdate();
				logger.debug("here...4");
			}
			logger.debug("here...5");
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
	public OrderDTO selectOrder(int ordNo, String uid) {
		OrderDTO order = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER);
			psmt.setInt(1, ordNo);
			psmt.setString(2, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				order = new OrderDTO();
				order.setOrdNo(rs.getString(1));
				order.setOrdUid(rs.getString(2));
				order.setOrdCount(rs.getString(3));
				order.setOrdPrice(rs.getString(4));
				order.setOrdDiscount(rs.getString(5));
				order.setOrdDelivery(rs.getString(6));
				order.setSavePoint(rs.getString(7));
				order.setUsedPoint(rs.getString(8));
				order.setOrdTotPrice(rs.getString(9));
				order.setRecipName(rs.getString(10));
				order.setRecipHp(rs.getString(11));
				order.setRecipZip(rs.getString(12));
				order.setRecipAddr1(rs.getString(13));
				order.setRecipAddr2(rs.getString(14));
				order.setOrdPayment(rs.getString(15));
				order.setOrdComplete(rs.getString(16));
				order.setOrdDate(rs.getString(17));
			}
			close();
		} catch (Exception e) {
			logger.error("selectOrder() error :"+e.getMessage());
		}
		return order;
	}
	
	
	public int selectLastOrdNo(String uid) {
		
		int ordNo = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LAST_ORDNO);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
				
			if(rs.next()) {
				ordNo = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectOrder() error :"+e.getMessage());
		}
		return ordNo;
	}
	public void selectOrders() {}
	public void updateOrder(OrderDTO order, int ordNo) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ORDER);
			psmt.setInt(1, order.getUsedPoint());
			psmt.setInt(2, order.getOrdTotPrice());
			psmt.setString(3, order.getRecipName());
			psmt.setString(4, order.getRecipHp());
			psmt.setString(5, order.getRecipZip());
			psmt.setString(6, order.getRecipAddr1());
			psmt.setString(7, order.getRecipAddr2());
			psmt.setInt(8, order.getOrdPayment());
			psmt.setInt(9, order.getOrdComplete());
			psmt.setInt(10, ordNo);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.debug("updateOrder() error :"+e.getMessage());
		}
	}
	public void deleteOrder() {}
	public int selectOrderCount(String uid) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER_COUNT);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}
			
			logger.debug("result :"+result);
			
			close();
		} catch (Exception e) {
			logger.error("selectOrderCount() error :"+e.getMessage());
		}
		return result;
	}
	public void deleteBeforeOrder(String uid) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_BEFORE_ORDER);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("selectOrderCount() error :"+e.getMessage());
		}
	}
	
	
	/////////////////////////////////////////////////
	
	public void insertOrderItems(CartDTO orderItem, int ordNo) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER_ITEM);
			psmt.setInt(1, ordNo);
			psmt.setInt(2, orderItem.getProdNo());
			psmt.setInt(3, orderItem.getCount());
			psmt.setInt(4, orderItem.getOrgPrice());
			psmt.setInt(5, orderItem.getDiscount());
			psmt.setInt(6, orderItem.getPoint());
			psmt.setInt(7, orderItem.getDelivery());
			psmt.setInt(8, orderItem.getTotal());
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertOrderItems() error :"+e.getMessage());
		}
	}
	public void insertOrderItem(OrderItemDTO orderItem, int ordNo) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER_ITEM);
			psmt.setInt(1, ordNo);
			psmt.setInt(2, orderItem.getProdNo());
			psmt.setInt(3, orderItem.getCount());
			psmt.setInt(4, orderItem.getPrice());
			psmt.setInt(5, orderItem.getDiscount());
			psmt.setInt(6, orderItem.getPoint());
			psmt.setInt(7, orderItem.getDelivery());
			psmt.setInt(8, orderItem.getTotal());
			
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertOrderItem() error :"+e.getMessage());
		}
	}
	public List<OrderItemDTO> selectOrderItems(String uid, int ordNo){
		
		List<OrderItemDTO> items = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDER_ITEMS);
			psmt.setString(1, uid);
			psmt.setInt(2, ordNo);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderItemDTO dto = new OrderItemDTO();
				dto.setOrdNo(rs.getString(1));
				dto.setProdNo(rs.getString(2));
				dto.setCount(rs.getString(3));
				dto.setPrice(rs.getString(4));
				dto.setDiscount(rs.getString(5));
				dto.setPoint(rs.getString(6));
				dto.setDelivery(rs.getString(7));
				dto.setTotal(rs.getString(8));
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
	public void deleteBeforeOrderItems(String uid) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_BEFORE_ORDER_ITEMS);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("deleteBeforeOrderItems() error :"+e.getMessage());
		}
		
	}
	
	
	public void insertPoint(String uid, int ordNo, String savePoint) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_POINT);
			psmt.setString(1, uid);
			psmt.setInt(2, ordNo);
			psmt.setString(3, savePoint);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertPoint() error : "+e.getMessage());
		}
	}
	
}
