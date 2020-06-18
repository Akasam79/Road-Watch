<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>RoadWatch | By James Obogu</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <link href="css/signin.css" rel="stylesheet">
    </head>
    
    <body class="text-center">
        <form class="form-signin" action="nav" method="POST">
            <img class="mb-4" src="img/road.jpg" alt="" width="134" height="88">
            <h1 class="h3 mb-3 font-weight-normal">Welcome to RoadWatch</h1>
            <h5 class="h5 mb-3 font-weight-normal" style="color:blue;">${info}</h5>
            <label for="username" class="sr-only">Phone Number</label>
            <input type="text" name="phone" id="username" class="form-control" placeholder="Phone Number" required maxlength="11" autofocus><br />
            <label for="inputPassword" class="sr-only">PIN</label>
            <input type="password" name="pin" id="inputPassword" class="form-control" placeholder="PIN" maxlength="4" required><br />
            <input type="text" name="signal" value="login" hidden>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; James Obogu, 2019</p>
        </form>
</body>
</html>

