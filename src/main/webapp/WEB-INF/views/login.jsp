<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap_signin.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

    <!-- Some things -->
    <link href="/css/dialog_id.css" rel="stylesheet">

    <!-- Some2 things -->
    <link href="/css/dialog_classes.css" rel="stylesheet">
    <title>Login Form</title>
</head>
<body>
  <div class="container">
   <form class="form-signin" role="form" method="post" action="<c:url value='j_spring_security_check' />">
       <h2 class="form-signin-heading">Please sign in</h2>
       <label for="inputEmail" class="sr-only">Email address</label>
       <input id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" type="email">
       <label for="inputPassword" class="sr-only">Password</label>
       <input id="inputPassword" class="form-control" placeholder="Password" required="" type="password">
       <div class="checkbox">
          <label>
             <input value="remember-me" type="checkbox"> Remember me
           </label>
       </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
   </form>
  </div>
</body>
</html>