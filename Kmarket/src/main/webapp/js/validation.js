/**
 * 
 */
// 폼 데이터 검증 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isEmailOk = false;
let isHpOk    = false;

let isCeoOk    = false;
let isCompOk   = false;
let isBizNoOk  = false;
let isFaxOk    = false;
let isTelOk    = false;

// 데이터 검증에 사용하는 정규표현식
// 일반 회원
const reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
const reName  = /^[가-힣]{2,10}$/ 
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

const reComp  = /^\(주\)[가-힣]{4,20}$/;
const reCeo   = /^[가-힣]{2,10}$/ 

const reBizNo = /^(?:\d{3})-(?:\d{2})-(?:\d{5})$/;
const reTel   = /^(?:02|031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064)-(?:\d{3})-\d{4}$/;
const reFax   = /^(?:02|070)-(?:\d{4})-\d{4}$/;


// 유효성 검사(Validation)
$(function() {
	
	// 아이디 검사
	$('input[name=km_uid]').keydown(function(){
		$('.msgId').text('');
		isUidOk = false;
	});
	
	// 비밀번호 검사
	$('input[name=km_pass2]').focusout(function() {
		const pass1 = $('input[name=km_pass]').val();
		const pass2 = $('input[name=km_pass2]').val();
		
		if(pass1 == pass2) {
			if(pass2.match(rePass)) {
				$('.msgPass:last').css('color', 'green').text('사용할 수 있는 비밀번호입니다.');
				isPassOk = true;
				
			}else {
				$('.msgPass:last').css('color', 'red').text('사용할 수 없는 비밀번호입니다.');
				isPassOk = false;
			}
			
		}else {
			$('.msgPass:last').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			isPassOk = false;
		}
		
		
	});
	
	// 이름 검사
	$('input[name=km_name]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reName)){
			$('.msgName').text('');
			isNameOk = true;
			
		}else {
			$('.msgName').css('color', 'red').text('유효한 이름이 아닙니다.');
			isNameOk = false;
		}
	})
	
	// 이메일 검사
	$('input[name=email]').keydown(function(){
		$('.emailResult').text('');
		isEmailOk = false;
	});
	
	// 휴대폰 검사 
	$('input[name=hp]').keydown(function(){
		$('.resultHp').text('');
		isHpOk = false;
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 최종 확인
	$('#formUser').submit(function() {
		
		if(!isUidOk) {
			alert('아이디를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isPassOk) {
			alert('비밀번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isNameOk) {
			alert('이름을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isNickOk) {
			alert('별명을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isEmailOk) {
			alert('이메일을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isHpOk) {
			alert('휴대번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		return true; // 폼 전송 시작
	});
});
