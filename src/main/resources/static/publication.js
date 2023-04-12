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

let post_1 = document.querySelector('.post_1')
let post_1_likeIcon = post_1.querySelector('.like_icon');
let post_1_bookmarkIcon = post_1.querySelector('.bookmark_icon');
let post_1_postImage = post_1.querySelector('.post_image');

post_1_likeIcon.addEventListener('click',function (){
    changeLikeState(post_1_likeIcon);
})
post_1_bookmarkIcon.addEventListener('click',function (){
    changeBookmarkState(post_1_bookmarkIcon);
})
post_1_postImage.addEventListener('dblclick', function (){
    addIconOnImage(post_1);
})



let splashScreen = document.getElementById('splash-screen');
let mainContent = document.getElementById('publication_block');
let loginButton = document.getElementById('login-button');

loginButton.addEventListener('click', function() {
    splashScreen.classList.add('d-none');
    mainContent.classList.remove('d-none');
});



let user = {
    userId: 1,
    username: 'Artur',
    email: 'artur230704@gmail.com',
    password: 'qwert',
    role: 'USER',
    isAuthorised: false
}

let post = {
    id: 1,
    user: user,
    title: 'some title',
    image: 'https://popsters.ru/blog/content/all/mceu0sjsks7x5512dji7b1k4ta8w1cnxb39.jpg',
    description: 'some description',
    isLiked: false,
    postDate: 'some date'
}

function createPost(post) {
    let publicationBlock = document.querySelector(".publication_block");
    let newPost = document.createElement("div");
    newPost.innerHTML = `
    <div class="card w-auto p-3 mt-5" style="width: 18rem;">
      <img src="${post.image}" class="card-img-top post_image" alt="...">
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
      </div>
    </div> `;


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
}

createPost(post);

