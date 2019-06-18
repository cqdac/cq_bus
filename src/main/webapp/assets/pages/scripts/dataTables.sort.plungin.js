jQuery.extend(jQuery.fn.dataTableExt.oSort, {
    "null-percent-pre": function (a) {
        if(a == null){
            a = "24:59:59.0";
        }
        return a;
    },

    "null-percent-asc": function (a, b) {                //正序排序引用方法
        return a>b;
    },

    "null-percent-desc": function (a, b) {                //倒序排序引用方法
        return b>a;
    }
});