let registerForm = document.querySelector('.register-form')
registerForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const data = event.target;
    const formData = new FormData(data)
    const user = Object.fromEntries(formData);
    saveUser(user)

    fetch('/users/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Object.fromEntries(formData))
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

function saveUser(user) {
    const userAsJSON = JSON.stringify(user)
    localStorage.setItem('user', userAsJSON);
}