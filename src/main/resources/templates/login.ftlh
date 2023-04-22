<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/auth.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="POST" enctype="multipart/form-data">
            <input type="text" placeholder="email" name="email"/>
            <input type="password" placeholder="password" name="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="/users/auth/signup">Create an account</a></p>
        </form>
    </div>
</div>

<script src="/js/auth.js"></script>
</body>
</html>