<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Myranto
  Date: 06/05/2023
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="inscription au site d'information sur les intelligence artificielle">
    <meta name="keywords" content="ai,  intelligence artificielle, inscription">
    <meta http-equiv="refresh" content="300">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <title>Inscription - Intelligence artificielle</title>
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
                                    <h5 class="card-title text-center pb-0 fs-4">Inscription!</h5>
                                    <p class="text-center small">Informer vous, informer les autres</p>
                                </div>

                                <form class="row g-3 needs-validation" method="post" action="<%= request.getContextPath() %>/ai/information/inscription.html" novalidate>
                                    <div class="col-12">
                                        <label for="yourUsernam" class="form-label">Nom :</label>
                                        <div class="input-group has-validation">
                                            <span class="input-group-text" id="inputGroupPrepen">@</span>
                                            <input type="text"  name="name" class="form-control" id="yourUsernam" required />
                                            <div class="invalid-feedback">Entrez votre nom</div>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <label for="yourUsername" class="form-label">Email</label>
                                        <div class="input-group has-validation">
                                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                                            <input type="email"  name="email" class="form-control" id="yourUsername" required />
                                            <div class="invalid-feedback">Entrez votre email</div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="yourPassword" class="form-label">Mot de passe</label>
                                        <input type="password" name="password" class="form-control" id="yourPassword" required />
                                        <div class="invalid-feedback">Entrez votre mot de passe!</div>
                                    </div>
                                    <div class="col-12">
                                        <label for="yourPasswor" class="form-label">Retaper votre mot de passe</label>
                                        <input type="password" name="password2" class="form-control" id="yourPasswor" required />
                                        <div class="invalid-feedback">Entrez votre mot de passe!</div>
                                    </div>

                                    <p style="color: red">${error}</p>

                                    <div class="col-12">
                                        <button class="btn btn-primary w-100" type="submit">S'inscrire</button>
                                    </div>
                                    <div class="col-12">
                                        <p class="small mb-0">Deja un compte? <a href="<%= request.getContextPath() %>/ai/information/connexion.html">Se connecter</a></p>
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

</body>
</html>
