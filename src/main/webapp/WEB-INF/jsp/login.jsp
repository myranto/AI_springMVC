<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Myranto
  Date: 05/05/2023
  Time: 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="Connexion au site d'information sur les intelligence artificielle">
    <meta name="keywords" content="ai,  intelligence artificielle, Connexion">
    <meta http-equiv="refresh" content="300">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <title>Connexion - Intelligence artificielle</title>
    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <!-- Vendor CSS Files -->
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/bootstrap/css/bootstrap.min.css' />">
    <link href="<c:url value='/ressources/theme/assets/bootstrap/css/bootstrap-icons.css'/>" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/assets/bootstrap/css/boxicons.min.css'/>" rel="stylesheet">


</head>
<body id="page-top" data-bs-spy="scroll" data-bs-target="#mainNav" data-bs-offset="54">
<main>
    <div class="container">

        <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                        <div class="d-flex justify-content-center py-4">
                                <span class="d-none d-lg-block" style="color: blue">INTELLIGENCE ARTIFICIELLE</span>

                        </div><!-- End Logo -->

                        <div class="card mb-3">

                            <div class="card-body">

                                <div class="pt-4 pb-2">
                                    <h5 class="card-title text-center pb-0 fs-4">Connectez-vous!</h5>
                                    <p class="text-center small">Entrer votre identifiant ainsi que votre mot de passe</p>
                                    <p style="color: seagreen">${success}</p>

                                </div>

                                <form class="row g-3 needs-validation" method="post" action="<%= request.getContextPath() %>/ai/information/access.html" novalidate>

                                    <div class="col-12">
                                        <label for="yourUsername" class="form-label">Email</label>
                                        <div class="input-group has-validation">
                                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                                            <input type="email" value="admin@gmail.com" name="email" class="form-control" id="yourUsername" required>
                                            <div class="invalid-feedback">Entrez votre email</div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourPassword" class="form-label">Mot de passe</label>
                                        <input type="password" name="password" value="admin"class="form-control" id="yourPassword" required>
                                        <div class="invalid-feedback">Entrez votre mot de passe!</div>
                                    </div>
                                    <p style="color: red">${error}</p>

                                    <div class="col-12">
                                        <button class="btn btn-primary w-100" type="submit">Se connecter</button>
                                    </div>
                                    <div class="col-12">
                                        <p class="small mb-0">vous n'avez pas de compte ? <a href="<%= request.getContextPath() %>/ai/information/register.html">S'inscrire</a></p>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>

    </div>
</main>
<%--    <form action="<%= request.getContextPath() %>/ai/information/access.html" method="post">--%>
<%--        <label>--%>
<%--            Identifiant :<input type="email" value="admin@gmail.com" name="email" placeholder="email">--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            Mot de passe :<input type="password" name="password" value="admin" placeholder="mot de passe">--%>
<%--        </label>--%>
<%--        <h3 style="color: red">${error}</h3>--%>
<%--        <input type="submit" value="se connecter">--%>
<%--    </form>--%>
</body>
</html>
