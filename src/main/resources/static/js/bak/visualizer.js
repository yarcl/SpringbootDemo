(function ($) {
    'use strict';

    /*
     * Add integers, wrapping at 2^32. This uses 16-bit operations internally
     * to work around bugs in some JS interpreters.
     */
    function safe_add(x, y) {
        var lsw = (x & 0xFFFF) + (y & 0xFFFF),
            msw = (x >> 16) + (y >> 16) + (lsw >> 16);
        return (msw << 16) | (lsw & 0xFFFF);
    }

    /*
     * Bitwise rotate a 32-bit number to the left.
     */
    function bit_rol(num, cnt) {
        return (num << cnt) | (num >>> (32 - cnt));
    }

    /*
     * These functions implement the four basic operations the algorithm uses.
     */
    function md5_cmn(q, a, b, x, s, t) {
        return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
    }

    function md5_ff(a, b, c, d, x, s, t) {
        return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
    }

    function md5_gg(a, b, c, d, x, s, t) {
        return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
    }

    function md5_hh(a, b, c, d, x, s, t) {
        return md5_cmn(b ^ c ^ d, a, b, x, s, t);
    }

    function md5_ii(a, b, c, d, x, s, t) {
        return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
    }

    /*
     * Calculate the MD5 of an array of little-endian words, and a bit length.
     */
    function binl_md5(x, len) {
        /* append padding */
        x[len >> 5] |= 0x80 << ((len) % 32);
        x[(((len + 64) >>> 9) << 4) + 14] = len;

        var i, olda, oldb, oldc, oldd,
            a = 1732584193,
            b = -271733879,
            c = -1732584194,
            d = 271733878;

        for (i = 0; i < x.length; i += 16) {
            olda = a;
            oldb = b;
            oldc = c;
            oldd = d;

            a = md5_ff(a, b, c, d, x[i], 7, -680876936);
            d = md5_ff(d, a, b, c, x[i + 1], 12, -389564586);
            c = md5_ff(c, d, a, b, x[i + 2], 17, 606105819);
            b = md5_ff(b, c, d, a, x[i + 3], 22, -1044525330);
            a = md5_ff(a, b, c, d, x[i + 4], 7, -176418897);
            d = md5_ff(d, a, b, c, x[i + 5], 12, 1200080426);
            c = md5_ff(c, d, a, b, x[i + 6], 17, -1473231341);
            b = md5_ff(b, c, d, a, x[i + 7], 22, -45705983);
            a = md5_ff(a, b, c, d, x[i + 8], 7, 1770035416);
            d = md5_ff(d, a, b, c, x[i + 9], 12, -1958414417);
            c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
            b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
            a = md5_ff(a, b, c, d, x[i + 12], 7, 1804603682);
            d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
            c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
            b = md5_ff(b, c, d, a, x[i + 15], 22, 1236535329);

            a = md5_gg(a, b, c, d, x[i + 1], 5, -165796510);
            d = md5_gg(d, a, b, c, x[i + 6], 9, -1069501632);
            c = md5_gg(c, d, a, b, x[i + 11], 14, 643717713);
            b = md5_gg(b, c, d, a, x[i], 20, -373897302);
            a = md5_gg(a, b, c, d, x[i + 5], 5, -701558691);
            d = md5_gg(d, a, b, c, x[i + 10], 9, 38016083);
            c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
            b = md5_gg(b, c, d, a, x[i + 4], 20, -405537848);
            a = md5_gg(a, b, c, d, x[i + 9], 5, 568446438);
            d = md5_gg(d, a, b, c, x[i + 14], 9, -1019803690);
            c = md5_gg(c, d, a, b, x[i + 3], 14, -187363961);
            b = md5_gg(b, c, d, a, x[i + 8], 20, 1163531501);
            a = md5_gg(a, b, c, d, x[i + 13], 5, -1444681467);
            d = md5_gg(d, a, b, c, x[i + 2], 9, -51403784);
            c = md5_gg(c, d, a, b, x[i + 7], 14, 1735328473);
            b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

            a = md5_hh(a, b, c, d, x[i + 5], 4, -378558);
            d = md5_hh(d, a, b, c, x[i + 8], 11, -2022574463);
            c = md5_hh(c, d, a, b, x[i + 11], 16, 1839030562);
            b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
            a = md5_hh(a, b, c, d, x[i + 1], 4, -1530992060);
            d = md5_hh(d, a, b, c, x[i + 4], 11, 1272893353);
            c = md5_hh(c, d, a, b, x[i + 7], 16, -155497632);
            b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
            a = md5_hh(a, b, c, d, x[i + 13], 4, 681279174);
            d = md5_hh(d, a, b, c, x[i], 11, -358537222);
            c = md5_hh(c, d, a, b, x[i + 3], 16, -722521979);
            b = md5_hh(b, c, d, a, x[i + 6], 23, 76029189);
            a = md5_hh(a, b, c, d, x[i + 9], 4, -640364487);
            d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
            c = md5_hh(c, d, a, b, x[i + 15], 16, 530742520);
            b = md5_hh(b, c, d, a, x[i + 2], 23, -995338651);

            a = md5_ii(a, b, c, d, x[i], 6, -198630844);
            d = md5_ii(d, a, b, c, x[i + 7], 10, 1126891415);
            c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
            b = md5_ii(b, c, d, a, x[i + 5], 21, -57434055);
            a = md5_ii(a, b, c, d, x[i + 12], 6, 1700485571);
            d = md5_ii(d, a, b, c, x[i + 3], 10, -1894986606);
            c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
            b = md5_ii(b, c, d, a, x[i + 1], 21, -2054922799);
            a = md5_ii(a, b, c, d, x[i + 8], 6, 1873313359);
            d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
            c = md5_ii(c, d, a, b, x[i + 6], 15, -1560198380);
            b = md5_ii(b, c, d, a, x[i + 13], 21, 1309151649);
            a = md5_ii(a, b, c, d, x[i + 4], 6, -145523070);
            d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
            c = md5_ii(c, d, a, b, x[i + 2], 15, 718787259);
            b = md5_ii(b, c, d, a, x[i + 9], 21, -343485551);

            a = safe_add(a, olda);
            b = safe_add(b, oldb);
            c = safe_add(c, oldc);
            d = safe_add(d, oldd);
        }
        return [a, b, c, d];
    }

    /*
     * Convert an array of little-endian words to a string
     */
    function binl2rstr(input) {
        var i,
            output = '';
        for (i = 0; i < input.length * 32; i += 8) {
            output += String.fromCharCode((input[i >> 5] >>> (i % 32)) & 0xFF);
        }
        return output;
    }

    /*
     * Convert a raw string to an array of little-endian words
     * Characters >255 have their high-byte silently ignored.
     */
    function rstr2binl(input) {
        var i,
            output = [];
        output[(input.length >> 2) - 1] = undefined;
        for (i = 0; i < output.length; i += 1) {
            output[i] = 0;
        }
        for (i = 0; i < input.length * 8; i += 8) {
            output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (i % 32);
        }
        return output;
    }

    /*
     * Calculate the MD5 of a raw string
     */
    function rstr_md5(s) {
        return binl2rstr(binl_md5(rstr2binl(s), s.length * 8));
    }

    /*
     * Calculate the HMAC-MD5, of a key and some data (raw strings)
     */
    function rstr_hmac_md5(key, data) {
        var i,
            bkey = rstr2binl(key),
            ipad = [],
            opad = [],
            hash;
        ipad[15] = opad[15] = undefined;
        if (bkey.length > 16) {
            bkey = binl_md5(bkey, key.length * 8);
        }
        for (i = 0; i < 16; i += 1) {
            ipad[i] = bkey[i] ^ 0x36363636;
            opad[i] = bkey[i] ^ 0x5C5C5C5C;
        }
        hash = binl_md5(ipad.concat(rstr2binl(data)), 512 + data.length * 8);
        return binl2rstr(binl_md5(opad.concat(hash), 512 + 128));
    }

    /*
     * Convert a raw string to a hex string
     */
    function rstr2hex(input) {
        var hex_tab = '0123456789abcdef',
            output = '',
            x,
            i;
        for (i = 0; i < input.length; i += 1) {
            x = input.charCodeAt(i);
            output += hex_tab.charAt((x >>> 4) & 0x0F) +
                hex_tab.charAt(x & 0x0F);
        }
        return output;
    }

    /*
     * Encode a string as utf-8
     */
    function str2rstr_utf8(input) {
        return unescape(encodeURIComponent(input));
    }

    /*
     * Take string arguments and return either raw or hex encoded strings
     */
    function raw_md5(s) {
        return rstr_md5(str2rstr_utf8(s));
    }

    function hex_md5(s) {
        return rstr2hex(raw_md5(s));
    }

    function raw_hmac_md5(k, d) {
        return rstr_hmac_md5(str2rstr_utf8(k), str2rstr_utf8(d));
    }

    function hex_hmac_md5(k, d) {
        return rstr2hex(raw_hmac_md5(k, d));
    }

    $.md5 = function (string, key, raw) {
        if (!key) {
            if (!raw) {
                return hex_md5(string);
            } else {
                return raw_md5(string);
            }
        }
        if (!raw) {
            return hex_hmac_md5(key, string);
        } else {
            return raw_hmac_md5(key, string);
        }
    };

}(typeof jQuery === 'function' ? jQuery : this));


var Map = function () {
    this.elements = [];

    //获取MAP元素个数
    this.size = function () {
        return this.elements.length;
    };

    //判断MAP是否为空
    this.isEmpty = function () {
        return (this.elements.length < 1);
    };

    //删除MAP所有元素
    this.clear = function () {
        this.elements = [];
    };

    //向MAP中增加元素（key, value)
    this.put = function (_key, _value) {
        this.elements.push({
            key: _key,
            value: _value
        });
    };

    //向MAP中增加元素（key, value)
    this.putAndUpdate = function (_key, _value) {
        if (this.containsKey(_key)) {
            _value += this.get(_key);
            this.removeByKey(_key);
        }
        this.elements.push({
            key: _key,
            value: _value
        });
    };

    //删除指定KEY的元素，成功返回True，失败返回False
    this.removeByKey = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //删除指定VALUE的元素，成功返回True，失败返回False
    this.removeByValue = function (_value) {//removeByValueAndKey
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //删除指定VALUE的元素，成功返回True，失败返回False
    this.removeByValueAndKey = function (_key, _value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value && this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function (_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return false;
        }
        return false;
    };

    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function (_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };

    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function (_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //判断MAP中是否含有指定VALUE的元素
    this.containsObj = function (_key, _value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value && this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function () {
        var arr = [];
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };

    //获取MAP中所有VALUE的数组（ARRAY）
    this.valuesByKey = function (_key) {
        var arr = [];
        for (i = 0; i < this.elements.length; i++) {
            if (this.elements[i].key == _key) {
                arr.push(this.elements[i].value);
            }
        }
        return arr;
    };

    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function () {
        var arr = [];
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };

    //获取key通过value
    this.keysByValue = function (_value) {
        var arr = [];
        for (i = 0; i < this.elements.length; i++) {
            if (_value == this.elements[i].value) {
                arr.push(this.elements[i].key);
            }
        }
        return arr;
    };

    //获取MAP中所有KEY的数组（ARRAY）
    this.keysRemoveDuplicate = function () {
        var arr = [];
        for (i = 0; i < this.elements.length; i++) {
            var flag = true;
            for (var j = 0; j < arr.length; j++) {
                if (arr[j] == this.elements[i].key) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                arr.push(this.elements[i].key);
            }
        }
        return arr;
    };
};

var event_bindings;
$.getElementIdx = function (elt) {
    var count = 1;
    for (var sib = elt.previousSibling; sib; sib = sib.previousSibling) {
        if (sib.nodeType == 1 && sib.tagName == elt.tagName) count++
    }
    return count;
};
$.getElementXPath = function (elt) {
    var path = '';
    for (; elt && elt.nodeType == 1; elt = elt.parentNode) {
        var idx = $.getElementIdx(elt);
        var xname = elt.tagName;
        var id = elt.getAttribute('id');
        if (id) {
            xname += "[@id='" + id + "']"
        }  else if (idx > 1) {
            xname += '[' + idx + ']'
        }
        path = '/' + xname + path;
    }
    return path;
};

$.urlParam = function (name) {
    var results = new RegExp('[\?&#]*' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    } else {
        return results[1] || 0;
    }
};

//var originPath = window.location.origin + window.location.pathname;
//var md5Origin = $.md5(originPath);
//var getVDUrl = "http://www.cobub.com/jsdemo/index.php?c=act&m=support";
//var postVDUrl = "http://www.cobub.com/jsdemo/index.php?c=act&m=submit";
var browser = {
    versions: function() {
        var u = navigator.userAgent,
        app = navigator.appVersion;
        return {
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1,
            iPad: u.indexOf('iPad') > -1
        };
    } (),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
};

$.bindEvent = function (mode) {
    var $body = $('body');
    
    if (mode) {
        $body.children().not('.razor-navbar').on('touchend', function (event) {
        	$body.find('.razor-tooltip').remove();
            $body.find('.razor-outline-shade').remove();
            var target = event.target;
            if ($body.find('.razor-tooltip').length < 1) {
                event.stopPropagation();
                event.preventDefault();
                var width = $(target).width();
                var height = $(target).innerHeight();
                var offset = $(target).offset();
                $body.find('.razor-outline').remove();
                $('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                $('<div class="razor-outline" style="left: ' + (offset.left + width) + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                $('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' + (offset.top + height) + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                $('<div class="razor-outline" style="left: ' + offset.left + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);

                var left = 0;
                    var high = offset.top +height+10;
                    var frame_right = $(document).width();
                    if(frame_right-offset.left>326){
                            left=offset.left;
                       }else{
                            left=offset.left+width-326;
                            if(left<0){
                                left=10;
                            }
                       }

                    if($(document).height()-offset.top-height-10<326){
                            high = offset.top-156;
                       }
                    $('<div class="razor-tooltip" style="left: ' + left + 'px; top: ' + high + 'px; width: 260px !important; position: relative;"><div class="razor-tooltip-header"><div class="razor-tooltip-close"><svg width="10" height="10" viewBox="0 0 10 10" xmlns="http://www.w3.org/2000/svg"><title>关闭</title><path d="M4.706 6.64l3.046 3.119c.306.315.504.318.816 0l.61-.626c.3-.307.32-.508 0-.835L5.955 4.996l3.225-3.3c.302-.31.308-.52 0-.836L8.568.234c-.317-.325-.514-.31-.816 0L4.706 3.353 1.66.234c-.302-.31-.498-.325-.815 0L.233.86c-.308.315-.303.524 0 .835l3.225 3.301L.233 8.297c-.32.328-.302.529 0 .835l.612.626c.308.318.507.315.815 0L4.706 6.64z" fill="#000" fill-rule="evenodd"></path></svg></div><div class="razor-tooltip-drag-handle"><svg width="10" height="10" viewBox="0 0 10 10" xmlns="http://www.w3.org/2000/svg"><title>drag</title><path d="M1.76-.01H.59C.26-.01 0 .25 0 .58v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V.58a.6.6 0 0 0-.6-.59zm0 3.82H.59c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V4.4a.607.607 0 0 0-.6-.59zm0 3.83H.59c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V8.23a.6.6 0 0 0-.6-.59zM5.59-.01H4.41c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V.58c0-.33-.27-.59-.59-.59zm0 3.82H4.41c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V4.4c0-.32-.27-.59-.59-.59zm0 3.83H4.41c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V8.23c0-.33-.27-.59-.59-.59zM9.41-.01H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V.58a.6.6 0 0 0-.6-.59zm0 3.82H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V4.4a.607.607 0 0 0-.6-.59zm0 3.83H8.24c-.32 0-.59.26-.59.59v1.18c0 .32.26.59.59.59h1.18c.32 0 .59-.26.59-.59V8.23a.6.6 0 0 0-.6-.59z" fill="#000" fill-rule="evenodd"></path></svg></div><div class="razor-tooltip-header-title">创建触发事件条件</div><div class="razor-tooltip-section razor-tooltip-error"></div><div class="razor-sliding-views"><div class="razor-tooltip-event-definition-region" style="display: table-cell;"><div class="razor-tooltip-section razor-tooltip-name"><div class="razor-tooltip-label">名称</div><input type="text" class="razor-tooltip-input-text event-cn" placeholder="如：事件-A"></div><div class="razor-tooltip-section razor-tooltip-name"><div class="razor-tooltip-label">别名</div><input type="text" class="razor-tooltip-input-text event-en" placeholder="如：Event-A"></div><div class="razor-tooltip-section razor-tooltip-limit-page"></div><div class="razor-tooltip-section razor-tooltip-include-similar"></div><div class="razor-tooltip-margin-uncollapser"></div><div class="razor-tooltip-separator razor-tooltip-properties-separator"></div><div class="razor-tooltip-properties-listing-region"><div></div></div><div class="razor-tooltip-btn-group"><button class="razor-tooltip-btn razor-tooltip-define">确定</button></div></div><div class="razor-tooltip-custom-properties-region" style="display: table-cell; opacity: 0;"><div><div class="razor-tooltip-full-width-button razor-tooltip-show-event-definition"><div class="razor-tooltip-full-width-button-body"><i class="razor-icon razor-icon-normal razor-icon-chevronleft"></i>返回创建</div></div><div class="razor-tooltip-section razor-tooltip-property-type"><div class="razor-tooltip-label">Type</div><div class="razor-tooltip-property-type-region"><select><option value="" disabled="" selected="">添加来自...</option><option value="selector">元素中的文本</option><option value="javascript">value of JavaScript</option></select></div></div><div class="razor-tooltip-btn-group"><button class="razor-tooltip-btn razor-tooltip-define razor-tooltip-create-custom-property">自定义属性</button></div></div></div></div></div>').appendTo($body);

                    var xpath = $.queryXpath();
                    for(var o in event_bindings){
                        var path=event_bindings[o].xpath;
                        if(path==xpath){
                            //设置已绑定的值
                            var $saveRegion_default = $('.razor-tooltip').find('.razor-tooltip-header').find('.razor-sliding-views').find('.razor-tooltip-event-definition-region');
                            $saveRegion_default.find('.event-en').val(event_bindings[o].event_identifier);
                            $saveRegion_default.find('.event-cn').val(event_bindings[o].event_name);
                        }
                    }
                    $('.razor-tooltip-close').on('click', function () {
                        $body.find('.razor-tooltip').remove();
                        $body.find('.razor-outline-shade').remove();
                    });
                    $(document).keyup(function (e) {
                        if (e.keyCode == 27) {
                            $body.find('.razor-tooltip').remove();
                            $body.find('.razor-outline-shade').remove();
                        }
                    });

                    var tarMsg = $.queryElement();
                    var $saveRegion = $('.razor-tooltip').find('.razor-tooltip-header').find('.razor-sliding-views').find('.razor-tooltip-event-definition-region');
                    $saveRegion.find('.razor-tooltip-btn-group').find('button').bind('click', function (e) {
                        var eventEn = $saveRegion.find('.event-en').val();
                        var eventCn = $saveRegion.find('.event-cn').val();
                        if(('' == eventEn || null == eventEn) || ('' == eventCn || null == eventCn)) {
                            return;
                        }
                        //调用原生方法发送event到addEventTrack.json接口，
                        //数据格式：{"activity":"file:///android_asset/demo.html",
                        //"eventdata":{"eventIdentifier":"click","eventName":"点击",
                        //"path":"//DIV[@id='content']/P[@class='a']/BUTTON[@class='aaa']"}}
                        var realurl=window.location.href;

                        if (browser.versions.android&&realurl.indexOf('?')!=-1) {
                            realurl=realurl.split('\?')[0];
                        }
                        var message='{"activity":"'+realurl+'","eventIdentifier":"'+eventEn
                        +'","eventName":"'+eventCn+'","path":"'+xpath+'"}';
                        setTimeout(function () {
                            $body.find('.razor-tooltip').remove();
                        }, 1000);
                        if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad){
                            var callInfo = JSON.stringify({"m":message});
                            tianbai.getCall(callInfo);}
                        if (browser.versions.android) window.JsInject.sendEvent(message);
                        $('<div class="razor-outline2" style="left: ' + offset.left + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                        $('<div class="razor-outline2" style="left: ' + (offset.left + width) + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                        $('<div class="razor-outline2" style="left: ' + offset.left + 'px' + ' !important; top: ' + (offset.top + height) + 'px' + '!important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                        $('<div class="razor-outline2" style="left: ' + offset.left + 'px' + ' !important; top: ' + offset.top + 'px' + '!important; width: 2px !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                    })
				if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad){
					 /*$(target).on('click', function (tar) {
                  	     tar.stopPropagation();
                  	     tar.preventDefault();
                  	     $body.find('.razor-outline-shade').remove();
                  	     $body.find('.razor-outline').remove();
                  	     $body.find('.razor-tooltip').remove();
                  	     $('<div class="razor-outline-shade" style="left: ' + offset.left + 'px' + ' !important; top: ' + offset.top + 'px' + ' !important; width: ' + width + 'px' + ' !important; max-width: ' + width + 'px' + ' !important; height: ' + height + 'px' + ' !important; max-height: ' + height + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
                  	     $.each($(target), function (id, key) {
	                         $(key).unbind('click');
	                         var width1 = $(key).width();
	                         var height1 = $(key).innerHeight();
	                         var offset1 = $(key).offset();
	                         $('<div class="razor-outline-shade" style="left: ' + offset1.left + 'px' + ' !important; top: ' + offset1.top + 'px' + ' !important; width: ' + width1 + 'px' + ' !important; max-width: ' + width1 + 'px' + ' !important; height: ' + height1 + 'px' + ' !important; max-height: ' + height1 + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
	                     });
	                 });*/
				}
                

            } else {
                event.preventDefault();
                event.stopPropagation();
                $(target).on('click', function (tar) {
                    tar.stopPropagation();
                    tar.preventDefault();
                })
            }
        }).on('touchmove', function (event) {
            // $body.find('.razor-outline').remove();
        });
    } else {
        $body.children().not('.razor-navbar').off().bind('touchend', function (event) {
            var target = event.target;
            $(target).off('click');
        });
        $body.find('.razor-tooltip').remove();
        $body.find('.razor-outline-shade').remove();
    }
};

var Callback = function(str)
{
alert(str);
}
var alerCallback = function()
{
alert('成功');
}

$.queryElement = function() {
    var msg = '';
    var e = e || window.event;
    var target = e.target || e.srcElement;
    var nodeName = target.nodeName;
    var id = target.getAttribute('id');
    var name = target.getAttribute('name');
    var xpath = '/' + $.getElementXPath(target).substring(10);
    msg += '{' + '"nodeName":"' + nodeName + '",' + '"id":"' + id + '",' + '"name":"' + name + '",' + '"xpath":"' + xpath + '"}';
    return msg;
};

$.queryXpath = function() {
    var e = e || window.event;
    var target = e.target || e.srcElement;
    var xpath = '/' + $.getElementXPath(target).substring(10);
    return xpath;
};

$(function () {
    var url = 'http://wapi.cobub.com/get/1.gif?';
    var eventUrl = "http://wapi.cobub.com/event/1.gif?";

    var params = {};
    //Document对象数据
    if (document) {
        params.domain = document.domain || '';
        params.url = document.URL || '';
        params.title = document.title || '';
        params.referrer = document.referrer || '';
    }
    //Window对象数据
    if (window && window.screen) {
        params.sh = window.screen.height || 0;
        params.sw = window.screen.width || 0;
        params.cd = window.screen.colorDepth || 0;
    }
    //navigator对象数据
    if (navigator) {
        params.lang = navigator.language || '';
    }

    window.$An = function (arr) {
        var event_name = arr[0];
        var category = arr[1];
        var action = arr[2];
        var opt_label = arr[3];
        var opt_value = arr[4];
        var img2 = new Image(1, 1);
        var str = 'eventname=' + encodeURI(event_name) +
            '&category=' + encodeURI(category) + '&action=' + encodeURI(action) +
            '&optlabel=' + encodeURI(opt_label) + '&optvalue=' + encodeURI(opt_value);
        //解析_maq配置
        if (_maq) {
            for (var i in _maq) {
                switch (_maq[i][0]) {
                    case '_setAccount':
                        str = str + '&account=' + encodeURI(_maq[i][1]);
                        break;
                    default:
                        break;
                }
            }
        }
        img2.src = eventUrl + str;
    };

    //解析_maq配置
    if (_maq) {
        for (var i in _maq) {
            switch (_maq[i][0]) {
                case '_setAccount':
                    params.account = _maq[i][1];
                    break;
                default:
                    break;
            }
        }
    }

    //拼接参数串
    var args = '';
    for (var i in params) {
        if (args != '') {
            args += '&';
        }
        args += i + '=' + encodeURIComponent(params[i]);
    }

    //通过Image对象请求后端脚本
    var img = new Image(1, 1);
    img.src = url + args;

//    var debug = $.urlParam('debug');
    var debug = 1;
    var $body = $('body');
    var mode = true;
    if (debug == 1) {
        $('<div class="razor-navbar"><div class="razor-navbar-inner"><div class="razor-mode-control"><div class="razor-mode-switch"><a class="razor-mode razor-mode-active" data-mode="definition">定义模式</a><a class="razor-mode" data-mode="normal">正常模式</a></div></div><div class="razor-ved-controls"><div class="razor-dropdown"><a class="razor-dropdown-label razor-exit-link"><i class="razor-icon razor-icon-logout"></i>退出</a></div></div><span class="razor-ved-message"></span></div></div>').appendTo($body);
        $body.attr('style', "user-select: none !important; margin-top: 51px !important; overflow-y: visible !important");
        $.bindEvent(true);
        var $swith = $body.children('.razor-navbar').find('.razor-navbar-inner').find('.razor-mode-control').find('.razor-mode-switch').find('a.razor-mode');
        $swith.on('click', function (tar) {
            $(tar.target).addClass('razor-mode-active').siblings().removeClass('razor-mode-active');
            if ($(tar.target).data('mode') == 'definition') {
                $body.attr('style', "user-select: none !important; margin-top: 51px !important; overflow-y: visible !important");
                $.bindEvent(true);
            } else if ($(tar.target).data('mode') == 'normal') {
                $body.find('.razor-outline').remove();
                $body.find('.razor-outline2').remove();
                $body.attr('style', "user-select: initial !important; margin-top: 51px !important; overflow-y: visible !important");
                $.bindEvent(false);
            }
        });
        $body.children('.razor-navbar').find('.razor-navbar-inner').find('.razor-ved-controls').find('.razor-dropdown').find('a.razor-dropdown-label.razor-exit-link').on('click', function () {
            $.each($swith, function (idx, result) {
                if ($(result).data('mode') == 'normal') {
                    $(result).click();
                    $body.children('.razor-navbar').remove();
                    $body.attr('style', "user-select: none !important; margin-top: 0px !important; overflow-y: visible !important");
                }
            });
        });
    }
});

function getEventBinds(event_list){
    event_bindings=event_list;
    //过滤已经绑定的事件
    for(var j in event_list){
        var path=event_list[j].xpath;
        console.log(path);
        if(''!=path){
            var tem=_xpath(path);
            if(tem==null) continue;
            var rect=tem.getBoundingClientRect();
            var left=rect.left;
            var right=rect.right;
            var top=rect.top;
            var bottom=rect.bottom;
            var $body = $('body');
            $('<div class="razor-outline" style="left: ' + left + 'px' + ' !important; top: ' + top + 'px' + '!important; width: ' + (right-left) + 'px' + ' !important; max-width: ' + (right-left) + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
            $('<div class="razor-outline" style="left: ' + right + 'px' + ' !important; top: ' + top + 'px' + '!important; width: 2px !important; max-width: ' + (right-left) + 'px' + ' !important; height: ' + (bottom-top) + 'px' + ' !important; max-height: ' + (bottom-top) + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
            $('<div class="razor-outline" style="left: ' + left + 'px' + ' !important; top: ' + bottom + 'px' + '!important; width: ' + (right-left) + 'px' + ' !important; max-width: ' + (right-left) + 'px' + ' !important; height: 2px !important; max-height: 2px !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
            $('<div class="razor-outline" style="left: ' + left + 'px' + ' !important; top: ' + top + 'px' + '!important; width: 2px !important; max-width: ' + (right-left) + 'px' + ' !important; height: ' + (bottom-top) + 'px' + ' !important; max-height: ' + (bottom-top) + 'px' + ' !important; white-space: pre-wrap !important; word-wrap: break-word !important;"></div>').appendTo($body);
        }
    }
}

function _xpath(STR_XPATH) {
    var xresult = document.evaluate('.'+STR_XPATH, document.body, null, XPathResult.ANY_TYPE, null);
    var xnodes = [];
    var xres;
    if(xres = xresult.iterateNext()) return xres;
    else return null;
}