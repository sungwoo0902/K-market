package kr.co.kmarket.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.TermsDTO;

public class TermsDAO extends DBHelper {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static TermsDAO instance = new TermsDAO();
	public static TermsDAO getInstance() {
		return instance;
	}
	private TermsDAO() {}
	
	public TermsDTO selectTerms() {
		TermsDTO terms = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				terms = new TermsDTO();
				terms.setTerms(rs.getString(1)); 
				terms.setPrivacy(rs.getString(2)); 
				terms.setLocation(rs.getString(3)); 
				terms.setFinance(rs.getString(4)); 
				terms.setTax(rs.getString(5)); 
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectTerms : " + e.getMessage());
		}
		return terms;
	}
}
