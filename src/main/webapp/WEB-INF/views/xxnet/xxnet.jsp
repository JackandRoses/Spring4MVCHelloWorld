<%@ include file="../common/taglibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en"><!-- See http://www.w3schools.com/tags/ref_language_codes.asp -->
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>XX-Net</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xxnet/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xxnet/bootstrap-responsive.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xxnet/flat-ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xxnet/ladda-themeless.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xxnet/style.css?ver=3.2.7">
    <!-- JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/flat-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/jquery.timer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/spin.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/ladda.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xxnet/site.js?ver=3.2.7"></script>
    <!--[if lte IE 9]>
        <script type="text/javascript" src="${pageContext.request.contextPath}/xxnet/js/jquery.placeholder.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/xxnet/js/jquery.xdomainrequest.min.js"></script>
    <![endif]-->
</head>
<body>
    <div id="header" class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <div class="row-fluid">
                    <div id="logo" class="span8">
                        <a href="/" title="XX-Net">
                            <img src="${pageContext.request.contextPath}/img/xxnet/logo.png" alt="Logo">
                            XX-Net
                        </a>
                        <a id="remote-connection-identifier" style="display: none;" href="/?module=launcher&menu=config">(已允许远程连接)</a>
                    </div> <!-- #logo -->
                    <div class="span4 text-right">
                        <ul class="inline">
                            <li>
                                <a id="resize-window-trigger" href="javascript:void(0);" title='自适应宽度'>
                                    <img src="${pageContext.request.contextPath}/img/xxnet/fixed-width.png" alt="resize-window">
                                </a>
                            </li>
                            <li>
                                <a id="quit-trigger" href="javascript:void(0);" title='退出程序'>
                                    <img src="${pageContext.request.contextPath}/img/xxnet/quit.png" alt="quit">
                                </a>
                            </li>
                        </ul>
                    </div> <!-- .span4 -->
                </div> <!-- .row-fluid -->
            </div> <!-- .container -->
        </div> <!-- .navbar-inner -->
    </div> <!-- #header -->
    <div id="content">
        <div class="container">
            <div class="row-fluid">
                <div id="sidebar" class="span3">
                    <div class="sidebar-nav well">
                        <ul class="nav nav-list"><li class="nav-header">GAEProxy</li>
<li class="active"><a href="/?module=gae_proxy&menu=status">状态</a></li>
<li ><a href="/?module=gae_proxy&menu=config">配置</a></li>
<li ><a href="/?module=gae_proxy&menu=deploy">部署服务端</a></li>
<li ><a href="/?module=gae_proxy&menu=advanced">高级</a></li>
<li ><a href="/?module=gae_proxy&menu=logging">日志</a></li>
<li class="nav-header">X-Tunnel</li>
<li ><a href="/?module=x_tunnel&menu=config">配置</a></li>
<li ><a href="/?module=x_tunnel&menu=logging">日志</a></li>
<li class="nav-header">系统</li>
<li ><a href="/?module=launcher&menu=config">配置</a></li>
<li ><a href="/?module=launcher&menu=about">关于</a></li>
</ul>
                    </div> <!-- .sidebar-nav -->
                </div> <!-- #sidebar -->
                <div class="span9">
                    <h2 id="title"></h2>
                    <div id="tip" class="alert fade in hide">
                        <button id="tip-close" type="button" class="close">×</button>
                        <p id="tip-message" class="message"></p>
                    </div> <!-- #tip -->
                    <!--[if lte IE 9]>
<div class="alert alert-warning">
    您的浏览器版本过低, 部分功能将无法使用。<br>
    我们建议您使用最新版本的Chrome浏览器。
</div>
<![endif]-->

<div id="noob-info" class=""></div>

<div id="details" hidden>
    <h4>状态</h4>
    <table id="status" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th width="25%">属性</th>
                <th>值</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>本地网络状态</td>
                <td id="network-status"></td>
            </tr>
            <tr>
                <td>IP数量</td>
                <td id="ip-num"></td>
            </tr>
            <tr>
                <td>空闲</td>
                <td id="is-idle"></td>
            </tr>
            <tr>
                <td>IP延迟</td>
                <td id="ip-quality"></td>
            </tr>
            <tr>
                <td>连接池(<a href="https://github.com/XX-net/XX-Net/wiki/GoAgent-Connection-status" target="_blank">帮助</a>)</td>
                <td id="connection-status"></td>
            </tr>
            <tr>
                <td>浏览器代理设置</td>
                <td id="browser-proxy-setting"></td>
            </tr>
            <tr id="tr_ca-status">
                <td>CA证书状态(<a href="/module/gae_proxy/control/download_cert">下载</a>)</td>
                <td id="ca-status"></td>
            </tr>
            <tr>
                <td>扫描IP线程数(<a href="/?module=gae_proxy&menu=advanced#scan_ip">设置</a>)</td>
                <td id="scan-ip-thread-num"></td>
            </tr>
            <tr>
                <td>屏蔽状态(<a href="https://github.com/XX-net/XX-Net/wiki/GoAgent-Blocked" target="_blank">帮助</a>)</td>
                <td id="block-stat"></td>
            </tr>
            <!--tr pro="False">
                <td>XX-Net版本</td>
                <td id="xxnet-version"></td>
            </tr-->
        </tbody>
    </table>

    <h4>配置</h4>
    <table id="setting" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th width="25%">属性</th>
                <th>值</th>
            </tr>
        </thead>
        <tbody>
            <tr hidden>
                <td>系统代理状态</td>
                <td id="proxy-status"></td>
            </tr>
            <tr>
                <td>监听代理</td>
                <td id="proxy-listen"></td>
            </tr>
            <tr>
                <td>PAC自动代理地址</td>
                <td id="pac-url"></td>
            </tr>
            <tr>
                <td>IPv6</td>
                <td id="ipv6-status"></td>
            </tr>
        </tbody>
    </table>

    <h4>Appid</h4>
    <table id="appids" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th width="25%">属性</th>
                <th width="75%">值</th>
            </tr>
        </thead>
        <tbody>
            <tr id="hidden-appids">
                <td>当前工作AppID</td>
                <td id="working-appid"></td>
            </tr>
            <tr id="tr-out-of-quota-appids" hidden="true">
                <td>超额AppID</td>
                <td id="out-of-quota-appids"></td>
            </tr>
            <tr id="tr-not-exist-appids" hidden="true">
                <td>不存在的 AppIDs</td>
                <td id="not-exist-appids"></td>
            </tr>
        </tbody>
    </table>

    <h4>系统版本</h4>
    <table id="version" class="table table-bordered table-striped">
        <thead>
            <tr>
                <th width="25%">属性</th>
                <th width="75%">值</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>XX-Net Version</td>
                <td id="xxnet-version"></td>
            </tr>
            <tr>
                <td>Python Version</td>
                <td id="python-version"></td>
            </tr>
            <tr>
                <td>OpenSSL Version</td>
                <td id="openssl-version"></td>
            </tr>
            <tr>
                <td>System Platform</td>
                <td id="sys-platform"></td>
            </tr>
            <tr>
                <td>OS System</td>
                <td id="os-system"></td>
            </tr>
            <tr>
                <td>OS Version</td>
                <td id="os-version"></td>
            </tr>
            <tr>
                <td>OS Release</td>
                <td id="os-release"></td>
            </tr>
            <tr>
                <td>OS Detail</td>
                <td id="os-detail"></td>
            </tr>
            <tr>
                <td>Language</td>
                <td id="language"></td>
            </tr>
            <tr>
                <td>Architecture</td>
                <td id="architecture"></td>
            </tr>
            <tr>
                <td>Browser</td>
                <td id="browser"></td>
            </tr>
        </tbody>
    </table>

    <button id="pop-up-report" class="btn btn-primary">诊断信息</button>
    <br><br>
    <div id="tip">
            <a href="https://github.com/XX-net/XX-Net/issues">查看Github上的问题讨论区</a> 或者 <a href="https://groups.google.com/forum/#!forum/xx-net">Google Group讨论组</a><br>
    </div>
    <br><br>
</div> <!-- #details -->

<!-- Toggle #details-->
<div class="row-fluid">
    <div class="span3 bold">显示详细信息</div> <!-- .span4 -->
    <div class="span9">
        <input id="show-detail" type="checkbox" />
    </div> <!-- .span8 -->
</div>

<div id="report-issue-modal" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>诊断信息</h3>
    </div> <!-- .modal-header -->
    <div class="modal-body">
        <p> * 贴到Github问题区需要登录Github账号</p>
        <textarea id="DiagInfo" onmouseover="this.focus();this.select()"></textarea>
    </div>
    <div class="modal-footer">
    </div> <!-- .modal-footer -->
</div> <!-- #report-issue-modal -->

<!-- JavaScript -->
<script type="text/javascript">
    title('GAEProxy状态信息');
</script>
<script type="text/javascript">
    function OneKeyReport(){
        $.ajax({
            type: 'GET',
            url: '/module/gae_proxy/control/status',
            dataType: 'JSON',
            success: function(result) {
                var information = new Array(
                    'sys-platform: ' + result['sys_platform'],
                    'os-system: ' + result['os_system'],
                    'os-version: ' + result['os_version'],
                    'os-release: ' + result['os_release'],
                    'os-detail: ' + result['os_detail'],
                    'architecture: ' + String(result['architecture'].concat()),
                    'browser: ' + result['browser'],
                    'xxnet-version: ' + result['xxnet_version'],
                    'python-version: ' + result['python_version'],
                    'openssl-version: ' + result['openssl_version'],

                    '\nipv6-status: ' + result['use_ipv6'],
                    'gws-ip-num: ' + 'total:' + result['ip_num'] + ' good:' + result['good_ip_num'],
                    'network-status: ' + result['network_state'],
                    'connected-link: ' + 'new:' + result['connected_link_new'] + ' used:'+ result['connected_link_used'],
                    'worker: ' + 'h1:' + result['worker_h1'] + ' h2:' + result['worker_h2'],
                    'scan-ip-thread-num: ' + result['scan_ip_thread_num'],
                    'ip-quality: ' + result['ip_quality'],
                    'is-idle: ' + result['is_idle'],
                    'block-stat: ' + result['block_stat'],
                    'proxy_state: ' + test_http_proxy_setting(),
                    'ca_state: ' + test_https_proxy_setting(),

                    'Appid_Working: ' + (result['working_appid'].length != 0),
                    'Appids_Out_Of_Quota: ' + (result['out_of_quota_appids'].length != 0),
                    'Appids_Not_Exist: ' + (result['not_exist_appids'].length != 0),
                    'Using_Public_Appid: ' + is_public_appid(result['gae_appid'])
                    );

                updateProperty("textarea#DiagInfo","XX-Net Status:\n\n" + information.join("\n"));

                IssueURL = 'https://github.com/XX-net/XX-Net/issues/new?body=-----------%0A问题描述：%0A请在此描述你遇到的问题，必要时贴出相关的日志信息。%0A%0A-----------%0A诊断信息：%0A' + encodeURIComponent(information.join("\n")) + ";"; 
                $("a#one-key-issue").attr("href",IssueURL);
                GGroupURL = "https://groups.google.com/forum/#!forum/xx-net";
                // The one-key generaton function is to be designed as Github does有待设计类似Github Issue的一键生成功能
                $("a#go-to-google-group").attr("href", GGroupURL);
            }
          })
    }
</script>
<script type="text/javascript">
    $(document).ready(function(){
        var timer = $.timer(function() {
            if ( $("#details").is(":visible")){
                updateDetailStatus();
            }
            updateNoobStatus();
        });

        timer.set({
            time: 1000,
            autostart: true
        });

        updateDetailStatus();
        updateNoobStatus();
        getConfig();
    });

    $("#pop-up-report").click(function(){
        OneKeyReport();
        $('#report-issue-modal').modal();
    });
</script>
<script type="text/javascript">
    function updateProperty(selector, content, attrclass) {
        var previousContent = $(selector).html();
        if (String(previousContent) != String(content)) {
            $(selector).html(content);
        }
        if (attrclass) {
            $(selector).attr("class",attrclass);
        }
    }
    function updateDetailStatus() {
        $.ajax({
            type: 'GET',
            url: '/module/gae_proxy/control/status',
            dataType: 'JSON',
            success: function(result) {
                if ( test_http_proxy_setting() == "OK"){
                    var ca_status_str = test_https_proxy_setting();
                }else{
                    var ca_status_str = "Fail";
                }

                var updates = {
                    'td#sys-platform': result['sys_platform'],
                    'td#os-system': result['os_system'],
                    'td#os-version': result['os_version'],
                    'td#os-release': result['os_release'],
                    'td#os-detail': result['os_detail'],
                    'td#language': result['language'],
                    'td#architecture': String(result['architecture'].concat()),
                    'td#browser': result['browser'],
                    'td#xxnet-version': result['xxnet_version'],
                    'td#python-version': result['python_version'],
                    'td#openssl-version': result['openssl_version'],

                    'td#proxy-listen': result['proxy_listen'],
                    'td#pac-url': result['pac_url'],
                    'td#ipv6-status': result['use_ipv6'] == '0' ? '禁用' : '启用',

                    'td#working-appid': appid_string(result['gae_appid']),
                    'td#out-of-quota-appids': result['out_of_quota_appids'],
                    'td#not-exist-appids': result['not_exist_appids'],

                    'td#network-status': result['network_state'],
                    'td#ip-num': result['good_ip_num'],
                    'td#is-idle': result['is_idle'],
                    'td#connection-status': connection_status_string(result['connected_link_new'], result['worker_h1'], result['worker_h2']),
                    'td#browser-proxy-setting': test_http_proxy_setting(),
                    'td#ca-status': ca_status_str,
                    'td#scan-ip-thread-num': result['scan_ip_thread_num'],
                    'td#ip-quality': result['ip_quality'],
                    'td#block-stat': result['block_stat']

                }
                for (var item in updates) {
                    updateProperty(item, updates[item]);
                }

                if (result['out_of_quota_appids'] === ""){
                    $('#tr-out-of-quota-appids').hide();
                }else{
                    $('#tr-out-of-quota-appids').show();
                }

                if (result['not_exist_appids'] === ""){
                    $('#tr-not-exist-appids').hide();
                }else{
                    $('#tr-not-exist-appids').show();
                }

                if ( !tipHasClose() ) {
                    tipClose();
                }
            },
            error: function(result) {
                var formValue = $('#sys-platform').html();

                if ( tipHasClose() ) {
                    $('html, body').animate({
                        scrollTop: 0
                    }, 'slow');
                }

                if ( formValue == '' ) {
                    tip('状态页显示空白, 很可能GAEProxy启动失败, 请按<a href="https://github.com/XX-net/XX-Net/wiki/How-to-get-start-error-log" target="_blank">指引</a>查找问题原因。<br>', 'warning', false);
                }
            }
        });
    }


    function updateNoobStatus() {
        $.ajax({
            type: 'GET',
            url: '/module/gae_proxy/control/status',
            dataType: 'JSON',
            success: function(result) {
                if ( result['network_state'] == "Fail" ){
                    return updateProperty('#noob-info', '网络无法连接，请检查网络和防火墙设置。', 'none');
                }

                if ( result['good_ip_num'] < 1){
                    if ( result['scan_ip_thread_num'] < 3 ){
                        return updateProperty('#noob-info', '您应该考虑$1开启 IP 扫描器$2。'.replace('$1', '<a href=\"./?module=gae_proxy&menu=advanced#scan_ip\">').replace('$2', '</a>'), 'none');
                    }else{
                        return updateProperty('#noob-info', '请等待大约半小时，XX-Net需要扫描IP。', 'none');
                    }
                }

                if (result['working_appid'] == ""){
                    if ( result['out_of_quota_appids'] != "" ){
                        if ( is_public_appid(result['gae_appid']) ){
                            return updateProperty('#noob-info', '公共APPID配额已用完，请<a href=\"https://github.com/XX-net/XX-Net/wiki/Register-Google-appid\" target=\"_blank\">部署私有APPID</a>。', 'none');
                        }else{
                            return updateProperty('#noob-info', '您的APPID流量已经用完，请<a href=\"https://github.com/XX-net/XX-Net/wiki/Register-Google-appid\" target=\"_blank\">部署更多APPID</a>。', 'none');
                        }
                    }else{
                        return updateProperty('#noob-info', '没有可用APPID，请检查。', 'none');
                    }
                }

                if ( (result['connected_link_new'] + result['worker_h1'] + result['worker_h2']) < 1 ){
                    if ( result['is_idle'] ){
                        return updateProperty('#noob-info', 'System is Idle. ', 'fluent');
                    }else{
                        return updateProperty('#noob-info', '尚未建立连接', 'none');
                    }
                }

                if ( test_http_proxy_setting() == "Fail" ){
                    return updateProperty('#noob-info', '<a href="https://github.com/XX-net/XX-Net/wiki/%E8%AE%BE%E7%BD%AE%E4%BB%A3%E7%90%86" target="_blank">' + '请检查浏览器代理设置。' + '</a>', 'none');
                } else  if ( test_http_proxy_setting() == "Detecting" ){
                    return updateProperty('#noob-info', '正在自检...', 'none');
                }

                if ( test_https_proxy_setting() == "Fail"){
                    return updateProperty('#noob-info', '<a href="https://github.com/XX-net/XX-Net/wiki/GoAgent-Import-CA" target="_blank">' + '请导入浏览器CA证书' + '</a>', 'none');
                } else  if ( test_https_proxy_setting() == "Detecting" ){
                    return updateProperty('#noob-info', '正在自检...', 'none');
                }

                if ( is_public_appid(result['gae_appid']) ){
                    return updateProperty('#noob-info', '您正在使用公共APPID，因为资源有限，使用上存在限制，建议<a href=\"https://github.com/XX-net/XX-Net/wiki/Register-Google-appid\" target=\"_blank\">部署私有APPID</a>。', 'hard');
                }

                return updateProperty('#noob-info', "XX-Net " + result['xxnet_version'].trim() + '，一切正常，你可以访问真正的互联网了。', 'fluent');

            },
            error: function(result) {
                var infoValue = $('#noob-info').html();
                if ( infoValue == '' ) {
                    tip('状态页显示空白, 很可能GAEProxy启动失败, 请按<a href="https://github.com/XX-net/XX-Net/wiki/How-to-get-start-error-log" target="_blank">指引</a>查找问题原因。<br>', 'warning', false);
                } else {
                    updateProperty('#noob-info', 'GAEProxy进程无响应, 可能已退出。', 'none');
                }
            }
        });
    }
    function appid_string(appid){
        if (is_public_appid(appid)){
            return "你正使用公共AppID";
        }else{
            return appid;
        }
    }
    function is_public_appid(appids){
        if ( appids.length > 1 ){
            return false;
        } else {
            return true;
        }
    }
    function connection_status_string(connected_link_new, worker_h1, worker_h2){
        return conn_str = '新:' + connected_link_new + ' h1:'+ worker_h1 + ' h2:'+ worker_h2;
    }
    function proxy_status_string(proxy_state,proxy_url){
        if (proxy_state == "Auto proxy enabled"){
            return '智能代理已启用：' + proxy_url
        }else if (proxy_state == "Proxy enabled"){
            return '全局代理已启用：' + proxy_url
        }else{
            return '全局代理已禁用'
        }
    }

    function test_http_proxy_setting() {
        if (window.http_proxy_setting === "OK" || window.https_proxy_setting === "OK") {
            return "OK";
        }

        $.ajax({
            type: 'GET',
            dataType: 'text',
            crossDomain: true,
            timeout: 1000,
            url: 'http://www.twitter.com/xxnet',
            success: function(result) {
                if ( result == "OK" ){
                    window.http_proxy_setting = "OK";
                }else{
                    window.http_proxy_setting = "Fail";
                }
            },
            error: function(result, textStatus, errorThrown) {
                window.http_proxy_setting = "Fail";
            },
        });

        if (window.http_proxy_setting === "Fail") {
            return "Fail";
        }
        return "Detecting";
    }

    function test_https_proxy_setting() {
        if (window.https_proxy_setting === "OK") {
            return "OK";
        }

        $.ajax({
            type: 'GET',
            dataType: 'text',
            crossDomain: true,
            timeout: 1000,
            url: 'https://www.twitter.com/xxnet',
            success: function(result) {
                if ( result == "OK" ){
                    window.https_proxy_setting = "OK";
                }else{
                    window.https_proxy_setting = "Fail";
                }
            },
            error: function(result, textStatus, errorThrown) {
                window.https_proxy_setting = "Fail";
            },
        });

        if (window.https_proxy_setting === "Fail") {
            return "Fail";
        }
        return "Detecting";
    }
    test_http_proxy_setting();
    test_https_proxy_setting();

    $(function() {
        $('#show-detail').wrap('<div class="switch" />').parent().bootstrapSwitch();
    });

    $('#show-detail').change(function() {
        var isChecked = $(this).is(':checked'),
            key       = 'show_detail',
            value     = isChecked ? 1 : 0;
        if (isChecked) {
            $( "#details" ).slideDown();
        } else {
            $( "#details" ).slideUp();
        }

        return setConfig(key, value);
    });

    function setConfig(key, value) {
        var pageRequests = {};
        pageRequests['cmd'] = 'set_config';
        pageRequests[key]   = value;

        $.ajax({
            type: 'GET',
            url: '/config',
            data: pageRequests,
            dataType: 'JSON'
        });
    }

    function getConfig() {
        var pageRequests = {
            'cmd': 'get_config'
        };
        $.ajax({
            type: 'GET',
            url: '/config',
            data: pageRequests,
            dataType: 'JSON',
            success: function(result) {
                if ( result['show_detail'] != 0 ) {
                    $( "#show-detail").parent().removeClass('switch-off');
                    $( "#show-detail").parent().addClass('switch-on');
                    $( "#show-detail").prop('checked', true);
                    $( "#details" ).slideDown();
                } else {
                    $( "#show-detail").parent().addClass('switch-off');
                    $( "#show-detail").parent().removeClass('switch-on');
                    $( "#show-detail").prop('checked', false);
                    $( "#details" ).slideUp();
                }
            }
        });
    };
</script>



                </div>
            </div> <!-- .row-fluid -->
        </div> <!-- .container -->
    </div> <!-- #content -->
    <!-- JavaScript -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript">
        $(function() {
            window.isFullWidth = false;
        });
    </script>
    <script type="text/javascript">
        $('#resize-window-trigger').click(function() {
            isFullWidth = !isFullWidth;

            if ( isFullWidth ) {
                $('img', this).attr('src', ${pageContext.request.contextPath}+'/img/xxnet/full-width.png');
                $('.container').addClass('container-fluid');
                $('.container').removeClass('container');
            } else {
                $('img', this).attr('src', ${pageContext.request.contextPath}+'/img/xxnet/fixed-width.png');
                $('.container-fluid').addClass('container');
                $('.container-fluid').removeClass('container-fluid');
            }
        });
    </script>
    <script type="text/javascript">
        $('#quit-trigger').click(function() {
            $.ajax({
                type: 'GET',
                url: '/quit',
                dataType: 'JSON',
                success: function(result) {
                    if ( result['status'] == 'success' ) {
                        tip('退出成功。', 'success');
                    } else {
                        tip('退出失败。', 'error');
                    }
                },
                error: function() {
                    tip('退出失败，网络错误。', 'error');
                }
            });
        });
    </script>
    <script type="text/javascript">
        var pageRequests = {
            'cmd': 'get_config'
        };

        $.ajax({
            type: 'GET',
            url: '/config',
            data: pageRequests,
            dataType: 'JSON',
            success: function(result) {
                if ( result['allow_remote_connect'] != 0 ) {
                    $('#remote-connection-identifier').show();
                }
            }
        });
    </script>
</body>
</html>
