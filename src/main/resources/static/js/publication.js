let splashScreen = document.querySelector('#splash-screen');
let mainContent = document.querySelector('.publication_block');
let loginButton = document.querySelector('#login-button');

loginButton.addEventListener('click', function() {
    splashScreen.classList.add('d-none');
    mainContent.classList.remove('d-none');
});

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

function addCommentHandler(post){
    let commentIcon = post.querySelector('.comment_icon')
    let commentForm = post.querySelector('.commentForm')
    let id = commentForm.querySelector('input[name="publicationId"]').value;
    commentIcon.addEventListener('click', () => {
        if (commentForm.classList.contains("d-none")) {
            commentForm.classList.remove("d-none");
            commentForm.classList.add("d-block");
        } else {
            commentForm.classList.remove("d-block");
            commentForm.classList.add("d-none");
        }
    })

    commentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const formData = new FormData(commentForm);
        axios.post('/comments/adding', formData)
            .then(response => {
                commentForm.reset();
                axios.get(`/comments/` + id)
                    .then(response => {
                        const comments = response.data;
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
            })
            .catch(error => {
                console.error(error);
            });
    });
}

let posts = document.querySelectorAll('.post');
posts.forEach(post => {
    let like = post.querySelector(".like_icon");
    like.addEventListener("click", () => {
        changeLikeState(like);
    });

    let bookmarkIcon = post.querySelector(".bookmark_icon");
    bookmarkIcon.addEventListener("click", () => {
        changeBookmarkState(bookmarkIcon);
    });

    let postImage = post.querySelector(".post_image");
    postImage.addEventListener('dblclick', function (){
        addIconOnImage(post);
    })

    addCommentHandler(post)
});

let form = document.querySelector('#publicationForm')
form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(form);
    axios.post('/publications/adding', formData)
        .then(response => {
            const publicationId = response.data.publication_id;
            const email = response.data.email;
            const imageString = response.data.imageString;
            const description = response.data.description;

            let newPost = {
                publicationId: publicationId,
                email: email,
                imageString: imageString,
                description: description
            }
            createPost(newPost);
        })
        .catch(error => {
            console.error(error);
        });
});

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
        '                            <input type="hidden" name="publicationId" value="' + post.publicationId + '">\n' +
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