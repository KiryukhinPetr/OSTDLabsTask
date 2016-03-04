<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Account pages : OSTD</title>
  <meta charset="utf-8">

  <!-- Meta -->
  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Always force latest IE rendering engine -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="UTF-8">
    <script src="scripts/angular.js"></script>
    <script src="scripts/ui-bootstrap-tpls-0.12.1.min.js"></script>
    <script src="assets/js/vendor/ui-router/release/angular-ui-router.js"></script>
    <script src="components/account/accountList.js"></script>
    <script src="components/account/accountCreate.js"></script>
    <script src="components/account/accountView.js"></script>
    <script src="app.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body ng-app="ostdApp">
	<h1>Title : ${title}</h1>

	<c:url value="/logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
<ul class="nav nav-tabs">
  <li><a ui-sref="accountCreate">Account Create</a></li>
  <li><a ui-sref="accountList">Account List</a></li>
</ul>
<div ui-view></div>
</div>
</body>
</html>