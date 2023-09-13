package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.MemberDTO;

public class MemberDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	public int insertMember(MemberDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_MEMBER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setInt(4, dto.getGender());
			psmt.setString(5, dto.getEmail());
			
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			result = psmt.executeUpdate();
			logger.debug("result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error("insertMember : " + e.getMessage());
		}
		return result;
	}
	
	public void insertSeller(MemberDTO dto) {
		
	}
	
	public MemberDTO selectMember(String uid, String pass) {
		
		MemberDTO member = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDTO();
				member.setUid(rs.getString(1));
				member.setPass(rs.getString(2));
				member.setName(rs.getString(3));
				member.setGender(rs.getString(4));
				member.setHp(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setType(rs.getString(7));
				member.setPoint(rs.getString(8));
				member.setLevel(rs.getString(9));
				member.setZip(rs.getString(10));
				member.setAddr1(rs.getString(11));
				member.setAddr2(rs.getString(12));
				member.setCompany(rs.getString(13));
				member.setCeo(rs.getString(14));
				member.setBizRegNum(rs.getString(15));
				member.setComRegNum(rs.getString(16));
				member.setTel(rs.getString(17));
				member.setManager(rs.getString(18));
				member.setManagerHp(rs.getString(19));
				member.setFax(rs.getString(20));
				member.setRegip(rs.getString(21));
				member.setWdate(rs.getString(22));
				member.setRdate(rs.getString(23));
				member.setEtc1(rs.getString(24));
				member.setEtc2(rs.getString(25));
				member.setEtc3(rs.getString(26));
				member.setEtc4(rs.getString(27));
				member.setEtc5(rs.getString(28));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectMember : " + e.getMessage());
		}
		return member;
	}
	
	public List<MemberDTO> selectMembers() {
		return null;
	}
	
	public void updateMember(MemberDTO dto) {
		
	}
	
	public void deleteMember(String uid) {
		
	}

}
