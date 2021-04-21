<%@ page import="org.springframework.boot.configurationprocessor.json.JSONObject" %>
<%@ page import="org.springframework.boot.configurationprocessor.json.JSONArray" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.springframework.boot.configurationprocessor.json.JSONException" %><%--
  Created by IntelliJ IDEA.
  User: Spyridon
  Date: 3/9/2021
  Time: 8:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Template</title>
    <link rel="stylesheet" href="/css-bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/loginCSS.css">
    <script type="module" src="/js/LoginHandler.js"></script>

    <!--
        per nick: remove in production. Testing path variables incur errors from get requests
        relating to favico
    -->
    <link rel="shortcut icon" href="#">
</head>
<body>

<%
    // enumerates constant key fields for decoding and input-form binding (username & password)
    final String loginUserInfoUsernameKey = "_username";
    final String loginUserInfoPasswordKey = "_password";

    // acquire json object for dynamic state parsing/decoding
    final JSONObject data = (JSONObject)request.getAttribute("data");

    /*
     declares a general errors (holds either a general error from server or
     -- if exception yielded, then set general errors to local constant
     */

    String generalErrors = "";
    final String generalErrorsDefault = "error occurred in rendering page; please contact IT Support";

    // initializes a map to hold all possible json objects (ErrorBindings)
    final Map<String, JSONObject> errorBindings = new HashMap<String, JSONObject>();

    // acquires errors (JSONArray) & general errors (String) -- note: may be null
    try {
        generalErrors = data.isNull("GeneralErrors") ? "" : data.getString("GeneralErrors");
        final JSONArray bindingErrors = data.isNull("Errors") ? null : data.getJSONArray("Errors");

        if(bindingErrors != null && !generalErrors.equals(""))
            throw new RuntimeException("Exception occurred in binding state -- general errors AND errors are set");

        if(bindingErrors != null) {

            // iterate over all error bindings and dynamically set them into the map
            // to where the keys stated above can access their state IF they exist
            for (int i = 0; i < bindingErrors.length(); i++) {

                // accesses JSONObject
                final JSONObject errorBinding = bindingErrors.getJSONObject(i);

                // set field name as the key (will be same to the constant keys stated above)
                // sets value to the JSONObject itself
                errorBindings.put(errorBinding.getString("fieldName"), errorBinding);

            }
        }

        // else -- no errors are set, normal flow occurs


    }
    catch(Exception ex){

        System.out.println("internal error in rendering page: " + ex.getMessage());

        // sets general errors to constant
        generalErrors = generalErrorsDefault;
    }


%>
<section class="Form my-4 py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-ofset-7 px-5 pt-5">
                <img src="/img/loginbackground.jpg" class="img-fluid" alt="">
                <h4 class="text-center">Sign in to your account</h4>
                <form>
                    <div class="form-group">
                        <div class="col-lg-12 mx-lg-auto">
                            <%
                                /*
                                IF corresponding error binding exist then set message error label and
                                set form refresh state in input field if it is not null
                                 */

                                String emailPlaceHolder = "Email";
                                String emailErrorMessage = "";
                                String emailHiddenValue = "hidden";
                                try {
                                    // if error bindings retain the error binding for username then set place holder and message accordingly
                                    if (errorBindings.containsKey(loginUserInfoUsernameKey)) {


                                        emailPlaceHolder =
                                                errorBindings.get(loginUserInfoUsernameKey).isNull("faultyData") ?
                                                        emailPlaceHolder :
                                                        errorBindings.get(loginUserInfoUsernameKey).getString("faultyData");

                                        emailErrorMessage = errorBindings.get(loginUserInfoUsernameKey).getString("message");

                                        emailHiddenValue = "text";
                                    }
                                }
                                catch(Exception ex){

                                    System.out.println("username: internal error in rendering page: " + ex.getMessage());

                                    // sets general errors to constant
                                    generalErrors = generalErrorsDefault;
                                }

                                out.println(
                                    "<input type=\"email\"" +
                                            " placeholder=\"" + emailPlaceHolder + "\" name=\""
                                            + loginUserInfoUsernameKey +
                                            "\" class=\"form-control my-4 p-2\">"
                            );

                            out.println("<label type=\"" + emailHiddenValue + "\" id=\"emailErrorLabel\">" + emailErrorMessage + "</label>");
                            %>
                        </div>
                        <div class="col-lg-12 mx-lg-auto">
                            <%
                                /*
                                IF corresponding error binding exist then set message error label and
                                set form refresh state in input field if it is not null
                                 */

                                String passwordPlaceHolder = "****";
                                String passwordErrorMessage = "";
                                String passwordHiddenValue = "hidden";

                                try {
                                    // if error bindings retain the error binding for username then set place holder and message accordingly
                                    if (errorBindings.containsKey(loginUserInfoPasswordKey)) {


                                        passwordPlaceHolder =
                                                errorBindings.get(loginUserInfoPasswordKey).isNull("faultyData") ?
                                                        passwordPlaceHolder :
                                                        errorBindings.get(loginUserInfoPasswordKey).getString("faultyData");

                                        passwordErrorMessage = errorBindings.get(loginUserInfoPasswordKey).getString("message");

                                        passwordHiddenValue = "text";
                                    }
                                }
                                catch(Exception ex){

                                    System.out.println("passwrod: internal error in rendering page: " + ex.getMessage());

                                    // sets general errors to constant
                                    generalErrors = generalErrorsDefault;
                                }

                                out.println(
                                        "<input type=\"password\"" +
                                                " placeholder=\"" + passwordPlaceHolder + "\" name=\""
                                                + loginUserInfoPasswordKey +
                                                "\" class=\"form-control my-3 p-2\">"
                                );

                                out.println("<label type=\"" + passwordHiddenValue + "\" id=\"passwordErrorLabel\">" + passwordErrorMessage + "</label>");
                            %>
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

<%
    // sets the hidden input for general errors
    System.out.println("making general error: " + generalErrors);
    out.println("<input id=\"generalErrors\" type=\""+ "hidden" + "\" value=\"" + generalErrors + "\">");
%>

<script type="application/javascript" src="/js-bootstrap/bootstrap.bundle.min.js"></script>


</body>
</html>
