/**
 * 사용자 정보 중복체크. (
 * 	[x] 아이디
 * 	[x] 휴대번호
 * 	[ ] 전화번호 : 중복체크 보류
 * 	[ ] 팩스번호 : 중복체크 보류
 * 	[x] 사업자등록번호
 * 	[ ] 통신판매업신고 번호 : 중복체크 보류
 * 	[x] 이메일 : 이메일 인증 예정
 * 	)
 */
$(function() {
	
	const chkBtn = document.getElementById('check');
	const msgId  = document.getElementsByClassName('msgId')[0];
	
	// 아이디 중복 체크 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	if(chkBtn != null) {
		chkBtn.onclick = function() {
			
			const uid = document.getElementsByName('km_uid')[0].value;
			
			// 유효성 검사
			if(!uid.match(reUid)){
				msgId.innerText   = '유효한 아이디가 아닙니다.';
				msgId.style.color = 'red';
				isUidOk = false;
				return;
			}
			
			// 중복 체크
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/Kmarket/member/duplication.do?uid=' + uid);
			xhr.send();
			
			xhr.onreadystatechange = function() {
				if(xhr.readyState == XMLHttpRequest.DONE) {
					if(xhr.status == 200) {
						const data = JSON.parse(xhr.response);
						
						if(data.result > 0) {
							msgId.innerText = '이미 사용중인 아이디입니다.';
							msgId.style.color = 'red';
							isUidOk = false;
							
						}else {
							msgId.innerText = '사용 가능한 아이디입니다.';
							msgId.style.color = 'green';
							isUidOk = true;
						}
					}
				}
			}
		}
	} // duplication check uid end
	
	
	
	// 휴대번호 중복체크 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	$('input[name=km_hp]').focusout(function() {
		const hp = $(this).val();
		const msgHp = document.getElementsByClassName('msgHp')[0];
		
		if(!hp.match(reHp)) {
			msgHp.innerText   = '유효하지 않은 휴대번호입니다.';
			msgHp.style.color = 'red';
			isHpOk = false;
			return;
		}
		
		
		const url = '/Kmarket/member/duplication.do?hp=' + hp;
		
		$.get(url, function(result) {
			const data = JSON.parse(result);
			
			if(data.result > 0) {
				msgHp.innerText   = '이미 사용중인 휴대번호입니다.';
				msgHp.style.color = 'red';
				isHpOk = false;
				return;
				
			}else {
				msgHp.innerText   = '사용 가능한 휴대번호입니다.';
				msgHp.style.color = 'green';
				isHpOk = true;
				return;
			}
			
		});
	}); // duplication check hp end
	
	
	
	// 이메일 중복체크 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	$('input[name=km_email]').focusout(function() {
		const email = $(this).val();
		const msgEmail = document.getElementsByClassName('msgEmail')[0];
		
		if(!email.match(reEmail)) {
			msgEmail.innerText   = '유효하지 않은 이메일입니다.';
			msgEmail.style.color = 'red';
			isEmailOk = false;
			return;
		}
		
		console.log("isEmailOk 3 : " + isEmailOk)
		
		const url = '/Kmarket/member/duplication.do?email=' + email;
		
		$.get(url, function(result) {
			const data = JSON.parse(result);
			
			if(data.result > 0) {
				msgEmail.innerText   = '이미 사용중인 이메일입니다.';
				msgEmail.style.color = 'red';
				isEmailOk = false;
				return;
				
			}else {
				msgEmail.innerText   = '사용 가능한 이메일입니다.';
				msgEmail.style.color = 'green';
				isEmailOk = true;
				return;
			}
		});
	}); // duplication check hp end
	
	
	
	// 사업자번호 중복체크 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	$('input[name=kms_corp_reg]').focusout(function() {
		const corpNo = $(this).val();
		const msgCorp = document.getElementsByClassName('msgCorp')[0];
		
		if(!corpNo.match(reBizNo)) {
			msgCorp.innerText   = '유효하지 않은 사업자번호입니다.';
			msgCorp.style.color = 'red';
			isBizNoOk = false;
			return;
		}
		
		const url = '/Kmarket/member/duplication.do?bizRegNum=' + corpNo;
		
		$.get(url, function(result) {
			const data = JSON.parse(result);
			
			if(data.result > 0) {
				msgCorp.innerText   = '이미 사용중인 사업자번호입니다.';
				msgCorp.style.color = 'red';
				isBizNoOk = false;
				return;
				
			}else {
				msgCorp.innerText   = '사용 가능한 사업자번호입니다.';
				msgCorp.style.color = 'green';
				isBizNoOk = true;
				return;
			}
		});
	}); // duplication check hp end
});