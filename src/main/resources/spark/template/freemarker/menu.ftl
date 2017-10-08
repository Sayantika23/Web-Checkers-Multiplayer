<div class="navigation navbar-form navbar-left" role="search">
	<#if currentPlayer??>
		<a href="/">my home</a> |
		<a href="/signout">sign out [${playerName}]</a>
	<#else>
		<a href="/signin">sign in</a>
	</#if>
</div>