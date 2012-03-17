<%@ page import="re.own.Clara" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'clara.label', default: 'Clara')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
<body>
<a href="#list-clara" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<div id="list-clara" class="content scaffold-list" role="main">
    <h2>This is Clara's page, and she can do with it what she likes</h2>
    <table>
        <tr>
            <td colspan="2">
                <div class="emphasize">Simple recordings<a href="./nosecurity/">here</a></div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="emphasize">Decent, but kind of boring movies<a href="./medium/">here</a></div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="emphasize">Interesting movies<a href="./high/">here</a></div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="emphasize">Supercool secret movies<a href="./superhigh/">here</a></div>
            </td>
        </tr>

   </table>
</div>
</body>
</html>
