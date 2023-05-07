<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Myranto
  Date: 05/05/2023
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="creation d'une information sur une intelligence artificielle">
    <meta name="keywords" content="ai, ia, intelligence artificielle, creation, information">
    <meta http-equiv="refresh" content="300">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <title>Création - Intelligence artificielle</title>
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kaushan+Script&amp;display=swap">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome-all.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/ressources/theme/assets/fonts/fontawesome5-overrides.min.css'/>">
</head>

<body id="page-top" data-bs-spy="scroll" data-bs-target="#mainNav" data-bs-offset="54">
<jsp:include page="../common/header.jsp"/>
<section>
    <div class="container">
    <h1>Intelligence Creation</h1>
    <form class="form-control" action="<%= request.getContextPath() %>/ai/information/creation_result.html" method="post">
        <div class="card-body">
            <div class="form-group">
                <label for="exampleInputEmail1">titre de l'information</label>
                <input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="Enter name article">
            </div>

            <%--                                    description--%>
            <div class="form-group">
                <label for="selectImage">choisir image</label>
                <div class="input-group">
                    <div class="custom-file">
                        <input type="file" name="val" class="form-control custom-file-input" id="selectImage">
<%--                        <label class="custom-file-label" for="selectImage">Choose file</label>--%>
                    </div>
                    <input type="text" hidden name="image" id="imageCode" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="contenu">Contenu:</label><br>
                <textarea id="contenu" rows="2" name="content"></textarea><br><br>
            </div>
        </div>
        <!-- /.card-body -->

        <div class="card-footer" style="align-content: center;text-align: center">
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </div>
    </form>
    </div>
</section>
    <script src="http://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>
    <script>
        CKEDITOR.replace( 'contenu' );
        // ClassicEditor
        //     .create( document.querySelector('#contenu'))
        //     .catch( error => {
        //         console.error( error );
        //     } );
    </script>
    <script>
        const input = document.getElementById("selectImage");
        const imageCode = document.getElementById("imageCode");
        const convertBase64 = (file) => {
            return new Promise((resolve, reject) => {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(file);
                fileReader.onload = () => {
                    resolve(fileReader.result);
                };
                fileReader.onerror = (error) => {
                    reject(error);
                };
            });
        };
        const uploadImage = async (event) => {
            const file = event.target.files[0];
            const base64 = await convertBase64(file);
            imageCode.value = base64;
            console.log(imageCode.value);
        };
        input.addEventListener("change", (e) => {
            uploadImage(e);
        });
    </script>
</body>
</html>
