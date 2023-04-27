<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="/css/auth.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="register-form" method="POST" enctype="multipart/form-data">
            <input type="text" placeholder="name" name="username" required/>
            <input type="text" placeholder="email address" name="email" required/>
            <input type="password" placeholder="password" name="password" required/>
            <button>create</button>
            <p class="message">Already registered? <a href="/users/auth/login">Sign In</a></p>
        </form>
    </div>
</div>

<script src="/js/registration.js"></script>
</body>
</html>