let registerForm = document.querySelector('.register-form')
let loginForm = document.querySelector('.login-form')

function prepareJson(formData){
    var object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    return JSON.stringify(object);
}

registerForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const data = event.target;
    const formData = new FormData(data)

    fetch('/users/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: prepareJson(formData)
    }).then(function(response) {
        if (response.ok) {
            window.location.href = '/publications';
        } else {
            console.log('HTTP ERROR: ' + response.status);
        }
    }).catch(error => {
        console.error(error);
    });
});