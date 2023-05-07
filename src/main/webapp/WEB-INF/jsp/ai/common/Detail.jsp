<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.web.tp.tp_s6_p14_web_design_mai_2022.models.Information" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Myranto
  Date: 06/05/2023
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="detail d'une publication sur une intelligence artificielle">
    <meta name="keywords" content="ai, ia, intelligence artificielle, Detail">
    <meta http-equiv="refresh" content="300">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <title>Detail - Intelligence artificielle</title>
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kaushan+Script&amp;display=swap">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome-all.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome5-overrides.min.css'/>">
</head>
<%
    Information info = (Information) request.getAttribute("detail");
%>
<body id="page-top" data-bs-spy="scroll" data-bs-target="#mainNav" data-bs-offset="54">
<jsp:include page="../common/header.jsp"/>
<section>
    <div  role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 mx-auto">
                                <h2 class="text-uppercase"><%= info.getTitle() %></h2>
                            <div class="modal-body">
                                <img class="img-fluid d-block mx-auto" src="<%= info.getImage() %>">
                                <ul class="list-unstyled">
                                    <li><label>Date:</label> <%= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(info.getDate_creation()) %></li>
                                    <li><label >Auteur:</label> <%= info.getAutor().getName() %></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <%= info.getContent() %>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
