package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.dto.MemberDTO;

public enum MemberService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberDAO dao = MemberDAO.getInstance();
	
	// CRUD 메서드
	public int insertMember(MemberDTO dto) {
		return dao.insertMember(dto);
	}
	
	public void insertSeller(MemberDTO dto) {
		dao.insertSeller(dto);
	}
	
	public MemberDTO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
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
}