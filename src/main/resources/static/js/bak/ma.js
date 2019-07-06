'use strict';

/**
 * function ： 封装了$main对象的基本功能和jQuery对象的类似功能。
 * @param {Object} windows
 * @param {Object} undefined
 */
(function (windows, undefined){

    // 封装一个数组的方法，数组中是否含有某个元素
    Array.prototype.contains = function (obj) {
        var i = this.length;
        while (i--) {
            if (this[i] === obj) {
                return true;
            }
        }
        return false;
    };

    if (!Array.prototype.every)
    {
        Array.prototype.every = function(fun /*, thisArg */)
        {
            if (this === void 0 || this === null)
                throw new TypeError();
            var t = Object(this);
            var len = t.length >>> 0;
            if (typeof fun !== 'function')
                throw new TypeError();
            var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
            for (var i = 0; i < len; i++)
            {
                if (i in t && !fun.call(thisArg, t[i], i, t))
                    return false;
            }
            return true;
        };
    }

    var $main = new Object();
    // 全局变量
    $main.appkey = null;
    $main.productId = null;
    $main.jsPath = null;
    $main.version = null;
    $main.sessionKey = null;
    $main.newWebUrl = null;
    $main.nowPage = 1;
    $main.pageSize = 3;

    // cookie的設置方法
    $main.setCookie = function(name,value, time){
        if(time){
            var exp = new Date();
            exp.setTime(exp.getTime() + time);
            document.cookie = name + "="+ escape (value) + ";expires="+exp.toGMTString();
        }else {
            document.cookie = name + "="+ escape (value) + ";expires=Fri, 31 Dec 9999 23:59:59 GMT";
        }
    };

    $main.getCookie = function(name){
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    };

    $main.removeRazorCookie = function(name){
        $main.setCookie(name, '', -1);
    };

    // ajax请求
    $main.ajax=function(obj){
        var xmlhttp, type, url, async, dataType, data;
        if (typeof(obj) != 'object')  return false;
        type = obj.type == undefined ? 'POST' : obj.type.toUpperCase();
        url = obj.url == undefined ? windows.location.href : obj.url;
        async = obj.async == undefined ? true : obj.type;
        dataType = obj.dataType == undefined ? 'HTML' : obj.dataType.toUpperCase();
        data = obj.data == undefined ? {} : obj.data;

        var formatParams = function () {
            if (typeof(data) == "object") {
                var str = "";
                for (var pro in data) {
                    str += pro + "=" + data[pro] + "&";
                }
                data = str.substr(0, str.length - 1);
            }
            if (type == 'GET' || dataType == 'JSONP') {
                if (url.lastIndexOf('?') == -1) {
                    url += '?' + data;
                } else {
                    url += '&' + data;
                }
            }
        }
        if (windows.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        } else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (dataType == 'JSONP') {
            if (typeof(obj.beforeSend) == 'function')obj.beforeSend(xmlhttp);
            var callbackName = ('jsonp_' + Math.random()).replace(".", "");
            var oHead = document.getElementsByTagName('head')[0];
            data.callback = callbackName;
            var ele = document.createElement('script');
            ele.type = "text/javascript";
            ele.onerror = function () {
                console.log('请求失败');
                obj.error && obj.error("请求失败");
            };
            oHead.appendChild(ele);
            windows[callbackName] = function (json) {
                oHead.removeChild(ele);
                windows[callbackName] = null;
                obj.success && obj.success(json);
            };
            formatParams();
            ele.src = url;
            return ;
        } else {
            formatParams();
            xmlhttp.open(type, url, async);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            if (typeof(obj.beforeSend) == 'function')obj.beforeSend(xmlhttp);
            xmlhttp.send(data);
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.status != 200) {
                    console.log(xmlhttp.status + '错误');
                    obj.error && obj.error(xmlhttp.status + '错误');
                    return ;
                }
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var res;
                    if (dataType == 'JSON') {
                        try {
                            res = JSON.parse(xmlhttp.responseText);
                        } catch (e) {
                            console.log('返回的json格式不正确');
                            obj.error('返回的json格式不正确');
                        }
                    } else if (dataType == 'XML') {
                        res = xmlhttp.responseXML;
                    } else {
                        res = xmlhttp.responseText;
                    }
                    obj.success && obj.success(res);
                }
            }
        }
    };

    // 给$main添加一个getElementIdx函数
    $main.getElementIdx = function (elt) {
        var count = 1;
        for (var sib = elt.previousSibling; sib; sib = sib.previousSibling) {
            if (sib.nodeType == 1 && sib.tagName == elt.tagName) count++
        }
        return count;
    };

    // 给$main添加一个getElementXPath函数
    $main.getElementXPath = function (elt) {
        var path = '';
        for (; elt && elt.nodeType == 1; elt = elt.parentNode) {
            var idx = $main.getElementIdx(elt);
            var xname = elt.tagName;
            var id = elt.getAttribute('id');
            var clazz = elt.getAttribute('class');
            if (id) {
                xname += "[@id='" + id + "']"
            } else if (clazz) {
                xname += "[@class='" + clazz + "']";
                if (idx > 1) {
                    xname += '[' + idx + ']';
                }
            } else if (idx > 1) {
                xname += '[' + idx + ']'
            }

            path = '/' + xname + path;
        }
        return path;
    };

    // 给$main添加一个getElementXPath函数
    $main.urlParam = function (name) {
        console.log('[\?&#]*' + name + '=([^&#]*)');
        var results = new RegExp('[\?&#]*' + name + '=([^&#]*)').exec(windows.location.href);
        if (results == null) {
            return null;
        } else {
            return results[1] || 0;
        }
    };

    // 创建元素的方法
    $main.createElement = function(str){
        var div1 = document.createElement("div");
        div1.innerHTML = str;
        var div2 = div1.childNodes[0];
        div2.appendTo = function (node){
            if(node){
                node.appendChild(div2);
            }
        }
        return div2;
    };

    // 給$main對象加入了一个queryElement函数
    $main.queryElement = function(e) {
        var msg = '';
        var e = e || windows.event;
        var target = e.target || e.srcElement;
        var nodeName = target.nodeName;
        var id = target.getAttribute('id');
        var name = target.getAttribute('name');
        // 获取xpath的路径
        var xpath = '/' + $main.getElementXPath(target).substring(10);
        msg += '{' + '"nodeName":"' + nodeName + '",' + '"id":"' + id + '",' + '"name":"' + name + '",' + '"xpath":"' + xpath + '"}';
        return msg;
    };

    // 通过字面量方式实现的函数each
    $main.each = function(object, callback){
        if(object!=undefined){
            var type = (function(){
                switch (object.constructor){
                    case Object:
                        return 'Object';
                        break;
                    case Array:
                        return 'Array';
                        break;
                    case NodeList:
                        return 'NodeList';
                        break;
                    default:
                        return 'null';
                        break;
                }
            })();
            // 为数组或类数组时, 返回: index, value
            if(type === 'Array' || type === 'NodeList'){
                // 由于存在类数组NodeList, 所以不能直接调用every方法
                [].every.call(object, function(v, i){
                    return callback.call(v, i, v) === false ? false : true;
                });
            }
            // 为对象格式时,返回:key, value
            else if(type === 'Object'){
                for(var i in object){
                    if(callback.call(object[i], i, object[i]) === false){
                        break;
                    }
                }
            }
        }
    };

    //截取url路径
    $main.getRazorDomainUrl = function(urlStr){
        var durl=/(http|https|ftp):\/\/([^\/]+)\//i;
        var domain = urlStr.match(durl);

        for(var prop in domain){
            //console.log("razorDomain:"+domain[prop]);
            return domain[prop];
            break;
        }
    };
    // 访问时长设置
    $main.getRazorDuraction = function (){
        $main.start_millis = new Date().getTime();

        if($main.getCookie("start_millis")){
            $main.razorEnd = $main.start_millis;
            $main.razorStart = $main.getCookie("start_millis");
            $main.duration = $main.razorEnd - parseInt($main.razorStart);
        } else {
            //$main.razorStart = $main.start_millis;
            //$main.razorEnd = new Date().getTime()+1;
            //$main.duration = 1;
        }
        $main.setCookie("start_millis", $main.start_millis, 5*60*1000);
    };
    // sessionId设置
    $main.setRazorSessionId = function(){
        if(!$main.getCookie("sessionId")){
            $main.setCookie("sessionId", $main.random32(), 1000*60*30);
        }
        $main.timer = windows.setInterval(function(){
            $main.setCookie("sessionId", $main.random32(), 1000*60*30);
            //$main.removeRazorCookie('start_millis');
        },1000*60*30);
    };
    // 点击事件触发，清空定时器，重设sessionId的过期时间
    $main.resetRazorSessionId = function(){
        clearInterval($main.timer);
        if($main.getCookie("sessionId")){
            $main.setCookie("sessionId", $main.getCookie("sessionId"), 1000*60*30);
        } else {
            $main.setCookie("sessionId", $main.random32(), 1000*60*30);
        }
        $main.timer = windows.setInterval(function(){
            $main.setCookie("sessionId", $main.random32(), 1000*60*30);
        },1000*60*30);
    };
    // 设置DevicedId
    $main.setRazorDevicedId = function(){
        var cookie1 = $main.getCookie('cmbcKeys');
        if(cookie1){
            $main.cookie = cookie1;
        } else {
            $main.cookie = new Date().getTime();
            $main.setCookie('cmbcKeys', $main.cookie);
        }
    };
    // 设置配置的UuidCookie
    $main.setRazorUuid = function(){
        var cookie1 = $main.getCookie("razorUuId");
        if(cookie1&&cookie1!='null') {
            $main.sessionKey = cookie1;
        } else {
            // 做设置，防止javascript注入代码
            $main.sessionKey = $main.urlParam('uuId');
            $main.setCookie("razorUuId", $main.sessionKey);
        }
    };
    // 获取当前网站的appkey
    $main.getRazorAppkey = function(){
        if (_maq) {
            for (var i in _maq) {
                switch (_maq[i][0]) {
                    case '_setAccount':
                        $main.appkey = _maq[i][1];
                        break;
                    case '_userId':
                        $main._userId = _maq[i][1];
                        break;
                    default:
                        break;
                }
            }
        }
    };
    // 产生32位随机字符串
    $main.random32 = function(){
        var data=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h",
            "i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
        var result="";
        for(var i=0;i<32;i++){
            var r=Math.floor(Math.random()*data.length);
            result+=data[r];
        }
        return result;
    };
    // 获取usinglog的参数
    $main.getNginxArgs = function(){
        var params = {};
        //Document对象数据
        if (document) {
            params.domain = document.domain || '';
            params.url = document.URL || '';
            params.title = document.title || '';
            params.referrer = document.referrer || '';
        }
        //Windows对象数据
        if (windows && windows.screen) {
            /*params.sh = windows.screen.height || 0;
             params.sw = windows.screen.width || 0;*/
            params.hw = windows.screen.height +"X"+windows.screen.width;
            params.cd = windows.screen.colorDepth || 0;
        }
        //navigator对象数据
        if (navigator) {
            params.lang = navigator.language || '';
        }
        var args = '';
        for (var i in params) {
            if (args != '') {
                args += '&';
            }
            args += i + '=' + encodeURIComponent(params[i]);
        }
        args += '&';
        args += "appkey" + '=' + encodeURIComponent($main.appkey);
        //args += "deviceName" + '=' + encodeURIComponent($main.appkey);
        args += '&';
        args += "activity" + '=' + encodeURIComponent($main.newWebUrl);
        args += '&';
        args += "start_millis" + '=' + encodeURIComponent($main.razorStart);
        args += '&';
        args += "session_id" + '=' + encodeURIComponent($main.getCookie("sessionId"));
        //args += "version" + '=' + encodeURIComponent($main.cookie);
        args += '&';
        args += "deviceid" + '=' + encodeURIComponent($main.cookie);
        if($main.razorEnd&&$main.duration){
            args += '&';
            args += "duration" + '=' + encodeURIComponent($main.duration);
            args += '&';
            args += "end_millis" + '=' + encodeURIComponent($main.razorEnd);
        } else {
            args += '&';
            args += "duration" + '=' + '';
            args += '&';
            args += "end_millis" + '=' + '';
        }
        args += '&';
        args += "userId" + '=' + encodeURIComponent($main._userId);
        //args += "sd" + '=' + encodeURIComponent($main.cookie);
        return args;
    };

    $main.initInterface = function(arrs){

        if(typeof accessNginxUrl !='undefined'){
            $main.nginxUrl = accessNginxUrl;
            $main.nginxCd = $main.nginxUrl+"webjscd?";
            $main.nginxEvent = $main.nginxUrl+"webjsevent?";
            $main.nginxUsingLog = $main.nginxUrl+"webjs?";
        } else {
            $main.nginxUrl = arrs[0];
            $main.nginxCd = $main.nginxUrl+"webjscd?";
            $main.nginxEvent = $main.nginxUrl+"webjsevent?";
            $main.nginxUsingLog = $main.nginxUrl+"webjs?";
        }

        if(typeof accessInnerUrl != 'undefined'){
            $main.accessInnerDomain = accessInnerUrl;
        } else {
            $main.accessInnerDomain = arrs[1];
        }
        if(typeof accessOuterUrl != 'undefined'){
            $main.accessOuterDomain = accessOuterUrl;
        } else {
            $main.accessOuterDomain = arrs[1]+"mobile-analysis/";
        }
        // 配置接口
        $main.pageSize = arrs[2];
        $main.getInfo = $main.accessInnerDomain+"mobile-analysis/getWebproductInfo.json";
        $main.getCss = $main.accessInnerDomain+'mobile-analysis-web/css/visualizer.css';
        $main.getAll = $main.accessOuterDomain+"getEventTrackList";
        $main.newWebUrl = windows.location.origin?windows.location.origin + windows.location.pathname:
        (windows.location.protocol+"//"+windows.location.host) + windows.location.pathname;

        // 设置DevicedId的cookie
        $main.setRazorDevicedId();

        // 获取页面停留时间
        $main.getRazorDuraction();

        // 解析_maq配置
        $main.getRazorAppkey();
        // 拼接参数串

        // 配置全局的参数uuId
        $main.setRazorUuid();

    };
    $main.sendNginxLog = function(){
        // 发送cd的nginx的日志
        if(!($main.getCookie("sessionId")&&$main.getCookie("sessionId")!='null')){
            var args = $main.getNginxArgs();
            args += '&';
            args += 'flag' + '=' + '1';
            args += '&';
            args += 'time' + '=' + new Date().getTime();
            var img3 = new Image(1, 1);
            img3.src = $main.nginxCd + args;
        }

        // 设置sessionId
        $main.setRazorSessionId();

        // 获取appkey的值
        $main.getRazorAppkey();
        // 获取所有的事件类型
        $main.getAllEvent();
        // 设置body监听器, 实现event日志发送监听
        $main.setListener();
        // 发送usinglog日志和封装event日志接口
        $main.nginxLogs();
    };
    // 获取所有事件信息
    $main.getAllEvent = function(){
        var event = [];
        $main.ajax({
            async: false,
            url : $main.getAll,
            jsonp: "callback",
            dataType : "jsonp",
            data: {'appkey':$main.appkey, 'activity':$main.newWebUrl},
            success : function(data){
                if(data&&data.reply&&data.reply.queryResult&&data.reply.flag==1){
                    $main.res = eval("("+data.reply.queryResult+")");
                    $main.each($main.res, function (idx, result) {
                        // event.putAndUpdate(result.md5_orign_path, result.md5_content);
                        event.push(result.activity + '_' + result.eventPaht);
                    });
                } else if(data&&data.reply&&data.reply.queryResult&&data.reply.flag==-4){
                    console.log('不采集数据');
                }
            },
            error:function(){
                console.log('fail');
            }
        });
    };
    /**
     * 发送nginx日志
     */
    $main.setListener = function(){

        if(document.body.attachEvent){
            document.body.attachEvent('onclick', function (e) {
                // 复位sessionId的值
                $main.resetRazorSessionId();

                $main.setCookie("start_millis", $main.getCookie("start_millis"), 5*60*1000);

                var target = e.target || e.srcElement;
                //console.log(target.tagName);
                var path = '';
                for (; target && target.nodeType == 1; target = target.parentNode) {
                    var count = 1;
                    for (var sib = target.previousSibling; sib; sib = sib.previousSibling) {
                        if (sib.nodeType == 1 && sib.tagName == target.tagName) count++
                    }
                    var idx = count;
                    var xname = target.tagName;
                    xname += '[' + idx + ']';
                    path = '/' + xname + path;
                }
                //console.log($main.res);
                if($main.res&&$main.res.length){
                    for(var index=0; index<$main.res.length; index++){
                        if($main.res[index].eventPath==path){
                            console.log("发送自定义事件！！");
                            //appkey/acc/activity/session_id/label/version/deviceid/time/event_identifier
                            windows.$An([1, $main.newWebUrl, $main.getCookie("sessionId"), '', '',
                                $main.getCookie("cmbcKeys"), $main.res[index].eventIdentifier], true);
                        }
                    }
                }
            }, false);
        } else {
            document.body.addEventListener('click', function (e) {
                // 复位sessionId的值
                $main.resetRazorSessionId();

                $main.setCookie("start_millis", $main.getCookie("start_millis"), 5*60*1000);

                var target = e.target || e.srcElement;
                //console.log(target.tagName);
                var path = '';
                for (; target && target.nodeType == 1; target = target.parentNode) {
                    var count = 1;
                    for (var sib = target.previousSibling; sib; sib = sib.previousSibling) {
                        if (sib.nodeType == 1 && sib.tagName == target.tagName) count++
                    }
                    var idx = count;
                    var xname = target.tagName;
                    xname += '[' + idx + ']';
                    path = '/' + xname + path;
                }
                //console.log($main.res);
                if($main.res&&$main.res.length){
                    for(var index=0; index<$main.res.length; index++){
                        if($main.res[index].eventPath==path){
                            console.log("发送自定义事件！！");
                            //appkey/acc/activity/session_id/label/version/deviceid/time/event_identifier
                            windows.$An([1, $main.newWebUrl, $main.getCookie("sessionId"), '', '',
                                $main.getCookie("cmbcKeys"), $main.res[index].eventIdentifier], true);
                        }
                    }
                }
            }, false);
        }
        //关闭浏览器触发的功能
        windows.onbeforeunload = function(){
            //getRazorDuraction();
            //nginxLogs();
            //$main.removeRazorCookie("start_millis");
        };
    };
    // 封装event日志接口和发送usinglog日志
    $main.nginxLogs = function(){
        // 发送事件数据
        windows.$An = function (arr, flag) {
            if(flag){
                var acc = arr[0];
                var activity = arr[1];
                var session_id = arr[2];
                var label = arr[3];
                var version = arr[4];
                var deviceid = arr[5];
                var time = new Date().getTime();
                var event_identifier = arr[6];
                var img2 = new Image(1, 1);
                var str = 'acc=' + encodeURI(acc) + '&activity=' + encodeURI(activity) +
                    '&session_id=' + encodeURI(session_id) + '&label=' + encodeURI(label)+
                    '&version=' + encodeURI(version)+ '&deviceid=' + encodeURI(deviceid)+
                    '&time=' + encodeURI(time)+'&event_identifier=' + encodeURI(event_identifier);
                //img2.src = $main.nginxUrl+str;
                if (_maq) {
                    for (var i in _maq) {
                        switch (_maq[i][0]) {
                            case '_setAccount':
                                str = str + '&appkey=' + encodeURI(_maq[i][1]);
                                break;
                            case '_userId':
                                str = str + '&userId=' + encodeURI(_maq[i][1]);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }else {

                var acc = 1;
                var activity = $main.newWebUrl;
                var session_id = $main.getCookie("");
                var deviceid = $main.getCookie("");
                var time = new Date().getTime();
                var event_identifier = arr[0];
                var version = '';
                //var category = arr[1];
                //var action = arr[2];
                var label = arr[3];
                //var opt_value = arr[4];
                var img2 = new Image(1, 1);
                var str = 'acc=' + encodeURI(acc) + '&activity=' + encodeURI(activity) +
                    '&session_id=' + encodeURI(session_id) + '&label=' + encodeURI(label)+
                    '&version=' + encodeURI(version)+ '&deviceid=' + encodeURI(deviceid)+
                    '&time=' + encodeURI(time)+'&event_identifier=' + encodeURI(event_identifier);

                //解析_maq配置
                if (_maq) {
                    for (var i in _maq) {
                        switch (_maq[i][0]) {
                            case '_setAccount':
                                str = str + '&account=' + encodeURI(_maq[i][1]);
                                break;
                            case '_userId':
                                str = str + '&userId=' + encodeURI(_maq[i][1]);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            img2.src = $main.nginxEvent + str;
        };
        // 获取网站的appkey
        $main.getRazorAppkey();

        // 获取nginx的浏览器args，并发送usinglog的日志
        var args = $main.getNginxArgs();
        args += '&usingFlag=2';
        var img = new Image(1, 1);
        img.src = $main.nginxUsingLog + args;
    };
    windows.$main = $main;
})(window);

function getRazorTimes(/*访问时间，单位ms*/startT, /*结束时间，单位ms*/ endT, /*是否需要日志*/ isPageChange){
    if(startT){
        $main.razorStart = startT;
    }
    if(endT) {
        $main.razorEnd = endT;
    }
    if(isPageChange){
        // 获取nginx的浏览器args，并发送usinglog的日志
        var args = $main.getNginxArgs();
        args += '&usingFlag=';
        var img = new Image(1, 1);
        img.src = $main.nginxUsingLog + args;
    }
}

function initMA(){

    // 初始化相关配置, 0 -- nginx   1 -- 请求的路径       3 -- 配置模式下的每页事件定义条数
    $main.initInterface(['http://192.168.1.202:8080/','http://192.168.1.202:18080/', 5]);

    //console.log($main.getRazorDomainUrl($main.newWebUrl));

    // 动态请求productId和jsPath
    $main.ajax({
        url : $main.getInfo,
        jsonp: "callback",
        dataType : "jsonp",
        jsonpCallback: 'success',
        data: {'appkey': $main.appkey, 'uuId': $main.sessionKey, 'newWebUrl':$main.getRazorDomainUrl($main.newWebUrl)},
        success : function(data){

            if(data&&data.reply&&data.reply.queryResult&&data.reply.flag==1){
                $main.sendNginxLog();
                // eval的风险
                var res = eval("("+data.reply.queryResult+")");
                $main.productId = res.productId;
                $main.jsPath = res.jsPath;
                if($main.jsPath!=null){
                    //console.log($main.jsPath);
                    var ma = document.createElement('script');
                    ma.type = 'text/javascript';
                    ma.async = true;
                    //ma.src = ('https:' == document.location.protocol ? 'https://' : 'http://')
                    //+ 'www.cobub.com/ma.js?' + new Date().getTime();
                    //ma.src = ('https:' == document.location.protocol ? 'https://' : 'http://') +
                    //'localhost:8080/mobile-analysis-web/jsweb/rQuery.js?' + new Date().getTime();
                    ma.src = $main.jsPath + "?" + new Date().getTime();
                    //ma.src = "./js/rQuery.js";
                    var s = document.getElementsByTagName('script')[0];
                    // 加入css样式表
                    var ln = document.createElement('link');
                    ln.rel = 'stylesheet';

                    //ma.src = ('https:' == document.location.protocol ? 'https://' : 'http://')
                    //+ 'www.cobub.com/ma.js?' + new Date().getTime();
                    ln.href = $main.getCss;
                    //ln.href = "./cibc/visualizer.min.css";
                    //ma.src = "./js/rQuery.js";
                    s.parentNode.insertBefore(ma, s);
                    s.parentNode.insertBefore(ln, s);
                }
            } else if(data&&data.reply&&data.reply.queryResult&&data.reply.flag==-4){
                console.log("不采集日志");
            } else {
                $main.sendNginxLog();
            }
        },
        error :function(){
            console.log("失败");
        }
    });
}
initMA();