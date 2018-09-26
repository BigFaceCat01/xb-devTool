var util = util || {};
(function (u) {

    u.openLoad = function () {//开启加载层
        return layer.load(1, {
            shade: [0.5, '#ccc'], //0.1透明度的白色背景
            time: 5000
        }); //换了种风格
    };
    u.closeLoad = function (index) {//关闭加载层
        if (index) {
            layer.close(index);
        } else {
            layer.close(layer.index);
        }
    };
    u.isOk = function (resp) {
        return resp.code == 0;
    };
    u.showMsg = function (msg) {
        layer.msg(msg);
    };
    u.confirm = function (msg, callback) {
        //询问框
        var index = layer.confirm(msg, {
            btn: ['确认', '取消'] //按钮
        }, function () {
            layer.close(index);
            if (typeof(callback) == "function") {
                callback();
            }
        }, function () {
            layer.close(index);
        });
    };

    u.formatDate = function (date, fmt) {//日期格式转换器
        var o = {
            "M+": date.getMonth() + 1, // 月份
            "d+": date.getDate(), // 日
            "h+": date.getHours(), // 小时
            "m+": date.getMinutes(), // 分
            "s+": date.getSeconds(), // 秒
            "q+": Math.floor((date.getMonth() + 3) / 3), // 季度
            "S": date.getMilliseconds()
            // 毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ?
                    (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    u.str2Json = function (str) {
        return JSON.parse(str);
    }
    u.json2Str = function (jsonObj) {
        return JSON.stringify(jsonObj);
    }

    u.validPhone = function (phone) {//验证国际手机号
        // return /^[1-9\+].*$/.test(phone)
         return /^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/.test(phone);
    };
    u.validQq = function (qq) {//验证国际QQ
        return /^[1-9][0-9]{4,15}$/.test(qq);
    };
    u.validUsername = function (username) {//验证用户名
        return /^[a-zA-Z][a-zA-Z0-9_]{5,30}$/.test(username);
    };
    u.validRealName= function (username) {//验证姓名
        return  /^[\u4E00-\u9FA5]{0,30}$/.test(username);
    };
    u.validEmail = function (email) {//验证邮箱
        return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(email);
    };
    u.isEmpty = function (str) {
        return str == undefined || str.trim() == '';
    };
    u.validLength = function (str, minLength, maxLength) {
        if (u.isEmpty(str)) {
            return false;
        }
        if (str.trim().length < minLength) {
            return false;
        }
        if (str.trim().length > maxLength) {
            return false;
        }
        return true;
    };
    var key = "ca-left-menus";
    u.getMenus = function () {
        var s = sessionStorage.getItem(key);
        if (s != null) {
            return u.str2Json(s);
        }
        return undefined;
    };
    u.setMenus = function (menus) {
        if (menus && menus != '') {
            sessionStorage.setItem(key, util.json2Str(menus));
        }

    };
    u.removeMenus = function () {
        sessionStorage.removeItem(key);
    };
    //显示选中的条数
    u.showCheckNum= function () {
        var checkIds = $("#checkIds").val();
        if (checkIds != "") {
            var ids = checkIds.split(",");
            $("input[name='check_id']").each(function () {
                var id = $(this).val();
                if ($.inArray(id, ids) >= 0) {
                    $(this).prop("checked", true);
                }
            });
            if (ids.length == $("input[name='check_id']").length) {
                $("#check_all").prop('checked', true);
            }else {
                $("#check_all").prop('checked', false);
            }
            $("#check_id_num").html(ids.length);
        } else {
            $("#check_all").prop('checked', false);
            $("#check_id_num").html(0);
        }
    };
    u.checkIdsRe=function (id) {
        var checkIds = $("#checkIds").val();
        if (checkIds != "") {
            var ids = checkIds.split(",");
            if ($.inArray(id, ids) >= 0) {
                ids.splice($.inArray(id, ids), 1);
                checkIds = ids.join(",");
            }
        }
        $("#checkIds").val(checkIds);
        u.showCheckNum();
    };
    //全选
    u.checkAll=function () {
        if ($("#check_all").prop('checked')) {
            $("input[name='check_id']").each(function () {
                var brandId = $(this).text();
                $(this).prop("checked", true);
                u.checkIdsAdd(brandId);
            });
        } else {
            $("input[name='check_id']").each(function () {
                var brandId = $(this).text();
                $(this).prop("checked", false);
                u.checkIdsRe(brandId);
            });
        }
    };
    u.checkIdsAdd= function (id) {
        var checkIds = $("#checkIds").val();
        if (checkIds != "") {
            var ids = checkIds.split(",");
            if ($.inArray(id, ids) < 0) {
                ids.push(id);
                checkIds = ids.join(",");
            }
        } else {
            var ids = new Array();
            ids.push(id);
            checkIds = ids.join(",");
        }
        $("#checkIds").val(checkIds);
        u.showCheckNum();
    };
    //单选框绑定的事件
    u.checkIds=function (e) {
        var id = $(e.target).text();
        if ($(e.target).is(':checked')) {
            u.checkIdsAdd(id);
        } else {
            u.checkIdsRe(id);
        }
    };
    u.clear=function () {
        $("#checkIds").val("");
        $("#check_all").prop("checked", false);
        $("#check_id_num").html(0);
        $("input[name='check_id']").each(function () {
            $(this).prop("checked", false);
        });
    };
})(util);
