

    <meta charset="utf-8">
    <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
    Remove this if you use the .htaccess -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="author" content="Administrator">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/css/style.css">
    <link rel="stylesheet" href="${ctx}/css/jquery.dataTables.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${ctx}/css/jquery-confirm.css">
    <link rel="stylesheet" href="${ctx}/css/select2.min.css">
	<link href="${ctx}/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
     <!--<link rel="stylesheet" href="css/dataTables.bootstrap.css">-->
    <script src="${ctx}/js/jquery-2.1.3.min.js"></script>
    <script src="${ctx}/js/jquery.mockjax.js"></script>
    <script src="${ctx}/js/bootstrap.js"></script>
    <script src="${ctx}/js/jquery.dataTables.js"></script>
    <script src="${ctx}/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctx}/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/js/jquery-confirm.min.js"></script>
    <script src="${ctx}/js/select2.min.js"></script>
    <script src="${ctx}/js/blockui-master/jquery.blockUI.js"></script>
    <script src="${ctx}/js/select2-zh.js"></script>
    
	<!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
	     This must be loaded before fileinput.min.js -->
	<script src="${ctx}/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
	<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
	     This must be loaded before fileinput.min.js -->
	<script src="${ctx}/js/plugins/sortable.min.js" type="text/javascript"></script>
	<!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
	     This must be loaded before fileinput.min.js -->
	<script src="${ctx}/js/plugins/purify.min.js" type="text/javascript"></script>
	<!-- the main fileinput plugin file -->
	<script src="${ctx}/js/fileinput.min.js"></script>
	<!-- optionally if you need a theme like font awesome theme you can include 
	    it as mentioned below -->
	<script src="${ctx}/js/themes/fa/theme.js"></script>
	<!-- optionally if you need translation for your language then include 
	    locale file as mentioned below -->
	<script src="${ctx}/js/locales/zh.js"></script>


    <!--<script src="${ctx}/js/dataTables.bootstrap.js"></script>-->
     <!--[if lte IE 6]>
      <link rel="stylesheet" type="text/css" href="../../css/bootstrap-ie6-min.css">
      <![endif]-->
      <!--[if lte IE 7]>
      <link rel="stylesheet" type="text/css" href="../../css/ie.css">
      <![endif]-->
       <!--[if lt IE 9]>
        <script src="../../js/lib/html5shiv.min.js"></script>
        <script src="../../js/lib/respond.min.js"></script>
    <![endif]-->
     <!--[if lte IE 6]>
        <script type="text/javascript" src="../../js/lib/bootstrap-ie.js"></script>
    <![endif]-->
    <!--[if IE]>
        <script type="text/javascript" src="../../js/lib/excanvas.compiled.js"></script>
    <![endif]-->

   <!--  <script>
        (function($){
            var curWwwPath = window.document.location.href;
            var pathName = window.document.location.pathname;
            var pos = curWwwPath.indexOf(pathName);
            var localhostPath = curWwwPath.substring(0,pos);
            var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            //
            $.base = localhostPath + projectName;
            //
            $.format = function(str) {
                for ( var i = 0; i < arguments.length - 1; i++) {
                    str = str.replace("{" + i + "}", arguments[i + 1]);
                }
                return str;
            }

            $.serializeForm = function(form, flag) {
                var o = {};
                if(undefined === flag){
                    flag = true;
                }
                $.each($(form).serializeArray(), function(index) {
                    if(!this['value'] && flag) {
                        return;
                    }
                    if (o[this['name']]) {
                        o[this['name']] = o[this['name']] + "," + this['value'];
                    } else {
                        o[this['name']] = this['value'];
                    }
                });
                return o;
            }
        })(jQuery);

        $.blockUI.defaults.message = '<img src="${ctx}/images/blockui/loading.gif" />';
        $.blockUI.defaults.css.zIndex = 999999;
        $.blockUI.defaults.overlayCSS.zIndex = 999998;
        $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

        $.extend($.fn.dataTable.defaults, {
            "language" : {
                "url" : $.base + "/js/json/zh_CN.json"
            }
        });

        $.fn.select2.defaults.set("language","zh-CN");

        Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1,
                "d+": this.getDate(),
                "H+": this.getHours(),
                "h+": this.getHours(),
                "m+": this.getMinutes(),
                "s+": this.getSeconds(),
                "q+": Math.floor((this.getMonth() + 3) / 3),
                "S": this.getMilliseconds()
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var i in o) {
                var reg = new RegExp("(" + i + ")");
                if (reg.test(format)) {
                    format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[i]).substr(("" + o[i]).length)));
                }
            }
            return format;
        }
    </script>
 -->