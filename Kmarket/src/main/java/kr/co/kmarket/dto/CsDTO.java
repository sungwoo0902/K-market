package kr.co.kmarket.dto;

import kr.co.kmarket.db.Utils;

public class CsDTO {
	private int no;
	private int parent;
	private int group;
	private int cate1;	
	private int cate2;
	private String uid;
	private String title;
	private String content;
	private String rdate;
	private int answer;

	
	
	
	
	
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public void setAnswer(String answer) {
		this.answer = Integer.parseInt(answer);
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setNo(String no) {
		this.no = Integer.parseInt(no);
	}
	
	
	
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setParent(String parent) {
		this.parent = Integer.parseInt(parent);
	}
	
	
	public String getUid() {
		return uid;
	}
	public String getMaskingUid() {
		return Utils.masking(uid);
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	// 추가 필드
	private String group_name;
	private String cate1_name;
	private String cate1_discription;
	private String cate2_name;

	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public void setGroup(String group) {
		this.group = Integer.parseInt(group);
	}
	
	
	
	
	public int getCate1() {
		return cate1;
	}
	public void setCate1(int cate1) {
		this.cate1 = cate1;
	}
	public void setCate1(String cate1) {
		this.cate1 = Integer.parseInt(cate1);
	}
	
	
	
	public int getCate2() {
		return cate2;
	}
	public void setCate2(int cate2) {
		this.cate2 = cate2;
	}
	public void setCate2(String cate2) {
		this.cate2 = Integer.parseInt(cate2);
	}
	
	
	public String getRdate() {
		return rdate.substring(2, 10);
	}
	public String getFullRdate() {
		return rdate.substring(0, 10);
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
	
	public String getCate1_name() {
		return cate1_name;
	}
	public void setCate1_name(String cate1_name) {
		this.cate1_name = cate1_name;
	}
	
	
	
	public String getCate1_discription() {
		return cate1_discription;
	}
	public void setCate1_discription(String cate1_discription) {
		this.cate1_discription = cate1_discription;
	}
	
	
	
	public String getCate2_name() {
		return cate2_name;
	}
	public void setCate2_name(String cate2_name) {
		this.cate2_name = cate2_name;
	}
	
	
	
	@Override
	public String toString() {
		return "CsDTO [no=" + no + ", parent=" + parent + ", group=" + group + ", cate1=" + cate1 + ", cate2=" + cate2
				+ ", uid=" + uid + ", title=" + title + ", content=" + content + ", rdate=" + rdate + ", group_name="
				+ group_name + ", cate1_name=" + cate1_name + ", cate1_discription=" + cate1_discription
				+ ", cate2_name=" + cate2_name + "]";
	}
	
	
	/*
	public static String toReplace(String str) {
		 
	    if(str == null) {
	        return null;
	    }
	 
	    String returnStr = str;
	 
	    returnStr = returnStr.replaceAll("<br>", "\n");
	 
	    returnStr = returnStr.replaceAll("&gt;", ">");
	 
	    returnStr = returnStr.replaceAll("&lt;", "<");
	 
	    returnStr = returnStr.replaceAll("&quot;", "");
	    
	    returnStr = returnStr.replaceAll("&nbsp;", " ");
	 
	    returnStr = returnStr.replaceAll("&amp;", "&");
	    
	    return returnStr;
	 
	}
	
	public static String getReplace(String srcString) {
		 
		  String rtnStr = null;
		  
		  try{
			  StringBuffer strTxt = new StringBuffer("");
			  char chrBuff;
			  
			  int len = srcString.length();
			    for(int i = 0; i < len; i++) {
			    
			    chrBuff = (char)srcString.charAt(i);
			    
			      switch(chrBuff) {
			        case '<':
				        strTxt.append("&lt;");
				        break;
			        
			        case '>':
				        strTxt.append("&gt;");
				        break;
			        
			        case '&':
				        strTxt.append("&amp;");
				        break;
			        
			        default:
			        	strTxt.append(chrBuff);
			      }
			    }
			  rtnStr = strTxt.toString();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  return rtnStr;
	}
	*/
}