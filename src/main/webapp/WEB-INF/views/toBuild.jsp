<%@ include file="./common/taglibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BuildUpgradePKG</title>
<%@ include file="./common/metas.jsp" %>
</head>
<style>
.step1 {
    width: 600px;
    margin-left: 50px;
}
</style>
<body>
<div class="step1">
<h2>Step1:请上传svn更新列表文件</h2>
<input id="inputFile" name="inputFile[]" multiple type="file" class="file file-loading" data-allowed-file-extensions='["txt"]'>
</div>
</body>
</html>

<script type="text/javascript">
$("#inputFile").fileinput({
	uploadUrl: 'receiveFile'
})
</script>
