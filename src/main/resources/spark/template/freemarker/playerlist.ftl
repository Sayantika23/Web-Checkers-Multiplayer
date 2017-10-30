<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<meta http-equiv="refresh" content="10">
	<title>${title} | Web Checkers</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
	<#if is_human>
	<div class="home flex flex-row flex-center">
		<div class="content flex flex-row flex-center">
			<#if invites?has_content>
			<h2>Reply to request</h2>
				<form action="/game" method="GET">
					<input type="text" hidden name="opponentType" id="opponentType" value="human">
					<input type="text" hidden name="requestType" id="requestType" value="invite">
					<#list invites as player>
					<nav class="navbar flex flex-center flex-align-center navbar-default">
						<input type="radio" name="opponentName" value="${player}" id="opponentName"> ${player}
					</nav>
					<br/>
					</#list>
					<#include "button.ftl">
				</form>
			</#if>
			<#if players?has_content>
				<div class="home flex flex-column flex-center">
					<div class="selection-content">
						<div class="selection-form">
							<div class="container-fluid">
								<div class="navbar-header">
									<div class="panel panel-default">
										<div class="panel-body">
											<div id="selection-controls"">
												<div id="selection-info">
													<div class="alert alert-primary">
														<p>Select an Opponent</p>
													</div>
													<form action="/game" method="GET">
														<input type="text" hidden name="opponentType" id="opponentType" value="human">
														<input type="text" hidden name="requestType" id="requestType" value="invite">
														<#list players as player>
															<nav class="navbar flex flex-center flex-align-center navbar-default">
																<input type="radio" name="opponentName" value="${player}" id="opponentName"> ${player}
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
			<#else >
				<div class="home flex flex-column flex-center">
					<div class="selection-content">
						<div class="selection-form">
							<div class="container-fluid">
								<div class="navbar-header">
									<div class="panel panel-default">
										<div class="panel-body">
											<div id="selection-controls"">
												<div id="selection-info">
													<div class="alert alert-danger">
														<p>There are no opponents online.</p>
													</div>
													<h2></h2>
													<p>Wait for opponent</p>
													<p>or</p>
													<p>Play against Computer</p>
													<form action="/mode" method="POST">
														<nav class="navbar flex flex-center flex-align-center navbar-default">
															<input type="radio" name="player" value="computer" id="opponent"> Computer<br/>
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
			</#if>
			<br/>
		</div>
	</div>
	</#if>
	<#if !is_human>
		<div class="home flex flex-column flex-center">
			<div class="selection-content">
				<div class="selection-form">
					<div class="container-fluid">
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="selection-controls"">
									<div id="selection-info">
										<div class="alert alert-primary">
											<p>Select difficulty level</p>
										</div>
										<form action="/game" method="GET">
											<input type="text" hidden name="opponentType" id="opponentType" value="computer">
											<#list levels as level>
											<nav class="navbar flex flex-center flex-align-center navbar-default">
												<input type="radio" name="difficulty" value="${level}" id="difficulty"> ${level}<br/>
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
</#if>
</body>
</html>