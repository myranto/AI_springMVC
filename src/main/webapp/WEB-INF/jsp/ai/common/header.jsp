<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-dark" id="mainNav">
    <div class="container"><a class="navbar-brand" href="#page-top">Intelligence artificielle</a>
        <button data-bs-toggle="collapse" data-bs-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right"
                type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i
                class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto text-uppercase">
                <li class="nav-item"><a class="nav-link" type="button" id="butt_search" href="#"><i class="fas fa-search" ></i></a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ai/information/list_information.html">info
                    AI</a></li>
                <%  if (request.getSession().getAttribute("id")!=null) { %>
                    <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ai/information/create_publication.html">creation</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ai/information/ownpub-<%= request.getSession().getAttribute("id") %>.html">mes publication</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ai/information/deconnexion-<%= request.getSession().getAttribute("id") %>.html">se deconnecter</a></li>
                <% } else{ %>
                  <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ai/information/connexion.html">se connecter</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
<%--assets/img/header-bg.jpg--%>
<%--<c:url value="/ressources/theme/assets/img/header-bg.jpg" var="imagePath" />--%>
<%--<header class="masthead" style="background-image: url('${imagePath}')">--%>
<%--    <div class="container">--%>
<%--        <div class="intro-text">--%>
<%--            <div class="intro-lead-in"><span>Welcome To Our Studio!</span></div>--%>
<%--            <div class="intro-heading text-uppercase"><span>It's Nice To Meet You</span></div><a class="btn btn-primary btn-xl text-uppercase" role="button" href="#services">Tell mE more</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</header>--%>