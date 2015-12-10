<%-- 
    Document   : Login
    Created on : Jun 27, 2014, 9:51:25 PM
    Author     : Sadika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="cssfiles/menu.css">
        <link rel="stylesheet" type="text/css" href="cssfiles/divider.css">
        <link href="cssfiles/bootstrap.min.css" rel="stylesheet">
        <link href="cssfiles/style.css" rel="stylesheet">
        <title>Login</title>
        <style>

            *,
            *:before,
            *:after {
                box-sizing: border-box;
            }
            form {
                border: 1px solid #c6c7cc;
                border-radius: 5px;
                font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
                overflow: hidden;
                width: 240px;
            }
            fieldset {
                border: 0;
                margin: 0;
                padding: 0;
            }
            input {
                border-radius: 5px;
                font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
                margin: 0;
            }
            .account-info {
                padding: 20px 20px 0 20px;
            }
            .account-info label {
                color: #395870;
                display: block;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .account-info input {
                background: #fff;
                border: 1px solid #c6c7cc;
                box-shadow: inset 0 1px 1px rgba(0, 0, 0, .1);
                color: #636466;
                padding: 6px;
                margin-top: 6px;
                width: 100%;
            }
            .account-action {
                background: #f0f0f2;
                border-top: 1px solid #c6c7cc;
                padding: 20px;
            }
            .account-action .btn {
                background: linear-gradient(#49708f, #293f50);
                border: 0;
                color: #fff;
                cursor: pointer;
                font-weight: bold;
                float: left;
                padding: 8px 16px;
            }
            .account-action label {
                color: #7c7c80;
                font-size: 12px;
                float: left;
                margin: 10px 0 0 20px;
            }
        </style>

    </head>
    <body>



        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                        </div>
                        <div class="col-md-4 column"><div class="ui clearing divider"><center><br><br>
                                    
                                    
                                    <form method="post" action="aa">
                                        <fieldset class="account-info">
                                            <label>
                                                Username
                                                <input type="text" name="username">                                            
                                            <label>
                                                Password
                                                <input type="password" name="password">
                                            </label>
                                        </fieldset>
                                        <fieldset class="account-action">
                                            <input class="btn" type="submit" name="submit" value="Login">

                                        </fieldset>
                                    </form>
                                </center>
                            </div>
                            <div class="col-md-4 column">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
