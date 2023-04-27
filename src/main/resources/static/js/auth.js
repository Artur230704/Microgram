let loginForm = document.querySelector('.login-form')
function saveUser(user) {
    const userAsJSON = JSON.stringify(user)
    localStorage.setItem('user', userAsJSON);
}
function restoreUser() {
    const userAsJSON = localStorage.getItem('user');
    const user = JSON.parse(userAsJSON);
    return user;
}
function updateOptions (settings) {
    const update = { ...settings };

    update.method = 'POST';
    update.cache = 'no-cache';
    update.mode = 'cors';
    update.headers = { ... settings.headers };
    update.headers['Content-Type'] = 'application/json';

    const user = restoreUser();

    if(user) {
        update.headers['Authorization'] = 'Basic ' + btoa(user.username + ':' + user.password);
        update.body = JSON.stringify(user);
    }
    return update;
}
function fetchAuthorized(url, options) {
    let settings = options || {};
    return fetch(url, updateOptions(settings))
}

loginForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const user = Object.fromEntries(formData);
    saveUser(user);
    window.location.href = '/publications'



    // fetchAuthorized('/users/auth/login')
    //     .then(() => window.location.href = '/publications')
    //     .catch(() => {
    //         window.location.href = '/users/auth/signup'
    //     });
});