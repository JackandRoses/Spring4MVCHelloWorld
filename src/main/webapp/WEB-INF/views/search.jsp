<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SpringMVC example</title>
<script type="text/javascript">
	document.onkeydown = function() {
		if (event.keyCode == 13) {
			 var y=document.getElementById("input")
			y.submit();
		}
	}
</script>
</head>
<body>
	<form name="input" id="input" action="hello">
		<input name="id" type="text" />
	</form>
</body>
</html>