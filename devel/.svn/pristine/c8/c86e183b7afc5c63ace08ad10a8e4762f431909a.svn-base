<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css" />

<title>Developer Util</title>
</head>
<body>

<p class="title">HQL</p>

<stripes:errors/>

<stripes:form action="/util/hbn_query" method="get">

	<p><stripes:textarea name="query" rows="10" cols="100"></stripes:textarea></p>

	<stripes:submit name="execute" value="Execute"/>
	
	<hr>
	
	<br>
	<display:table name="${actionBean.results}" ></display:table>

</stripes:form>

</body>
</html>