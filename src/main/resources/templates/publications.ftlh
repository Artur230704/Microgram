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
    <div class="header">
    <nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Microgram</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>

                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item" id="user-info"></li>
                    <li class="nav-item" id="logout-btn">
                        <a class="nav-link active" aria-current="page" href="#">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
    <div class="container">
        <div class="main_block row align-items-start">
            <div class="publication_block col-8"></div>

            <div class="publication_adding_block col-4">
                <form action="/publications/adding" method="POST" enctype="multipart/form-data" id="publicationForm">
                    <p class="publication_text">New post</p>
                    <input type="text" name="description" placeholder="description">
                    <label>
                        <input type="file" name="image">
                        Select a file
                    </label>
                    <input type="submit" value="send">
                </form>
            </div>
        </div>

        <div id="splash-screen" class="d-flex align-items-center justify-content-center">
            <div class="text-center">
                <h1 class="mb-3">Welcome!</h1>
                <p class="mb-3">Please sign in to continue.</p>
                <a href="/users/auth/login" class="btn btn-primary splash_screen_btn">Login</a>
                <a href="/users/auth/signup" class="btn btn-primary splash_screen_btn">Register</a>
                <a href="#" class="btn btn-primary splash_screen_btn" id="splash_screen_continue_btn">Continue</a>
            </div>
        </div>
    </div>


<script src="/js/publication.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>