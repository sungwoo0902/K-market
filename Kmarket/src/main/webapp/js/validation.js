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
let isComNoOk  = false;
let isFaxOk    = false;
let isTelOk    = false;

//console.log("isUidOk : " + isUidOk)
//console.log("isPassOk : " + isPassOk)
//console.log("isNameOk : " + isNameOk)
//console.log("isEmailOk: " + isEmailOk)
//console.log("isHpOk : " + isHpOk)
	
//console.log("isCeoOk : " + isCeoOk)
//console.log("isCompOk : " + isCompOk)
//console.log("isBizNoOk : " + isBizNoOk)
//console.log("isComNoOk : " + isComNoOk)
//console.log("isFaxOk : " + isFaxOk)
//console.log("isTelOk : " + isTelOk)

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
const reComNo = /^(?:[가-힣]{2,4}-[0-9]{5}|제\s\d-\d{2}-\d{2}-\d{4}호|\d{4}-[가-힣]{4}-\d{4})$/;
const reTel   = /^(?:02|031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064)-(?:\d{3})-\d{4}$/;
const reFax   = /^(02|070)-\d{3,4}-\d{4}$/;


// 유효성 검사(Validation)
$(document).ready(function() {
	
	/** 회원가입 일반, 판매자 공통영역 */
	// 아이디 검사 (중복체크는 duplication.js)
	$('input[name=km_uid]').keydown(function(){
		$('.msgId').css('color', 'black').text('영문, 숫자로 4~12자까지 설정해 주세요.');
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
	
	// 이메일 검사 (중복체크는 duplication.js)
	$('input[name=km_email]').keydown(function(){
		$('.msgEmail').text('');
		isEmailOk = false;
	});
	//$('input[name=km_email]').keydown(function(){
	//	const name = $(this).val();
	//	
	//	if(name.match(reEmail)){
	//		$('.msgEmail').text('');
	//		isEmailOk = true;
	//		
	//	}else {
	//		$('.msgEmail').css('color', 'red').text('유효한 이메일이 아닙니다.');
	//		isEmailOk = false;
	//	}
	//});
	
	// 휴대폰 검사 (중복체크는 duplication.js)
	$('input[name=km_hp]').keydown(function(){
		$('.msgHp').css('color', 'black').text('휴대번호 11자리를 입력하세요.');
		isHpOk = false;
	});
	
	
	
	
	/** 판매자 회원가입 */
	// 회사명 검사
	$('input[name=kms_company]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reComp)){
			$('.msgCompany').css('color', 'black').text('(주)포함 입력, 예) (주)케이마켓');
			isCompOk = true;
			
		}else if(name == ''){
			$('.msgCompany').css('color', 'black').text('(주)포함 입력, 예) (주)케이마켓');
			isCompOk = false;
		
		}else {
			$('.msgCompany').css('color', 'red').text('(주)를 포함하는지 확인해주세요.');
			isCompOk = false;
		}
	})
	
	// 대표자 검사
	$('input[name=kms_ceo]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reCeo)){
			$('.msgCeo').text('');
			isCeoOk = true;
			
		}else {
			$('.msgCeo').css('color', 'red').text('유효한 이름이 아닙니다.');
			isCeoOk = false;
		}
	})
	
	// 사업자번호 검사
	$('input[name=kms_corp_reg]').focusout(function() {
		$('.msgCorp').css('color', 'black').text('사업자등록번호 10자리 입력, 예) 123-45-67890');
		isBizNoOk = false;
	})
	
	// 통신판매신고번호 검사
	$('input[name=kms_online_reg]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reComNo)){
			$('.msgOnline').css('color', 'black').text('- 표시 포함 예) 강남-12345, 제 1-01-23-4567호, 2017-경기성남-0011');
			isComNoOk = true;
			
		}else if(name == ''){
			$('.msgOnline').css('color', 'black').text('- 표시 포함 예) 강남-12345, 제 1-01-23-4567호, 2017-경기성남-0011');
			isComNoOk = false;
		
		}else {
			$('.msgOnline').css('color', 'red').text('유효한 사업자번호가 아닙니다.');
			isComNoOk = false;
		}
	})
	
	// 전화번호 검사
	$('input[name=kms_tel]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reTel)){
			$('.msgTel').css('color', 'black').text('지역번호 포함, 예) 02-234-1234');
			isTelOk = true;
			
		}else if(name == ''){
			$('.msgTel').css('color', 'black').text('지역번호 포함, 예) 02-234-1234');
			isTelOk = false;
		
		}else {
			$('.msgTel').css('color', 'red').text('유효한 전화번호가 아닙니다.');
			isTelOk = false;
		}
	})
	
	// 팩스번호 검사
	$('input[name=kms_fax]').focusout(function() {
		const name = $(this).val();
		
		if(name.match(reFax)){
			$('.msgFax').css('color', 'black').text('지역번호 포함, 예) 02-234-1234');
			isFaxOk = true;
			
		}else if(name == ''){
			$('.msgFax').css('color', 'black').text('지역번호 포함, 예) 02-234-1234');
			isFaxOk = false;
		
		}else {
			$('.msgFax').css('color', 'red').text('유효한 팩스번호가 아닙니다.');
			isFaxOk = false;
		}
	})

	
	
	// 일반인 회원가입 최종 확인
	$('#formRegNormal').submit(function() {
		
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
	
	
	
	// 판매자 회원가입 최종 확인
	$('#formRegSeller').submit(function() {
		
		console.log('서브밋 제출 click!');
		
		if(!isUidOk) {
			alert('아이디를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isPassOk) {
			alert('비밀번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isCompOk) {
			alert('회사명을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isCeoOk) {
			alert('대표자 명을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isBizNoOk) {
			alert('사업자등록번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isComNoOk) {
			alert('통신판매업신고 번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isTelOk) {
			alert('전화번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isFaxOk) {
			alert('팩스번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isEmailOk) {
			alert('이메일을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isNameOk) {
			alert('담당자 이름을 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		if(!isHpOk) {
			alert('담장자 휴대번호를 다시 확인하십시오.');
			return false; // 폼 전송 취소
		}
		
		return true; // 폼 전송 시작
	});
});
