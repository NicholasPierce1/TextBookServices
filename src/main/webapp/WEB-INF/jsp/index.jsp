
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Template</title>
    <link rel="stylesheet" href="/src/main/webapp/WEB-INF/bootstrap/css/bootstrap.min.css">

<style>
    *{
        padding: 0;
        margin:0;
        box-sizing: border-box;
    }
    body{
        background-color: rgb(0, 103, 76);
        
        overflow: hidden;
    }
    .row{
        
        display: table;
        background: white;
        border-radius: 30px;
        box-shadow: 12px 12px 22px;
    }
    .container{
        width:max-content;
    }
    img{
        border-top-left-radius: 30px;
        border-bottom-left-radius: 30px;

    }
    .btn1{
        border:none;
        outline: none;
        height: 50px;
        width: 100%;
        background-color: rgb(0,103,76);
        color: white;
        border-radius: 4px;
        font-weight: bold;
    }
    .btn1:hover{
        background: white;
        border: 1px solid;
        color: rgb(27,85,12);
    }

</style>
</head>
<body>

<section class="Form my-4 py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-ofset-7 px-5 pt-5">
                <img src="/src/main/webapp/WEB-INF/images/loginbackground.jpg" class="img-fluid" alt="">
                <h4 class="text-center">Sign in to your account</h4>
                <form>
                    <div class="form-group">
                        <div class="col-lg-12 mx-lg-auto">
                            <input type="email" placeholder="Email" class="form-control my-4 p-2">
                        </div>
                        <div class="col-lg-12 mx-lg-auto">
                            <input type="password" placeholder="**********" class="form-control my-3 p-2">
                        </div>
                        <div class="col-lg-12 mx-lg-auto">
                            <button type="button" class="btn1 my-3 btn-lg">Login</button>
                        </div>
                    </div>
                    <div style="text-align:center">
                        <a href="#">Forgot Password</a>
                        <p>Don't have an account?
                            <a href="#">Register</a>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous">
</script>

</body>
</html>
