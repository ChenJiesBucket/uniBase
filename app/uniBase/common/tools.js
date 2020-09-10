//工具方法

/**
 * 身份证校验
 * @param {Object} idcard
 */
function validateIdCard(idcard){
	// 正则表达式：
	var idcardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if (!idcardReg.test(idcard)) {
	    return false;
	}else{
		return true;
	}
}
/**
 * 手机号校验
 * @param {Object} phone
 */
function validatePhone(phone){
	// 正则表达式：
	 var phoneReg = /^1(3|4|5|6|7|8|9)\d{9}$/;
	if (!phoneReg.test(phone)) {
	    return false;
	}else{
		return true;
	}
}

/**
 * email校验
 * @param {Object} email
 */
function validateEmail(email){
	// 正则表达式：
	var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!emailReg.test(email)) {
	    return false;
	}else{
		return true;
	}
}
/**
 * 校验只要是数字（包含正负整数，0以及正负浮点数）就返回true
 * @param {Object} val
 */
function isNumber(val){
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)){
        return true;
    }else{
        return false;
    }

}
/**
 * 获取当前登录用户的所属级别和code
 */
function getguide(){
	var guide = JSON.parse(localStorage.getItem("guide"));
	var type = guide.type
	var result = {};
	var code = "";
	if(type == 0){
		code = guide.currArea;
	}else if(type == 1){
		code = guide.areaCode;
	}else if(type == 2){
		code = guide.streetCode;
	}else if(type == 3){
		code = guide.comCode;
	}else if(type == 4){
		code = guide.gridCode;
	}
	result = {type:type,code:code};
	return result;
}