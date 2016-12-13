    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <%@ page import="com.infosys.basic.util.Uploader" %>

            <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    up.setSavePath("upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(1000); //��λKB
    up.upload();

    String callback = request.getParameter("callback");
	System.out.println("callback:"+up.getUrl());
    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );
//    System.out.println("result:"+result);
//    result = result.replaceAll( "\\\\", "\\\\" );
    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
    %>
