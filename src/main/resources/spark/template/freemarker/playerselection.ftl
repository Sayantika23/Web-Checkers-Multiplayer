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
	<div class="home flex flex-column flex-center">
		<div class="selection-content">
			<div class="selection-form">
				<div class="container-fluid">
					<div class="navbar-header">
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="selection-controls"">
									<div id="selection-info">
										<h1 class="home-title">${title}</h1>
										<form action="/mode" method="POST">
											<nav class="navbar flex flex-center flex-align-center navbar-default">
												<input type="radio" name="player" value="human" id="opponent"> Human
											</nav>
											<nav class="navbar flex flex-center flex-align-center navbar-default">
												<input type="radio" name="player" value="computer" id="opponent"> Computer
											</nav>
											<#include "button.ftl">
											<#--<#include "humanbutton.ftl">-->
											<#--<#include "computerbutton.ftl">-->
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