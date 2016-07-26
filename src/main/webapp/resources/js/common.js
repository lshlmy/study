/**
 * @author lshlmy
 */

var publicUtil = {
	numberReg : "/^[0-9]+.?[0-9]*$/",
	isNotEmpty : function(val) {
		return !this.isEmpty(val);
	},
	isEmpty : function(val) {
		if ((!val || typeof (val) == "undefined")
				|| (typeof (val) == "string" && val.length == 0 && val != "undefined")) {
			return true;
		} else {
			return false;
		}
	},
	isNumeric : function(val) {
		if (!numberReg.test(val)) {
			return false;
		}
		return true;
	}
}