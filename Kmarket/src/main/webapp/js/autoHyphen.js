/**
 * 
 */
// 자동으로 하이픈을 붙임

$(function(){	
	// 전화번호용
	const autoHyphen1 = (target) => {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	}
	 
	// 휴대폰용
	const autoHyphen2 = (target) => {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
			.replace(/(\-{1,2})$/g, "");
	}
	
	// 사업자 등록번호용
	const autoHyphen3 = (target) => {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/^(\d{0,3})(\d{0,2})(\d{0,5})$/g, "$1-$2-$3")
			.replace(/(\-{1,2})$/g, "");
	}
	
	
	const inputs = document.querySelectorAll('input[class^="auto"]');
	inputs.forEach(input => {
		input.addEventListener('input', function() {
			if (this === document.activeElement) {
				
				if (this.classList.contains('auto1')) {
					autoHyphen1(this);
					
				} else if (this.classList.contains('auto2')) {
					autoHyphen2(this);
					
				} else if (this.classList.contains('auto3')) {
					autoHyphen3(this);
					
				} else if (this.classList.contains('auto4')) {
					autoHyphen4(this);
				}
			}
		});
	});
});