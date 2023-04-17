<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/f0a5070496.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/publications.css">
    <link rel="stylesheet" href="/css/comment.css">
    <link rel="stylesheet" href="/css/publicationForm.css"> //
</head>
<body>
    <div class="container">
        <div class="publication_block">
            <#list publications as post>
                <div class="card w-auto p-3 mt-5 post" style="width: 18rem;">
                    <p class="card-text text-center publication_text">${post.email}</p>
                    <img src="data:image/png;base64,${post.imageString}" class="card-img-top post_image" alt="post image">
                    <div class="card-body">
                        <p class="card-text text-center publication_text">${post.description}</p>
                        <div class="icons">
                            <div class="row text-secondary">
                                <div class="col-4 ps-5">
                                    <i class="bi bi-heart like_icon"></i>
                                    <i class="bi bi-chat-left comment_icon"></i>
                                </div>
                                <div class="col-8 text-end">
                                    <i class="bi bi-bookmark bookmark_icon"></i>
                                </div>
                            </div>
                        </div>

                        <form class="commentForm d-none" action="/comments/adding" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="publicationId" value="${post.publication_id}">
                            <textarea name="commentText" placeholder="comment"></textarea>
                            <button type="submit">add comment</button>
                        </form>

                        <div class="comments_list"></div>
                    </div>
                </div>
            </#list>
        </div>

        <div class="comment_block">
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
                <button id="login-button" class="btn btn-primary">Login</button>
            </div>
        </div>
    </div>


<script src="/js/publication.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>