'use strict';
(function(window, undefined){

    // 封装的对象，
    function ElementTags() {
        // 当前获取元素的对象
        this.arr = [];
        // 临时存放数据的对象
        this.tmp = [];
        // 暂存查询条件
        this.length = this.arr.length;
    }

    // 封装的原形方法
    ElementTags.prototype = {
        /**
         * function : 拿到直接子元素
         * args : String类型的类选择器（可选）
         * return : new ElementTags
         */
        children : function(conn){
            for(var i =0, len = this.arr.length; i<len; i++){
                if(this.arr[i].nodeType==1){
                    if(conn){
                        // 类选择器
                        var oElements = this.arr[i].childNodes;
                        for(var index=0; index < oElements.length; index++){
                            if(oElements[index].nodeType==1){
                                var cls = oElements[index].className.split(" ");
                                for(var i=0; i< cls.length;i++){
                                    if(cls[i]==conn.split(".")[1]){
                                        this.tmp.push(oElements[index]);
                                    }
                                }
                            }
                        }
                    } else {
                        var oElements = this.arr[i].childNodes;
                        for(var index=0; index < oElements.length; index++){
                            if(oElements[index].nodeType==1){
                                this.tmp.push(oElements[index]);
                            }
                        }
                    }
                }
            }
            var obj = new ElementTags();
            obj.arr = this.tmp;
            obj.length = this.tmp.length;
            this.tmp = [];
            return obj;
        },
        // 内部函数
        add : function(obj){
            this.arr.push(obj);
        },
        // 获取元素数组函数
        getArr: function(){
            return this.arr;
        },
        /**
         * function : 根据this.arr中元素拿到匹配的所有的后代元素
         * args : [string, object] 两种类型的参数
         * return : new ElementTags
         */
        find :function(conn){
            var classRegx = /^\..*/;
            // 类选择器
            if(typeof conn=='string'&&classRegx.test(conn)){
                for(var index=0, len = this.arr.length; index<len; index++){
                    var cls1 = this.arr[index].className.split(" ");
                    for(var t=0; t<cls1.length;t++)
                        if(cls1[t]==conn.split(".")[1]){
                            this.tmp.push(this.arr[index]);
                            break;
                        }
                    var allElements = this.arr[index].getElementsByTagName("*");
                    for(var i=0; i< allElements.length&&allElements[i].nodeType==1; i++){
                        if(allElements[i].className){
                            var cls2 = (allElements[i].className+"").split(" ");
                            for(var t=0; t<cls2.length;t++)
                                if(cls2[t]==conn.split(".")[1]){
                                    this.tmp.push(allElements[i]);
                                    break;
                                }
                        }
                    }
                }
            }
            else if(typeof conn == 'string' && /[?.]+\.*/.test(conn)){
                var nodeLists = document.querySelectorAll(conn);
                for(var j = 0, nlen = nodeLists.length; j < nlen; j++){
                    for(var index=0, len = this.arr.length; index<len; index++){
                        if(this.arr[index]==nodeLists[j]){
                            this.tmp.push(this.arr[index]);
                        }
                        var allElements = this.arr[index].getElementsByTagName("*");
                        for(var i=0; i< allElements.length; i++){
                            if(allElements[i] == nodeLists[j]){
                                this.tmp.push(allElements[i]);
                            }
                        }
                    }
                }
            }
            // 非类选择器
            else if(typeof conn == 'string' && !classRegx.test(conn)){
                for(var index=0, len = this.arr.length; index<len; index++){
                    if(this.arr[index].tagName.toLowerCase()==conn){
                        this.tmp.push(this.arr[index]);
                    }
                    var allElements = this.arr[index].getElementsByTagName("*");
                    for(var i=0; i< allElements.length; i++){
                        if(allElements[i].tagName.toLowerCase()==conn){
                            this.tmp.push(allElements[i]);
                        }
                    }
                }
            }
            // 传入的参数为对象
            else if(typeof conn == 'object'){
                for(var index=0, len = this.arr.length; index<len; index++){
                    if(this.arr[index]==conn){
                        this.tmp.push(this.arr[index]);
                    }
                    var allElements = this.arr[index].getElementsByTagName("*");
                    for(var i=0; i< allElements.length; i++){
                        if(allElements[i]==conn&&!this.arr.contains(conn)){
                            this.tmp.push(allElements[i]);
                        }
                    }
                }
            }
            var obj = new ElementTags();
            obj.arr = this.tmp;
            obj.length = this.tmp.length;
            this.tmp = [];
            return obj;
        },
        // 返回指定下标的某个元素
        get : function(number){
            return this.arr[number];
        },
        // 返回非指定类选择器的元素
        not : function(conn){
            for(var index = 0, len = this.arr.length ; index < len; index++){
                if(!((this.arr[index].className+"").indexOf(conn.split(".")[1])>-1)){
                    this.tmp.push(this.arr[index]);
                }
                /*var cls = (this.arr[index].className+"").split(" ");
                 var flag = false;
                 for(var i=0; i<cls.length; i++){
                 if((cls[i]==conn.split(".")[1])){
                 flag=true;
                 break;
                 }
                 }
                 if(!flag){
                 this.tmp.push(this.arr[index]);
                 }*/
            }
            var obj = new ElementTags();
            obj.arr = this.tmp;
            obj.length = this.tmp.length;
            this.tmp = [];
            return obj;
        },
        // 隐藏
        hide : function(){
            for(var index=0, len = this.arr.length; index<len; index++){
                this.arr[index].style.display = 'none';
            }
        },
        // 显示,显示样式与$main有点区别
        show : function(){
            for(var index=0, len = this.arr.length; index<len; index++){
                this.arr[index].style.display = 'block';
            }
        },
        // 删除元素集合的内容
        remove:function (){
            for(var index=0, len = this.arr.length; index<len; index++){
                this.arr[index].parentNode.removeChild(this.arr[index]);
            }
        },
        // 获取第0个元素的value,只能对form控件调用
        val : function(){
            if(this.arr[0]){
                return this.arr[0].value;
            }
        },
        // 获取值或者是赋值
        attrs :function(a, b, c){
            /*for(var index=0, len = this.arr.length; index<len; index++){
             if(a == 'class'){
             this.arr[index].className = this.arr[index].className + " " + b;
             continue;
             } else {
             for(var obj in this.arr[index]){
             if(obj==a){
             this.arr[index][obj] = b;
             }
             }
             }
             }*/
            if(!a) return;
            if(!!c){
                var _att =document.createAttribute(b);
                _att.nodeValue = c;
                a.setAttributeNode(_att);
                return ;
            }
            if(!!a.getAttribute(b)){
                var _val = a.getAttributeNode(b).nodeValue;
                return _val;
            }else {
                return null;
            }
        },
        // 事件绑定方法
        on : function(ev, fn){
            this.bindEv(ev, fn);
            var obj = new ElementTags();
            obj.arr = this.arr;
            obj.length = this.arr.length;
            return obj;
        },
        // 获取第一个元素的宽度
        width: function(){
            if(this.arr[0].nodeType == 9){
                return document.body.clientWidth;
            } else{
                // 谷歌浏览器不兼容
                var width = 0;
                var objStyle = this.arr[0].currentStyle?this.arr[0].currentStyle : window.getComputedStyle(this.arr[0], null);
                var resultMain = parseInt(objStyle['width'].split("px")[0]);
                return isNaN(resultMain)?this.arr[0].offsetWidth:resultMain;
            }
        },
        // 获取第一个元素的内高
        innerHeight :function(){
            if(this.arr[0]){
                // 谷歌浏览器不兼容
                var height = 0;
                var objStyle = this.arr[0].currentStyle?this.arr[0].currentStyle : window.getComputedStyle(this.arr[0], null);
                var resultMain = parseInt(objStyle['height'].split("px")[0])+parseInt(objStyle['padding-top']
                        .split("px")[0])+parseInt(objStyle['padding-bottom'].split("px")[0]);
                return isNaN(resultMain)?this.arr[0].offsetHeight:resultMain;
            }
        },
        // 获取对象的偏移
        offset : function(){
            if(this.arr[0]){
                // 谷歌浏览器不兼容
                var obj = {left:0,top:0};
                this.getOffset(this.arr[0], true, obj);
                return obj;
            }
        },
        getOffset : function(node,init, offset){
            // 递归获取 offset, 可以考虑使用 getBoundingClientRect
            // 非Element 终止递归
            if (node.nodeType !== 1) {
                return;
            }
            var _position = window.getComputedStyle(node)['position'];
            // position=static: 继续递归父节点
            if (typeof(init) === 'undefined' && _position === 'static') {
                this.getOffset(node.parentNode, undefined, offset);
                return;
            }
            offset.top = node.offsetTop + offset.top - node.scrollTop;
            offset.left = node.offsetLeft + offset.left - node.scrollLeft;
            // position = fixed: 获取值后退出递归
            if (_position === 'fixed') {
                return;
            }
            this.getOffset(node.parentNode, undefined, offset);
        },
        // 解除事件绑定
        unbind : function (ev, fn){
            this.unBindEv(ev, fn);
        },
        // 绑定事件
        bind : function(ev, fn){
            this.bindEv(ev, fn);
        },
        // 键盘按下事件,这个函数最好只对document使用
        keyup:function(callback){
            for(var index=0, len = this.arr.length; index<len; index++){
                this.arr[index].onkeyup = callback;
            }
        },
        // 获取所有元素指定标签名称的父标签，只能有小写字符串
        parents:function(conn){
            for(var index=0, len = this.arr.length; index<len; index++){
                if(this.arr[index].parentNode && this.arr[index].parentNode.nodeType!==11){
                    this.getAllParents(this.arr[index],this.tmp, conn);
                }else {
                    continue;
                }
            }
            var obj = new ElementTags();
            obj.arr = this.tmp;
            obj.length = this.tmp.length;
            this.tmp = [];
            return obj;
        },
        // 事件解除
        off : function(ev, fn){
            if(ev){
                this.unBindEv(ev, fn);
            } else {
                var events = ['click', 'mouseover', 'mouseout'];
                for(var i=0, elen = events.length; i<elen; i++){
                    var ev1 = events[i];
                    this.unBindEv(ev1, fn);
                }
            }
            var obj = new ElementTags();
            obj.arr = this.arr;
            obj.length = this.arr.length;
            return obj;
        },
        click : function(){
            for(var index = 0, len = this.arr.length; index<len; index++){
                if(document.all) {
                    this.arr[index].click();
                }
                // 其它浏览器
                else {
                    var e = document.createEvent("MouseEvents");
                    e.initEvent("click", true, true);　　　　　　　　　　　　　　//这里的click可以换成你想触发的行为
                    this.arr[index].dispatchEvent(e);　　　   //这里的clickME可以换成你想触发行为的DOM结点
                }
            }
        },
        siblings : function(){
            for(var index = 0, len = this.arr.length; index<len; index++){
                var children = this.arr[index].parentNode.childNodes;//获取目标元素的所有兄弟元素和它自身
                for (var i = 0 , elen = children.length; i < elen; i++) {
                    //判断此元素是一个元素节点而且不是调用元素的本身
                    //若为真放进数组里面
                    if (children[i].nodeType == 1 && children[i] != this.arr[index]) {
                        this.tmp.push(children[i]);
                    }
                }
            }
            //返回最后得出的结果数组
            var obj = new ElementTags();
            obj.arr = this.tmp;
            obj.length = this.tmp.length;
            this.tmp = [];
            return obj;
        },
        addClass : function(clazzName){
            for(var index = 0, len = this.arr.length; index < len; index++){
                this.arr[index].className = this.arr[index].className + " "+clazzName;
            }
            var obj = new ElementTags();
            obj.arr = this.arr;
            obj.length = this.arr.length;
            return obj;
        },
        removeClass : function(clazzName){
            for(var index = 0, len = this.arr.length; index < len; index++){
                this.arr[index].className = this.arr[index].className.replace(clazzName, "");
            }
        },
        data : function(attr){
            var result = this.arr[0].getAttribute("data-"+attr);
            return result;
        },
        html : function(obj){
            if(this.arr[0]){
                this.arr[0].innerHTML = obj;
            }
        },
        /*查询带条件的所有父级元素*/
        getAllParents : function (ele, results, conn){
            if(conn){
                if(ele.parentNode&&ele.parentNode.tagName){
                    if(ele.parentNode.tagName.toLowerCase()==conn&&!results.contains(ele.parentNode)){
                        results.push(ele.parentNode);
                    }
                    this.getAllParents(ele.parentNode, results, conn);
                }
            } else {
                if(ele.parentNode&&ele.parentNode.tagName&&!results.contains(ele.parentNode)){
                    results.push(ele.parentNode);
                    this.getAllParents(ele.parentNode, results);
                }
            }
        },
        getFuncName : function(fn){
            if(!fn)return null;    //如果没有传函数名,则返回空
            var reg = /\bfunction\s+([^(]+)/;    //正则匹配函数名
            var result = fn.toString().match(reg);   //通过正则表达式在函数转的字符串中得到数组
            return result ? result[1] : null; //取出第一个子项的结果 即为函数名 若没有找到
        },
        bindEv : function(ev, fn){
            for(var index=0, len = this.arr.length; index<len;index++){
                var obj = this.arr[index];
                var fnName = this.getFuncName(fn) || Math.random()+''+Math.random(); //如果函数名不存在的话 则取两次随机数(避免重复)作为函数存入的key值
                var func = function(){        //将函数专为匿名函数并改变this指向 低版本ie下的必然操作  标准下如此操作没有副作用
                    fn.apply(obj,arguments);
                }
                obj.funByEv = obj.funByEv || {};
                obj.funByEv[ev] = obj.funByEv[ev] || [];
                var _json = {};             //新建的json
                _json[fnName] = func;       //key:传进来的函数的名字  value:传进来的函数转变的匿名函数
                obj.funByEv[ev].push( _json );        //将json push到数组里
                if(obj.addEventListener){
                    obj.addEventListener(ev,func,false);
                }else{
                    obj.attachEvent('on'+ev,func);
                }
            }
        },
        unBindEv : function(ev, fn){
            for(var index=0, lens = this.arr.length; index<lens;index++){
                var obj = this.arr[index];
                var fnName = this.getFuncName(fn);     //函数名   getFuncName()内部处理 如果传匿名函数或没有传函数 则返回null 否则返回函数名
                var iBtn = false;                 //用来结束数组循环查找的开关
                var len;
                if(obj.funByEv&&obj.funByEv[ev]!=undefined){
                    len = obj.funByEv[ev].length;
                }
                if(fn&&fnName){                   //如果传进来数组 且数组有名字  则走if语句
                    for(var i=0;i<len;i++){         //循环  解除绑定对象的属性下对应事件的数组
                        for(var j in obj.funByEv[ev][i] ){             //在数组每项中 用json的key与解除的函数匹配
                            if (j == fnName){                          //匹配成功 则删除对应的函数
                                obj.removeEventListener ? obj.removeEventListener(ev,obj.funByEv[ev][i][fnName],false)
                                    : obj.dettachEvent('on'+ev,obj.funByEv[ev][i][fnName]);
                                iBtn = true;                           //删除后 则可以结束循环
                            }
                        }
                        if(iBtn)break;      //非常重要  同一个函数绑定给同个对象多次,这里认为解除哪个都一样(也许是有区别的,)  所以解除掉一个后,就退出数组循环
                    }
                }else{            //如果没有传函数,或者传入的是匿名函数  对不起 干掉所有绑定的
                    for(var i=0;i<len;i++){       //同if操作.只是不用去匹配  json的key和需要解除的函数的名字
                        if(obj.funByEv[ev]&&obj.funByEv[ev][i]){
                            for(var k in obj.funByEv[ev][i]){                //原因? 干掉每一个  当然不用去匹配了
                                obj.removeEventListener ? obj.removeEventListener(ev,obj.funByEv[ev][i][k],false)
                                    : obj.detachEvent('on'+ev,obj.funByEv[ev][i][k]);
                            }
                        }
                    }
                }
            }
        }
    };
    // 事件分页方法
    $main.changePage = function(number){
        var $body = $main.$('body');
        $main.nowPage = $main.nowPage + number;
        if($main.nowPage>=$main.totalPage){
            $main.nowPage = $main.totalPage;
        }
        if($main.nowPage<=0){
            $main.nowPage = 1;
        }
        $body.find(".razor-navbar_1").remove();
        var innerhtml='<table width="150px"><tr><td width="25px">名称</td>'+
            '<td width="25px">操作</td></tr></li>';
        $main.each($main.res, function (idx, result) {
            if(idx>=($main.nowPage-1)*$main.pageSize&&idx<$main.nowPage*$main.pageSize) {
                innerhtml+='<tr class="showfordiv"><td>'+result.eventName+'<div style="display:none;">{'
                    +result.eventPath+'}</div></td>'+
                    '<td class="delline" id="index_'+result.eventId+'">删除</td></tr>';
            }
        });
        innerhtml+='</table><div><a href="javascript:void(0);" onclick="$main.changePage(-1)">上一页</a>'+
            '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="$main.changePage(1)">下一页</a></div>';
        // 修改
        $main.createElement('<div class="razor-navbar_1" style="left:10px;top:70px;position: absolute;">'
            +innerhtml+'</div>').appendTo($body.get(0));
        $body.find('.razor-navbar_1').find('.showfordiv').on('click', function (tar) {
            var h=tar.currentTarget.innerHTML;
            var ind=tar.currentTarget.innerHTML.indexOf('{')
            var ine=tar.currentTarget.innerHTML.indexOf('}')
            var str=h.substring(ind+1,ine);
            var nodes=document.evaluate(str, document, null, XPathResult.ANY_TYPE, null).iterateNext();
            $body.find(nodes).show();

            var width = $main.$(nodes).width();
            var height = $main.$(nodes).innerHeight();
            var offset = $main.$(nodes).offset();
            var hjson = {};
            hjson["width"] = width;
            hjson["height"] = height;
            hjson["left"] = offset.left;
            hjson["top"] = offset.top;

            $body.find('.razor-outline-shade').remove();
            $body.find('.razor-outline').remove();
            $body.find('.razor-tooltip').remove();

            // 修改
            $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                + ' !important; top: ' + hjson.top + 'px' + '!important; width: ' + hjson.width + 'px'
                + ' !important; max-width: ' + hjson.width + 'px' + ' !important; height: 2px'
                +' !important; max-height: 2px !important; white-space: pre-wrap !important;'
                +' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
            $main.createElement('<div class="razor-outline" style="left: ' + (hjson.left + hjson.width)
                + 'px' + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px'
                +' !important; max-width: ' + hjson.width + 'px' + ' !important; height: '
                + hjson.height + 'px' + ' !important; max-height: ' + hjson.height + 'px'
                + ' !important; white-space: pre-wrap !important; word-wrap: break-word'
                +' !important;"></div>').appendTo($body.get(0));
            $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                + ' !important; top: ' + (hjson.top + hjson.height) + 'px' + '!important; width: '
                + hjson.width + 'px' + ' !important; max-width: ' + hjson.width + 'px'
                + ' !important; height: 2px !important; max-height: 2px !important;'
                +' white-space: pre-wrap !important; word-wrap: break-word !important;">'
                +'</div>').appendTo($body.get(0));
            $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px !important;'
                +' max-width: ' + hjson.width + 'px' + ' !important; height: ' + hjson.height
                + 'px' + ' !important; max-height: ' + hjson.height + 'px' + ' !important;'
                +' white-space: pre-wrap !important; word-wrap: break-word !important;">'
                +'</div>').appendTo($body.get(0));
        });
        $body.find('.razor-navbar_1').find('.showfordiv').find('.delline').on('click', function (tar) {
            var element1 = tar.toElement || tar.target;
            var inx =element1.id.split("_")[1];
            //console.log(inx);
            $main.$(element1).parents("tr").remove();

            $main.ajax({
                async: false,
                url : $main.delUrl,
                jsonp: "callback",
                dataType : "jsonp",
                data: {'eventId': inx},
                success : function(data){
                    alert("删除成功");
                },
                error:function(){
                    console.log('fail');
                }
            });
        });
    };
    /**
     * 对象构造函数
     * @param {Object} a
     */
    $main.$ = function(a){
        if(typeof a == 'string'){
            var nodeList = document.querySelectorAll(a);
            var index = 0;
            var eleTags = new ElementTags();
            for(;index<nodeList.length; index++){
                if(nodeList[index].nodeType==1){
                    eleTags.add(nodeList[index]);
                }
            }
            return eleTags;
        } else if(typeof a  == 'object'){
            var eleTags = new ElementTags();
            eleTags.add(a);
            return eleTags;
        }
    }


    $main.postVDUrl = $main.accessInnerDomain+"mobile-analysis/addWebEventTrack.json";
    $main.delUrl = $main.accessInnerDomain+"mobile-analysis/deleteEventTrack.json";
    $main.bindEvent = function (mode) {
        // $('')
        var $body = $main.$('body');
        if (mode) {
            var obj1 = $body.children();
            var obj2 = obj1.not('.razor-navbar');
            var obj3 = obj2.not('.razor-navbar_1');
            $body.children().not('.razor-navbar').not('.razor-navbar_1').on('mouseover', function (event) {

                var target = event.target || window.event.target;
                var getFlag = false;
                if(target.tagName.toUpperCase()=='A'
                    ||target.tagName.toUpperCase()=='BUTTON'||target.tagName.toUpperCase()=='INPUT'
                        /*||target.tagName.toUpperCase()=='LI'*/
                    ||target.tagName.toUpperCase()=='TEXTAREA'||target.tagName.toUpperCase()=='SELECT'){
                    getFlag = true;
                }
                if ($body.find('.razor-tooltip').length < 1&&getFlag) {
                    event.stopPropagation();
                    event.preventDefault();

                    if(typeof jQuery != 'undefined' || typeof $ != 'undefined') {
                        var razorFun = jQuery || $;
                        razorFun(event.target).off("click");
                    }

                    if(event.target&&event.target.onclick){
                        event.target.onclick = null;
                    }

                    var width = $main.$(target).width();

                    var height = $main.$(target).innerHeight();

                    var offset = $main.$(target).offset();
                    var offjson = {};
                    offjson["width"] = width;
                    offjson["height"] = height;
                    offjson["left"] = offset.left;
                    offjson["top"] = offset.top;
                    $body.find('.razor-outline').remove();

                    // 修改
                    $main.createElement('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' +
                        offset.top + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' +
                        ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important;'+
                        ' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + (offset.left + width) + 'px' + ' !important; top: ' +
                        offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' +
                        ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' +
                        ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' +
                        (offset.top + height) + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px'
                        + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important;'+
                        ' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' +
                        offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' + ' !important; height: ' +
                        height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; '+
                        'word-wrap: break-word !important;"></div>').appendTo($body.get(0));

                    // 无法阻止addEventListener的事件
                    target.parentNode.addEventListener('click', function(tar){
                        //var target = tar.target;
                        tar.stopPropagation();
                        tar.preventDefault();
                        $body.find('.razor-outline-shade').remove();
                        $body.find('.razor-outline').remove();
                        $body.find('.razor-tooltip').remove();
                        // 修改
                        $main.createElement('<div class="razor-outline-shade" style="left: ' + offset.left + 'px' +
                            ' !important; top: ' + offset.top + 'px' + ' !important; width: ' + width + 'px' +
                            ' !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' +
                            ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important;'+
                            ' word-wrap: break-word !important;"></div>').appendTo($body.get(0));

                        var vol = target==undefined?undefined:$main.$(target).getArr();
                        $main.each(vol, function (id, key) {
                            $main.$(key).unbind('click');
                            var width1 = $main.$(key).width();
                            var height1 = $main.$(key).innerHeight();
                            var offset1 = $main.$(key).offset();
                            $main.createElement('<div class="razor-outline-shade" style="left: ' + offset1.left +
                                'px' + ' !important; top: ' + offset1.top + 'px' + ' !important; width: ' + width1 + 'px' +
                                ' !important; max-width: ' + width1 + 'px' + ' !important; height: ' + height1 + 'px' +
                                ' !important; max-height: ' + height1 + 'px' + ' !important; white-space: pre-wrap !important;'+
                                ' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                        });

                        var left = 0;
                        var frame_right = $main.$(document).width();
                        if ((offset.left + width + 326) >= frame_right) {
                            left = frame_right - 326 - width - 24;
                            if ((offset.left - 326) > 0 && (offset.left - 326 <= left)) {
                                left = offset.left - 326;
                            } else {
                                left = offset.left + 24;
                            }
                        } else {
                            left = offset.left + width + 24;
                        }

                        // 修改
                        $main.createElement('<div class="razor-tooltip" style="left: ' + left + 'px; top: ' + (offset.top + 2) +
                            'px; width: 260px !important; position: relative;"><div class="razor-tooltip-header">'+
                            '<div class="razor-tooltip-close"><svg width="10" height="10" viewBox="0 0 10 10" xmlns="http://www.w3.org/2000/svg">'+
                            '<title>关闭</title><path d="M4.706 6.64l3.046 3.119c.306.315.504.318.816 0l.61-.626c.3-.307.32-.508 0-.835L5.955'+
                            ' 4.996l3.225-3.3c.302-.31.308-.52 0-.836L8.568.234c-.317-.325-.514-.31-.816 0L4.706 3.353 1.66.234c-.302-.31-.498-.325-.815'+
                            ' 0L.233.86c-.308.315-.303.524 0 .835l3.225 3.301L.233 8.297c-.32.328-.302.529 0 .835l.612.626c.308.318.507.315.815'+
                            ' 0L4.706 6.64z" fill="#000" fill-rule="evenodd"></path></svg></div><div class="razor-tooltip-drag-handle">'+
                            '<svg width="10" height="10" viewBox="0 0 10 10" xmlns="http://www.w3.org/2000/svg"><title>drag</title>'+
                            '<path d="M1.76-.01H.59C.26-.01 0 .25 0 .58v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V.58a.6.6 0 0 0-.6-.59zm0'+
                            ' 3.82H.59c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V4.4a.607.607 0 0 0-.6-.59zm0 3.83H.59c-.32'+
                            ' 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V8.23a.6.6 0 0 0-.6-.59zM5.59-.01H4.41c-.32 0-.59.26-.59.59v1.18c0'+
                            ' .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V.58c0-.33-.27-.59-.59-.59zm0 3.82H4.41c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32'+
                            ' 0 .59-.26.59-.59V4.4c0-.32-.27-.59-.59-.59zm0 3.83H4.41c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0'+
                            ' .59-.26.59-.59V8.23c0-.33-.27-.59-.59-.59zM9.41-.01H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0'+
                            ' .59-.26.59-.59V.58a.6.6 0 0 0-.6-.59zm0 3.82H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V4.4a.607.607'+
                            ' 0 0 0-.6-.59zm0 3.83H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V8.23a.6.6 0 0 0-.6-.59z" fill="#000"'+
                            ' fill-rule="evenodd"></path></svg></div><div class="razor-tooltip-header-title">创建触发事件条件</div>'+
                            '<div class="razor-tooltip-section razor-tooltip-error"></div><div class="razor-sliding-views">'+
                            '<div class="razor-tooltip-event-definition-region" style="display: table-cell;"><div class="razor-tooltip-section razor-tooltip-name">'+
                            '<div class="razor-tooltip-label">名称</div><input type="text" class="razor-tooltip-input-text event-cn" placeholder="如：事件-A"></div>'+
                            '<div class="razor-tooltip-section razor-tooltip-limit-page"></div><div class="razor-tooltip-section razor-tooltip-include-similar"></div>'+
                            '<div class="razor-tooltip-margin-uncollapser"></div><div class="razor-tooltip-separator razor-tooltip-properties-separator"></div>'+
                            '<div class="razor-tooltip-properties-listing-region"><div></div></div><div class="razor-tooltip-btn-group">'+
                            '<button class="razor-tooltip-btn razor-tooltip-define">确定</button></div></div>'+
                            '<div class="razor-tooltip-custom-properties-region" style="display: table-cell; opacity: 0;">'+
                            '<div><div class="razor-tooltip-full-width-button razor-tooltip-show-event-definition"><div class="razor-tooltip-full-width-button-body">'+
                            '<i class="razor-icon razor-icon-normal razor-icon-chevronleft"></i>返回创建</div></div><div class="razor-tooltip-section razor-tooltip-property-type">'+
                            '<div class="razor-tooltip-label">Type</div><div class="razor-tooltip-property-type-region"><select><option value="" disabled="" selected="">添加来自...</option>'+
                            '<option value="selector">元素中的文本</option><option value="javascript">value of JavaScript</option></select></div></div>'+
                            '<div class="razor-tooltip-btn-group"><button class="razor-tooltip-btn razor-tooltip-define razor-tooltip-create-custom-property">自定义属性</button>'+
                            '</div></div></div></div></div>').appendTo($body.get(0));

                        $main.$('.razor-tooltip-close').on('click', function () {
                            $body.find('.razor-tooltip').remove();
                            $body.find('.razor-outline-shade').remove();
                        });
                        $main.$(document).keyup(function (e) {

                            if (e.keyCode == 27) {
                                $body.find('.razor-tooltip').remove();
                                $body.find('.razor-outline-shade').remove();
                                $body.find('.css-ceshi').remove();
                            }
                        });

                        var tarMsg = $main.queryElement(tar);
                        var target = tar.target || tar.srcElement;
                        var path = '';
                        for (; target && target.nodeType == 1; target = target.parentNode) {
                            var count = 1;
                            for (var sib = target.previousSibling; sib; sib = sib.previousSibling) {
                                if (sib.nodeType == 1 && sib.tagName == target.tagName) count++;
                            }
                            var idx = count;
                            var xname = target.tagName;
                            xname += '[' + idx + ']';
                            path = '/' + xname + path;
                        }
                        var nodes=document.evaluate(path, document,null, XPathResult.ANY_TYPE, null).iterateNext();
                        var $saveRegion = $main.$('.razor-tooltip').find('.razor-tooltip-header')
                            .find('.razor-sliding-views').find('.razor-tooltip-event-definition-region');
                        $saveRegion.find('.razor-tooltip-btn-group').find('button').bind('click', function (e) {
                            // console.log($body.find('.razor-navbar_1')[0].innerHTML);
                            //var eventEn = $saveRegion.find('.event-en').val();
                            var eventCn = $saveRegion.find('.event-cn').val();

                            if('' == eventCn || null == eventCn) {
                                return;
                            }
                            $main.ajax({
                                url : $main.postVDUrl,
                                jsonp: "callback",
                                dataType : "jsonp",
                                // jsonpCallback: 'jsonpCallback',
                                data: {'xPath': path, 'eventName': eventCn, 'productId': $main.productId,
                                    'appkey':$main.appkey, 'version': $main.version,'activity':$main.newWebUrl},
                                success : function(data){
                                    //==========================
                                    $saveRegion.find('div.razor-tooltip-section.razor-tooltip-include-similar').html('事件定义成功！');

                                    setTimeout(function () {
                                        $body.find('.razor-tooltip').remove();
                                        $body.find('.razor-outline-shade').remove();
                                    }, 1000);
                                    $body.find(".razor-navbar_1").remove();
                                    var innerhtml='<table width="150px"><tr><td width="25px">名称</td>'+
                                        '<td width="25px">操作</td></tr></li>';

                                    $main.ajax({
                                        url : $main.getAll,
                                        jsonp: "callback",
                                        dataType : "jsonp",
                                        // jsonpCallback: 'jsonpCallback',
                                        data: {'appkey':$main.appkey, 'activity':$main.newWebUrl},
                                        success : function(data){
                                            // console.log(data);
                                            if(data&&data.reply&&data.reply.queryResult){
                                                var res = eval("("+data.reply.queryResult+")");
                                                $main.res = res;
                                                $main.totalPage = $main.res.length/$main.pageSize==0?$main.res.length/$main.pageSize:parseInt($main.res.length/$main.pageSize)+1;
                                                $main.each(res, function (idx, result) {
                                                    if(idx>=($main.nowPage-1)*$main.pageSize&&idx<$main.nowPage*$main.pageSize) {
                                                        innerhtml+='<tr class="showfordiv"><td>'+result.eventName+'<div style="display:none;">{'
                                                            +result.eventPath+'}</div></td>'+ '<td class="delline" id="index_'+result.eventId+'">删除</td></tr>';
                                                    }
                                                });
                                            }
                                            innerhtml+='</table><div><a href="javascript:void(0);" onclick="$main.changePage(-1)">上一页</a>'+
                                                '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="$main.changePage(1)">下一页</a></div>';
                                            // 修改
                                            $main.createElement('<div class="razor-navbar_1" style="left:10px;top:70px;position: absolute;">'
                                                +innerhtml+'</div>').appendTo($body.get(0));
                                            $body.find('.razor-navbar_1').find('.showfordiv').on('click', function (tar) {
                                                var h=tar.currentTarget.innerHTML;
                                                var ind=tar.currentTarget.innerHTML.indexOf('{')
                                                var ine=tar.currentTarget.innerHTML.indexOf('}')
                                                var str=h.substring(ind+1,ine);
                                                var nodes=document.evaluate(str, document, null, XPathResult.ANY_TYPE, null).iterateNext();
                                                $body.find(nodes).show();

                                                var width = $main.$(nodes).width();
                                                var height = $main.$(nodes).innerHeight();
                                                var offset = $main.$(nodes).offset();
                                                var hjson = {};
                                                hjson["width"] = width;
                                                hjson["height"] = height;
                                                hjson["left"] = offset.left;
                                                hjson["top"] = offset.top;

                                                $body.find('.razor-outline-shade').remove();
                                                $body.find('.razor-outline').remove();
                                                $body.find('.razor-tooltip').remove();

                                                // 修改
                                                $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                                                    + ' !important; top: ' + hjson.top + 'px' + '!important; width: ' + hjson.width + 'px'
                                                    + ' !important; max-width: ' + hjson.width + 'px' + ' !important; height: 2px'
                                                    +' !important; max-height: 2px !important; white-space: pre-wrap !important;'
                                                    +' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                                                $main.createElement('<div class="razor-outline" style="left: ' + (hjson.left + hjson.width)
                                                    + 'px' + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px'
                                                    +' !important; max-width: ' + hjson.width + 'px' + ' !important; height: '
                                                    + hjson.height + 'px' + ' !important; max-height: ' + hjson.height + 'px'
                                                    + ' !important; white-space: pre-wrap !important; word-wrap: break-word'
                                                    +' !important;"></div>').appendTo($body.get(0));
                                                $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                                                    + ' !important; top: ' + (hjson.top + hjson.height) + 'px' + '!important; width: '
                                                    + hjson.width + 'px' + ' !important; max-width: ' + hjson.width + 'px'
                                                    + ' !important; height: 2px !important; max-height: 2px !important;'
                                                    +' white-space: pre-wrap !important; word-wrap: break-word !important;">'
                                                    +'</div>').appendTo($body.get(0));
                                                $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                                                    + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px !important;'
                                                    +' max-width: ' + hjson.width + 'px' + ' !important; height: ' + hjson.height
                                                    + 'px' + ' !important; max-height: ' + hjson.height + 'px' + ' !important;'
                                                    +' white-space: pre-wrap !important; word-wrap: break-word !important;">'
                                                    +'</div>').appendTo($body.get(0));
                                            });
                                            $body.find('.razor-navbar_1').find('.showfordiv').find('.delline').on('click', function (tar) {
                                                var element1 = tar.toElement || tar.target;
                                                var inx =element1.id.split("_")[1];
                                                //console.log(inx);
                                                $main.$(element1).parents("tr").remove();

                                                $main.ajax({
                                                    async: false,
                                                    url : $main.delUrl,
                                                    jsonp: "callback",
                                                    dataType : "jsonp",
                                                    data: {'eventId': inx},
                                                    success : function(data){
                                                        alert("删除成功");
                                                    },
                                                    error:function(){
                                                        console.log('fail');
                                                    }
                                                });
                                            });
                                        },
                                        error: function (e) {
                                            console.log('error');
                                        }
                                    });
                                },
                                error: function (e) {
                                    console.log('error');
                                }
                            });
                        })

                        tar.stopPropagation();
                        return false;
                    }, true);

                } else {
                    event.preventDefault();
                    event.stopPropagation();
                    $main.$(target).on('click', function (tar) {
                        tar.stopPropagation();
                        tar.preventDefault();
                    })
                }
            }).on('mouseout', function (event) {
                var uu = $body.find('.razor-outline');
                uu.remove();
            });
        } else {

            $body.children().not('.razor-navbar').off().bind('mouseover', function (event) {
                var objs1 = $body.children().not('.razor-navbar');
                var target = event.target || window.event.target;
                $main.$(target).off('click');
                var parentsEle=[];
                $main.$(target).getAllParents(target, parentsEle);
                for(var index=0, len=parentsEle.length; index < len; index++){
                    $main.$(parentsEle[index]).off('click');
                }
            });
            $body.find('.razor-tooltip').remove();

            $body.find('.razor-outline-shade').remove();
        }
    };

    // 先加载这个
    function initRQuery(){
        var $body = $main.$('body');
        var mode = true;
        var innerhtml='<table width="150px"><tr><td width="25px">名称</td><td width="25px">操作</td></tr></li>';
        var st = (new Date()).valueOf();
        $main.ajax({
            url : $main.getAll,
            jsonp: "callback",
            dataType : "jsonp",
            data: {'appkey':$main.appkey, 'activity':$main.newWebUrl},
            success : function(data){
                // console.log(data);

                // 拼接对应的数据表格
                if(data&&data.reply&&data.reply.queryResult){
                    var res = eval("("+data.reply.queryResult+")");
                    $main.res = res;
                    $main.totalPage = $main.res.length/$main.pageSize==0?$main.res.length/$main.pageSize:parseInt($main.res.length/$main.pageSize)+1;
                    $main.each(res, function (idx, result) {
                        if(idx>=($main.nowPage-1)*$main.pageSize&&idx<$main.nowPage*$main.pageSize) {
                            innerhtml+='<tr class="showfordiv"><td style="cursor: pointer;"> '+result.eventName
                                +'<div style="display:none;">{'+result.eventPath+'}</div>'
                                +'<td class="delline" style="cursor: pointer;" id="index_'
                                +result.eventId+'">删除</td></tr>';
                        }
                    });
                }
                // 关闭表格结束标签
                innerhtml+='</table><div><a href="javascript:void(0);" onclick="$main.changePage(-1)">上一页</a>'+
                    '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="$main.changePage(1)">下一页</a></div>';

                // 修改
                $main.createElement('<div class="razor-navbar_1" style="left:10px;top:70px;position: absolute;">'
                    +innerhtml+'</div>').appendTo($body.get(0));
                if($main.getCookie("razor_normal")=='true'){
                    $body.find('.razor-navbar_1').hide();
                }
                if($main.getCookie("razor_out")=='1'){
                    $main.setCookie("razor_out", '2');
                    $body.find('.razor-navbar_1').hide();
                    $main.getAllEvent();
                    //$main.$(result).click();
                    $body.children('.razor-navbar').remove();
                    $body.attrs($body.get(0), 'style', "user-select: none !important; margin-top: 0px !important; overflow-y: visible !important");
                    $main.bindEvent(false);
                } else if($main.getCookie('razor_out') == '2'){
                    $main.removeRazorCookie('razor_out')
                }
                $body.find('.razor-navbar_1').find('.showfordiv').on('click', function (tar) {
                    var st = (new Date()).valueOf();
                    var h=tar.currentTarget.innerHTML;
                    //console.log(h);
                    var ind=tar.currentTarget.innerHTML.indexOf('{')
                    var ine=tar.currentTarget.innerHTML.indexOf('}')
                    var str=h.substring(ind+1,ine);

                    var nodes=document.evaluate(str, document, null, XPathResult.ANY_TYPE, null).iterateNext();

                    $body.find(nodes).show();

                    var width = $main.$(nodes).width();
                    var height = $main.$(nodes).innerHeight();
                    var offset = $main.$(nodes).offset();
                    var hjson = {};
                    hjson["width"] = width;
                    hjson["height"] = height;
                    hjson["left"] = offset.left;
                    hjson["top"] = offset.top;

                    $body.find('.razor-outline-shade').remove();
                    $body.find('.razor-outline').remove();
                    $body.find('.razor-tooltip').remove();
                    // 修改
                    $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px' + ' !important; top: '
                        + hjson.top + 'px' + '!important; width: ' + hjson.width + 'px' + ' !important; max-width: '
                        + hjson.width + 'px' + ' !important; height: 2px !important; max-height: 2px !important;'
                        +' white-space: pre-wrap !important; word-wrap: break-word !important;">'
                        +'</div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + (hjson.left + hjson.width)
                        + 'px' + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px !important; max-width: '
                        + hjson.width + 'px' + ' !important; height: ' + hjson.height + 'px' + ' !important; max-height: '
                        + hjson.height + 'px' + ' !important; white-space: pre-wrap !important;'
                        +' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                        + ' !important; top: ' + (hjson.top + hjson.height) + 'px' + '!important; width: '
                        + hjson.width + 'px' + ' !important; max-width: ' + hjson.width + 'px' + ' !important;'
                        +' height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important;'
                        +' word-wrap: break-word !important;"></div>').appendTo($body.get(0));
                    $main.createElement('<div class="razor-outline" style="left: ' + hjson.left + 'px'
                        + ' !important; top: ' + hjson.top + 'px' + '!important; width: 2px !important; max-width: '
                        + hjson.width + 'px' + ' !important; height: ' + hjson.height + 'px' + ' !important; max-height: '
                        + hjson.height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word'
                        +' !important;"></div>').appendTo($body.get(0));

                    var ed = (new Date()).valueOf();
                    console.log("xpath获取控件时间"+(ed-st)+"毫秒");
                });
                $body.find('.razor-navbar_1').find('.showfordiv').find('.delline').on('click', function (tar) {
                    var element1 = tar.toElement || tar.target;
                    var inx =element1.id.split("_")[1];
                    //console.log(inx);
                    $main.$(element1).parents("tr").remove();

                    $main.ajax({
                        async: false,
                        url : $main.delUrl,
                        jsonp: "callback",
                        dataType : "jsonp",
                        data: {'eventId': inx},
                        success : function(data){
                            alert("删除成功");
                        },
                        error:function(){
                            console.log('fail');
                        }
                    });
                });
            },
            error: function (e) {
                console.log('error');
            }
        });



        // console.log(innerhtml);
        // 修改
        $main.createElement('<div class="razor-navbar"><div class="razor-navbar-inner"><div class="razor-mode-control">'
            +'<div class="razor-mode-switch"><a class="razor-mode razor-mode-active" data-mode="definition" href="#">定义模式</a>'
            +'<a id="tnor" class="razor-mode" data-mode="normal" href="#">正常模式</a></div></div><div class="razor-ved-controls">'
            +'<div class="razor-dropdown"><a class="razor-dropdown-label razor-exit-link" href="#">'
            +'<i class="razor-icon razor-icon-logout"></i>退出</a></div></div><span class="razor-ved-message"></span>'
            +'</div></div>').appendTo($body.get(0));
        $body.attrs($body.get(0), 'style', "user-select: none !important; margin-top: 51px !important; overflow-y: visible !important");
        if($main.getCookie("razor_normal")=='true'){
            $main.$("#tnor").addClass('razor-mode-active').siblings().removeClass('razor-mode-active');
            $main.getAllEvent();
            $body.attrs($body.get(0), 'style', "user-select: initial !important; margin-top: 51px !important; overflow-y: visible !important");
            $main.bindEvent(false);
        } else {
            $main.bindEvent(true);
        }
        var $swith = $body.children('.razor-navbar').find('.razor-navbar-inner').find('.razor-mode-control')
            .find('.razor-mode-switch').find('a.razor-mode');
        $swith.on('click', function (tar) {
            $main.$(tar.target).addClass('razor-mode-active').siblings().removeClass('razor-mode-active');
            if ($main.$(tar.target).data('mode') == 'definition') {
                $main.removeRazorCookie("razor_normal");
                $body.attrs($body.get(0), 'style', "user-select: none !important; margin-top: 51px !important; overflow-y: visible !important");
                $body.find('.razor-navbar_1').show();
                $main.bindEvent(true);
            } else if ($main.$(tar.target).data('mode') == 'normal') {
                $main.setCookie("razor_normal", 'true');
                window.location.reload();
                $main.getAllEvent();
                $body.find('.razor-navbar_1').hide();
                $body.attrs($body.get(0), 'style', "user-select: initial !important; margin-top: 51px !important; overflow-y: visible !important");
                $main.bindEvent(false);
            }
        });
        $body.children('.razor-navbar').find('.razor-navbar-inner').find('.razor-ved-controls').find('.razor-dropdown')
            .find('a.razor-dropdown-label.razor-exit-link').on('click', function () {
            $main.setCookie("razor_out", "1");
            window.location.reload();
        });
        var ed = (new Date()).valueOf();
        console.log("拉数据时间:"+(ed-st)+"毫秒");
    }

    initRQuery();

})(window);
