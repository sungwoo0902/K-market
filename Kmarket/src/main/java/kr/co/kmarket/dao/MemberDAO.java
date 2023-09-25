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
			psmt.setInt(11, dto.getLocation());
			result = psmt.executeUpdate();
			logger.debug("result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error("insertMember : " + e.getMessage());
		}
		return result;
	}
	
	public int insertSeller(MemberDTO dto) {
		
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_SELLER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getCompany());
			psmt.setString(4, dto.getCeo());
			psmt.setString(5, dto.getBizRegNum());
			psmt.setString(6, dto.getComRegNum());
			psmt.setString(7, dto.getTel());
			psmt.setString(8, dto.getFax());
			psmt.setString(9, dto.getEmail());
			psmt.setString(10, dto.getZip());
			psmt.setString(11, dto.getAddr1());
			psmt.setString(12, dto.getAddr2());
			psmt.setString(13, dto.getManager());
			psmt.setString(14, dto.getManagerHp());
			psmt.setString(15, dto.getRegip());
			psmt.setString(16, dto.getManager());
			psmt.setString(17, dto.getManagerHp());
			result = psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("insertSeller : " + e.getMessage());
		}
		return result;
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
				member.setLocation(rs.getString(24));
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
	
	public MemberDTO selectMember(String uid) {
		
		MemberDTO member = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER_AUTO_LOGIN);
			psmt.setString(1, uid);
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
				member.setLocation(rs.getString(24));
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
	
	public MemberDTO selectMemRecip(String uid) {
		
		MemberDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER_RECEIPT);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setName(rs.getString(1));
				dto.setHp(rs.getString(2));
				dto.setZip(rs.getString(3));
				dto.setAddr1(rs.getString(4));
				dto.setAddr2(rs.getString(5));
				dto.setPoint(rs.getString(6));
			}
			close();
		} catch (Exception e) {
			logger.error("selectMemRecip() error:"+e.getMessage());
		}
		return dto;
	}
	
	
	// 회원가입시 중복체크 메서드
	public int selectCheckUid(String uid) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckUid result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckHp(String hp) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckHp result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckTel(String tel) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_TEL);
			psmt.setString(1, tel);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckTel result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckFax(String fax) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_FAX);
			psmt.setString(1, fax);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckFax result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckBizRegNum(String bizRegNum) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_BIZ_NUM);
			psmt.setString(1, bizRegNum);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckbizRegNum result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckComRegNum(String comRegNum) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_ONLINE_NUM);
			psmt.setString(1, comRegNum);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckComRegNum result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckEmail(String email) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckEmail result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCheckManagerHp(String managerHp) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DUPLICATION_CHECK_MANAGER_HP);
			psmt.setString(1, managerHp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			logger.debug("selectCheckManagerHp result : " + result);
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// 멤버 포인트 차감
	public void minusMemberPoint(String usedPoint, String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.MINUS_MEMBER_POINT);
			psmt.setString(1, usedPoint);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("minusMemberPoint() errror : "+e.getMessage());
		}
	}
	// 멤버 포인트 적립
	public void plusMemberPoint(int savePoint, String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.PLUS_MEMBER_POINT);
			psmt.setInt(1, savePoint);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("plusMemberPoint() errror : "+e.getMessage());
		}
	}

		

}
