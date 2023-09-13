package kr.co.kmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.TermsDAO;
import kr.co.kmarket.dto.TermsDTO;

public enum TermsService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private TermsDAO dao = TermsDAO.getInstance();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
}
