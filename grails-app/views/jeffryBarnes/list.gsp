
<%@ page import="re.own.JeffryBarnes" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'jeffryBarnes.label', default: 'JeffryBarnes')}" />
		<title>Jeffrey's music</title>
	</head>
	<body>
		<a href="#list-jeffryBarnes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-jeffryBarnes" class="content scaffold-list" role="main">
			<h1>Good news! Jeffrey's music is available for free download.  Simply click below.</h1>
            <g:form name="create" action="show">
			<table>
				<tbody>
                <input type="hidden" name="soundName" value="Track01.wav"/>
                <tr class="even"><td>Song 1</td><td><g:actionSubmit value="Download Song 1" action="show1"/></td> </tr>
                <tr class="odd"><td>Song 2</td><td><g:actionSubmit value="Download Song 2" action="show2"/></td> </tr>
                <tr class="even"><td>Song 3</td><td><g:actionSubmit value="Download Song 3" action="show3"/></td> </tr>
                <tr class="odd"><td>Song 4</td><td><g:actionSubmit value="Download Song 4" action="show4"/></td> </tr>
                <tr class="even"><td>Song 5</td><td><g:actionSubmit value="Download Song 5" action="show5"/></td> </tr>
                <tr class="odd"><td>Song 6</td><td><g:actionSubmit value="Download Song 6" action="show6"/></td> </tr>
				</tbody>
			</table>
            </g:form>
		</div>
	</body>
</html>
