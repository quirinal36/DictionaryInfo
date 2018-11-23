<%@page import="ha.can.na.db.DBconn"%>
<%@page import="ha.can.na.bean.DictionaryInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONArray"%>
<%@page import="ha.can.na.controller.DictionaryController"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Logger logger = Logger.getLogger("getDictionaries.jsp");
String data = new String();

DBconn dbconn = new DBconn();
DictionaryController control = new DictionaryController();

List<DictionaryInfo> list = control.getDictionaryList();
JSONArray array = control.listToJSONArray(list);

out.print( array.toString() );
%>
