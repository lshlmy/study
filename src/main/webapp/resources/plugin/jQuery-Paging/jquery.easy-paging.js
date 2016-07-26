/**
 * @author lshlmy
 * @desciption 自定义显示方式
 **/

(function($) {

    $["fn"]["easyPaging"] = function(num, o) {

        if (!$["fn"]["paging"]) {
            return this;
        }

        // Normal Paging config
        var opts = {
            "perpage": 5,
            "elements": 0,
            "page": 1,
            "format": "",
            "lapping": 0,
            "pageUrl":"",
            "onSelect": function(page) {

            }
        };

        $["extend"](opts, o || {});

        var $li = $("li", this);

        var masks = {};
        $li.each(function(i) {

            if (0 === i) {
                masks.first = this.innerHTML;
                opts.format += "[";
            } else if (1 == i){
				masks.prev = this.innerHTML;
                opts.format += "<";
			} else if (i+1 ==$li.length){
				masks.last = this.innerHTML;
                opts.format += "]";
			} else if (i+2 == $li.length) {
                masks.next = this.innerHTML;
                opts.format += ">";
            } else {
                masks[i-1] = this.innerHTML.replace(/#[cn]/, function(str) {
                    opts["format"] += str.replace("#", "");
                    return "([...])";
                });
            }
        });
        opts["onFormat"] = function(type) {

            var value = "";

            switch (type) {
                case 'block':
                    value = masks[this["pos"]].replace("([...])", this["value"]);
					
                    if (!this['active'])
                        return '<li class="disabled pageNumClass">' + value + '</li>';
                    if (this["page"] !== this["value"])
                        return '<li class="pageNumClass"><a href="' + opts.pageUrl + this["value"] + '">' + value + '</a></li>';
                    return '<li class="current pageNumClass">' + value + '</li>';
				case 'first':
				case 'last':
                case 'next':
                case 'prev':
                    if (!this['active'])
                        return '<li class="disabled pageUtilClass">' + masks[type] + '</li>';
                    return '<li class="pageUtilClass"><a href="' + opts.pageUrl + this["value"] + '">' + masks[type] + '</a></li>';
				
            }
        };

        return $(this)["paging"](num, opts);

    };

}(jQuery));
