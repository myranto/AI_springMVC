<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.web.tp.tp_s6_p14_web_design_mai_2022.models.Information" %>
<%@ page import="com.web.tp.tp_s6_p14_web_design_mai_2022.controller.AiController" %><%--
  Created by IntelliJ IDEA.
  User: Myranto
  Date: 06/05/2023
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = request.getAttribute("title") != null ? (String) request.getAttribute("title") : "Liste";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="<%= title %> de publication d'information sur les intelligence artificielle">
    <meta name="keywords" content="ai, ia, intelligence artificielle, <%= title %>">
    <meta http-equiv="refresh" content="300">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <title><%= title %> - Intelligence artificielle</title>
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kaushan+Script&amp;display=swap">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome-all.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome5-overrides.min.css'/>">

</head>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f1f1f1;
    }

    /* Style du bouton d'ouverture */
    .open-button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
    }

    .open-button:hover {
        background-color: #45a049;
    }

    /* Style de la modal */
    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
    }

    /* Style de la modal - contenu */
    .modal-content {
        background-color: #fefefe;
        margin: 10% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 60%; /* Réduction de la largeur de la modal */
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        text-align: center; /* Alignement du contenu au centre */
    }

    /* Style du titre de la modal */
    .modal-title {
        font-size: 24px;
        margin-bottom: 10px;
    }

    /* Style du texte de la modal */
    .modal-text {
        margin-bottom: 20px;
    }

    /* Style des boutons */
    .modal-buttons {
        display: flex; /* Alignement horizontal des boutons */
        justify-content: center; /* Centrage horizontal des boutons */
        margin-top: 20px;
    }

    .modal-buttons button {
        margin: 0 10px; /* Espacement horizontal entre les boutons */
    }

    /* Style du bouton de fermeture */
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
    }
</style>

</style>
<%
    ArrayList<Information> list = (ArrayList<Information>) request.getAttribute("list");
    int count = (int) request.getAttribute("count");
    int pages = AiController.maxRow;
    String action = (String) request.getAttribute("action");
    if (action == null) action = "list";
    String success = request.getAttribute("success")!=null? (String) request.getAttribute("success") :"";
%>
<body id="page-top" data-bs-spy="scroll" data-bs-target="#mainNav" data-bs-offset="54">
<jsp:include page="../common/header.jsp"/>

<section class="h-25" id="section" hidden>
    <%--    <div class="row">--%>
    <div class="col-lg-12 text-center">
        <form action="<%= request.getContextPath() %>/search" method="get">
            <label> label :
                <input type="text" name="search" class="form-control" placeholder="type here ...">
                <input type="text" name="option" hidden value="1">
            </label>
            <label>date debut :
                <input type="date" class="form-control" name="datedebut">
            </label>
            <label>date fin :
                <input type="date" class="form-control" name="datefin">
            </label>
            <%  if (request.getSession().getAttribute("id")!=null) { %>
            <label>est publier :
                <input type="checkbox" name="ispubliate" value="true">
            </label>
            <% }else{ %>
                <input type="checkbox" name="ispubliate" checked hidden value="true">
            <%}%>
            <input type="submit" class="btn btn-success" value="search">
            <a class="btn" id="close" style="color: red;float: right;margin-top: -2%" href="#">X</a>
        </form>
    </div>

</section>

<section class="bg-light" id="portfolio">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="text section-heading">Intelligence artificielle</h2>
                <h3 class="text-muted section-subheading"><% if (list.size()<0) out.print("aucun resultat"); else out.print(list.size()+" resultat"); %></h3>
                <p style="color: red"><% if (request.getAttribute("error")!=null ) out.print(request.getAttribute("error")); %></p>
                <p style="color: seagreen"><%= success %></p>
            </div>
        </div>
        <div class="row">
            <% for (Information info : list) { %>

            <div class="col-sm-6 col-md-4 portfolio-item"><a class="portfolio-link"
                                                             href="<%= request.getContextPath() %>/ai/information/publication-<%= info.getId() %>.html">
                <div class="portfolio-hover">
                    <div class="portfolio-hover-content"><i class="fa fa-plus fa-3x"></i></div>
                </div>
                <img class="img-fluid w-100 h-75" src="<%= info.getImage() %>">
            </a>
                <div class="portfolio-caption">
                    <h4><%= info.getTitle()%>
                    </h4>
                    <% if ((info.getState().equals("f") ) && ((int)request.getSession().getAttribute("id")== info.getId_autor())) {%>
                        <p class="text-danger">non publier &nbsp;<a class="btn btn-outline-info " onclick="openModal(<%= info.getId() %>)" href="#">publier?</a></p>
                    <% } else if ((info.getState().equals("f") ) && ((int)request.getSession().getAttribute("id")!= info.getId_autor())) { %>
                        <p class="text-danger">non publier &nbsp;</p>
                    <% }else { %>
                    <p class="text-success">est publier
                        <%  if (request.getSession().getAttribute("id")!=null && (int)request.getSession().getAttribute("id")== info.getId_autor()) { %>
                            <a class="btn btn-outline-danger "  href="<%= request.getContextPath() %>/ai/information/publication_retirer-<%= info.getId() %>.html">retirer</a>
                        <%}%>
                    </p>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>
        <center>
            <ul class="pagination page-item">
                <%--//                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>--%>
                <% int first = 0;
                    int last = pages;
                    for (int i = 0; i < count; i++) {
                        if (action.equals("search")) { %>
                <li><a class="page-link" style="color: #1a1e21"
                       href="<%= request.getContextPath() %>/search?first=<%= first %>"><%= i + 1%>
                </a></li>
                <% } else { %>

<%--                <li><a class="page-link" style="color: #1a1e21;"--%>
<%--                       href="<%= request.getContextPath() %>/list_info?first=<%= first %>"><%= i + 1%>--%>
<%--                </a></li>--%>
 <li><a class="page-link" style="color: #1a1e21;"
                       href="<%= request.getContextPath() %>/ai/information/list_information-<%= first %>.html"><%= i + 1%>
                </a></li>

                <%
                        }
                        first = last;
                        last = pages + first;
                    } %>
            </ul>
        </center>
    </div>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <form action="<%= request.getContextPath() %>/ai/information/publication_valider.html" method="post" novalidate>
                <h2 class="modal-title">Confirmer la publication ?</h2>
                <input type="text" name="id_info" id="idpub" hidden />
                <div class="modal-buttons">
                    <input type="submit" class="btn btn-success" value="oui">
                    <button class="btn btn-danger" type="button" onclick="closeModal()">non</button>
                </div>
            </form>

        </div>
    </div>
</section>
<script src="<c:url value='/ressources/theme/assets/bootstrap/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/ressources/theme/assets/js/agency.js' />"></script>
<script>
    // butt_search
    let section = document.getElementById('section');
    let button = document.getElementById('butt_search');
    button.addEventListener("click", (e) => {
        section.hidden = false;
    });
    document.getElementById('close').addEventListener("click", (e) => {
        section.hidden = true;
    });
    // Fonction pour ouvrir la modal
    function openModal(idcs) {
        document.getElementById("myModal").style.display = "block";
        document.getElementById("idpub").value=idcs;
    }

    // Fonction pour fermer la modal
    function closeModal() {
        document.getElementById("myModal").style.display = "none";
    }

</script>
</body>
</html>
