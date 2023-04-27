let splashScreen = document.querySelector('#splash-screen');
let mainContent = document.querySelector('.publication_block');
let splashScreenBtn = document.querySelector('#splash_screen_continue_btn');
let userInfo = document.querySelector('#user-info');
let logoutBtn = document.querySelector('#logout-btn');

let user = restoreUser();
if (user !== null){
    hideSplashScreen()
    userInfo.innerHTML = '<p class="nav-link active" aria-current="page">' + user.email + '</p>'
} else {
    userInfo.innerHTML = ' ';
}

splashScreenBtn.addEventListener('click', function() {
    hideSplashScreen()
});

logoutBtn.addEventListener('click', (event) => {
    event.preventDefault()
    deleteLocalStorage();
    userInfo.innerHTML = ' ';
    showSplashScreen();
})

function showSplashScreen(){
    splashScreen.classList.remove('d-none');
    mainContent.classList.add('d-none');
}
function hideSplashScreen(){
    splashScreen.classList.add('d-none');
    mainContent.classList.remove('d-none');
}
function deleteLocalStorage(){
    localStorage.removeItem('user');
}
function restoreUser() {
    const userAsJSON = localStorage.getItem('user');
    const user = JSON.parse(userAsJSON);
    return user;
}
function changeLikeState(likeIcon){
    if (likeIcon.className === 'bi bi-heart like_icon'){
        likeIcon.className = 'bi bi-heart-fill text-danger like_icon';
    } else {
        likeIcon.className ='bi bi-heart like_icon';
    }
}
function changeBookmarkState(bookmarkIcon){
    if (bookmarkIcon.classList.contains("bi-bookmark")) {
        bookmarkIcon.classList.remove("bi-bookmark");
        bookmarkIcon.classList.add("bi-bookmark-fill");
        bookmarkIcon.classList.add("text-warning")
    } else {
        bookmarkIcon.classList.remove("bi-bookmark-fill");
        bookmarkIcon.classList.remove("text-warning")
        bookmarkIcon.classList.add("bi-bookmark");
    }
}
function addIconOnImage(post){
    let likeIcon = post.querySelector('.like_icon');
    if (likeIcon.className === 'bi bi-heart like_icon'){
        let overlay = document.createElement('i');
        overlay.className = 'bi bi-heart-fill text-danger icon_overlay';
        document.body.appendChild(overlay);

        setTimeout(function() {
            overlay.classList.add('visible');
        }, 0);

        setTimeout(function() {
            overlay.classList.remove('visible');
            setTimeout(function() {
                overlay.parentNode.removeChild(overlay);
            }, 200);
        }, 2000);
    }

    changeLikeState(likeIcon);
}
function addCommentHandler(post) {
    let commentIcon = post.querySelector('.comment_icon')
    let commentForm = post.querySelector('.commentForm')
    let id = commentForm.querySelector('input[name="publicationId"]').value;
    commentIcon.addEventListener('click', () => {
        if (commentForm.classList.contains("d-none")) {
            commentForm.classList.remove("d-none");
            commentForm.classList.add("d-block");
            showComments(post,id);
        } else {
            commentForm.classList.remove("d-block");
            commentForm.classList.add("d-none");
            hideComments(post);
        }
    })

    commentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const formData = new FormData(commentForm);
        formData.append('publicationId', id);
        fetch('/comments/adding', {
            method: 'POST',
            body: formData
        })
            .then(data => {
                commentForm.reset();
                showComments(post,id);
            })
            .catch(error => {
                console.error(error);
            });
    });
}
function showComments(post,publicationId){
    fetch(`/comments/` + publicationId)
        .then(response => response.json())
        .then(comments => {
            const commentsList = post.querySelector('.comments_list');
            commentsList.innerHTML = '';
            comments.forEach(comment => {
                const commentItem = document.createElement('div');
                commentItem.classList.add('comment_item');
                commentItem.innerHTML = `<p>${comment.email}: ${comment.commentText}</p>`;
                commentsList.appendChild(commentItem);
            });
        })
        .catch(error => {
            console.error(error);
        });
}
function hideComments(post){
    const commentsList = post.querySelector('.comments_list');
    commentsList.innerHTML = '';
}
function displayPublicationsOnPage(){
    fetch('/publications/all')
        .then(response => response.json())
        .then(posts => {
            posts.forEach(post => {
                createPost(post);
            });
        });
}
function createPost(post) {
    let publicationBlock = document.querySelector(".publication_block");
    let newPost = document.createElement("div");
    newPost.innerHTML = '<div class="card w-auto p-3 mt-5 post" style="width: 18rem;">\n' +
        '                    <p class="card-text text-center publication_text">' + post.email + '</p>\n' +
        '                    <img src="data:image/png;base64,' + post.imageString + '" class="card-img-top post_image" alt="post image">\n' +
        '                    <div class="card-body">\n' +
        '                        <p class="card-text text-center publication_text">' + post.description + '</p>\n' +
        '                        <div class="icons">\n' +
        '                            <div class="row text-secondary">\n' +
        '                                <div class="col-4 ps-5">\n' +
        '                                    <i class="bi bi-heart like_icon"></i>\n' +
        '                                    <i class="bi bi-chat-left comment_icon"></i>\n' +
        '                                </div>\n' +
        '                                <div class="col-8 text-end">\n' +
        '                                    <i class="bi bi-bookmark bookmark_icon"></i>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '\n' +
        '                        <form class="commentForm d-none" action="/comments/adding" method="POST" enctype="multipart/form-data">\n' +
        '                            <input type="hidden" name="publicationId" value=' + post.publication_id + '>\n' +
        '                            <textarea name="commentText" placeholder="comment"></textarea>\n' +
        '                            <button type="submit">add comment</button>\n' +
        '                        </form>\n' +
        '\n' +
        '                        <div class="comments_list"></div>\n' +
        '                    </div>\n' +
        '                </div>';

    let likeIcon = newPost.querySelector(".like_icon");
    likeIcon.addEventListener("click", () => {
        changeLikeState(likeIcon);
    });

    let bookmarkIcon = newPost.querySelector(".bookmark_icon");
    bookmarkIcon.addEventListener("click", () => {
        changeBookmarkState(bookmarkIcon);
    });

    let postImage = newPost.querySelector(".post_image");
    postImage.addEventListener('dblclick', function (){
        addIconOnImage(newPost);
    })

    publicationBlock.appendChild(newPost);
    addCommentHandler(newPost);
}





displayPublicationsOnPage();

let publicationForm = document.querySelector('#publicationForm')
publicationForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(publicationForm);
    fetch('/publications/adding', {
        method: 'POST',
        body: formData
    }).then(response => response.json())
        .then(data => {
            let newPost = {
                publication_id: data.publication_id,
                email: data.email,
                imageString: data.imageString,
                description: data.description
            };
            createPost(newPost);
        })
        .catch(error => {
            console.error(error);
        });
});
