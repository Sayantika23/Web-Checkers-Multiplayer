<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<#--<meta http-equiv="refresh" content="10">-->
	<title>${title} | Web Checkers</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
	<div class="home flex flex-row flex-center">
		<div class="home flex flex-column flex-center">
			<div class="selection-content">
				<div class="selection-form">
					<div class="container-fluid">
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="selection-controls"">
									<div id="selection-info">
										<div class="alert alert-primary">
											<p>Select an opponent</p>
										</div>
										<form action="/game">
											<#list players as player>
											<nav class="navbar flex flex-center flex-align-center navbar-default">
												<input type="radio" name="opponent" value="${player}" id="opponent"> ${player}<br/>
											</nav>
											</#list>
											<#include "button.ftl">
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
</body>
</html>