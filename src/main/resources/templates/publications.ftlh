<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Publications</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/f0a5070496.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/publications.css">
    <link rel="stylesheet" href="/css/comment.css">
</head>
<body>
    <div class="container">
        <div class="publication_block"></div>

        <div class="publication_adding_block">
            <form action="/publications/adding" method="POST" enctype="multipart/form-data" id="publicationForm">
                <input type="text" name="description" placeholder="description">
                <input type="file" name="image">
                <input type="submit" value="send">
            </form>
        </div>

        <div id="splash-screen" class="d-flex align-items-center justify-content-center">
            <div class="text-center">
                <h1 class="mb-3">Welcome!</h1>
                <p class="mb-3">Please sign in to continue.</p>
<#--                <a href="/users/auth/login" class="btn btn-primary splash_screen_btn">Login</a>-->
                <a href="/users/auth/signup" class="btn btn-primary splash_screen_btn">Register</a>
                <a href="#" class="btn btn-primary splash_screen_btn" id="splash_screen_continue_btn">Continue</a>
            </div>
        </div>
    </div>


<script src="/js/publication.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>