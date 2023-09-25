package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.MemberDTO;

public enum MemberService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberDAO dao = MemberDAO.getInstance();
	
	// CRUD 메서드
	public int insertMember(MemberDTO dto) {
		return dao.insertMember(dto);
	}
	
	public int insertSeller(MemberDTO dto) {
		return dao.insertSeller(dto);
	}
	
	public MemberDTO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
	}
	
	public MemberDTO selectMember(String uid) {
		return dao.selectMember(uid);
	}
	
	public List<MemberDTO> selectMembers() {
		return dao.selectMembers();
	}
	
	public void updateMember(MemberDTO dto) {
		dao.updateMember(dto);
	}
	
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
	
	public MemberDTO selectMemRecip(String uid) {
		return dao.selectMemRecip(uid);
	}
	
	
	// 회원가입 시 중복체크 메서드
	public int selectCheckUid(String uid) {
		return dao.selectCheckUid(uid);
	}
	
	public int selectCheckHp(String hp) {
		return dao.selectCheckHp(hp);
	}
	
	public int selectCheckTel(String tel) {
		return dao.selectCheckTel(tel);
	}
	
	public int selectCheckFax(String fax) {
		return dao.selectCheckFax(fax);
	}
	
	public int selectCheckBizRegNum(String bizRegNum) {
		return dao.selectCheckBizRegNum(bizRegNum);
	}
	
	public int selectCheckComRegNum(String comRegNum) {
		return dao.selectCheckComRegNum(comRegNum);
	}
	
	public int selectCheckEmail(String email) {
		return dao.selectCheckEmail(email);
	}
	
	public int selectCheckManagerHp(String managerHp) {
		return dao.selectCheckManagerHp(managerHp);
	}
	
	
	
	// 멤버 포인트 차감
	public void minusMemberPoint(String usedPoint, String uid) {
		dao.minusMemberPoint(usedPoint, uid);
	}
	// 멤버 포인트 적립
	public void plusMemberPoint(int usedPoint, String uid) {
		dao.plusMemberPoint(usedPoint, uid);
	}
}