<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css" href="./styles/common.css">
<!-- custom style -->
<link rel="stylesheet" type="text/css" href="<tiles:getAsString name="customCSS" />">
<link rel="stylesheet" type="text/css" href="<tiles:getAsString name="sidePanelCSS" />">
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	<tiles:insertAttribute name="sidePanel"></tiles:insertAttribute>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>