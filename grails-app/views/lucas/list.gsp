
<%@ page import="re.own.Lucas" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lucas.label', default: 'Lucas')}" />
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
    <h2>This is Lucas's page.</h2>
    <table width="100%">
        <tr>
            <td colspan="4">
            Some of Lucas's favorite things
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="3">
                Some of Lucas's favorite things
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td colspan="2">
                Computer-related activities
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
           <td></td>
            <td>
                <a href="Http://www.Runescape.com">Runescape</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="http://www.coolmath-games.com/">cool math games</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="http://www.onemorelevel.com/">this one, too</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td colspan="2">
                Books
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
           <td></td>
            <td>
                <a href="http://www.the39clues.com/">39 clues</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="http://www.percyjacksonbooks.com/">Percy Jackson</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="./kingauthur">King Arthur</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td colspan="2">
                Other Stuff
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="http://hes.psharvard.org/">Hildreth Elementary School</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
