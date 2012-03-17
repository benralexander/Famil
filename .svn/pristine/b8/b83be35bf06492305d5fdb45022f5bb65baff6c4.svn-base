<%@ page import="re.own.Ben" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'papa.label', default: 'Ben')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>

    <table id="tablewrapper6">
        <tr>
            <td>
                <h1>Technical blog:Serving video from your webpages using Grails</h1>
                <h5>January 15, 2012</h5>
            </td>
        </tr>
        <tr>
            <td>
               <p>Have you ever tried to wade through the complexities necessary to provide a video clip to people viewing your webpage?  One easy solution
                for Grails programmers is to use the FlashPlayer plug-in.  It took me a little fiddling to get the plug-in working (fiddling that I thought I'd capture in this
                post) but once the plug-in became operational the presentation of further video clips was a breeze.  I'll describe the two
                steps that are necessary, and if you follow these steps then soon your webpages can be streaming video too. Note : the
               following instructions  are directed to users of Grails V2RC –- if you're using an earlier or later version of Grails then
                I make no guarantees.</p>
            </td>
        </tr>
        <tr>
            <td>
                <b>Step one</b> is to retrieve the plug-in off the net and install it in Grails. Use the standard command:
            </td>
        </tr>
        <tr>
            <td>
                <pre>grails install-plugin flash-player</pre>
            </td>
        </tr>
        <tr>
            <td>
                <p>Ideally this command should be sufficient to pull the plug-in down off the net and install it in your current grails project,
                but I ran into some difficulties. First off, there was a dependency somehow incorrectly listed in the plug-in, leading to an
                error message like this:</p>
            </td>
        </tr>
        <tr>
            <td>
                <pre>| Error WARNING: Specified dependency definition compile(:swfobject:2.2.1 > *) is invalid! Skipping.. </pre>
            </td>
        </tr>
        <tr>
            <td>
                I was able to get around this first problem by downloading the most recent release of swfobject from website  http://code.google.com/p/swfobject/,
                and copying swfobject.js to the JavaScript directory of my project. Next I added the line
            </td>
        </tr>
        <tr>
            <td>
               <pre>compile ":swfobject:2.2.1" </pre>
            </td>
        </tr>
        <tr>
            <td>
                <p>to the plugins section of BuildConfig.groovy.  The second problem with the plug-in installation wasn’t immediately obvious, but upon
                checking the plug-in documentation I discovered that a directory “movie” should've been created in my directory, and a test file named video.flv
                should've been downloaded, but neither happened. To correct this problem I simply created the directory myself, and then copied over a *.flv file
                I found lying around on disk for testing. With these two changes the plug-in was installed and ready to go. </p>
                <p />
            </td>
        </tr>
        <tr>
            <td>
               <p><b>Step two</b> was to insert the necessary references into the GSP code to direct my flash movie to the desired part of my user interface.  This part of
                the work followed the plug-in documentation pretty precisely, so I won't bother to describe it here except to say that it was pleasingly simple.
                The basic idea is to let your GSP handle the data stream and then send it down to the browser. The only trick involved choosing the correct
                method to reference the data constituting the video. The first time through I provided the byte stream directly and my flash player offered an
                obscure looking error message stating <pre>"Task Queue failed at step five: Playlist could not be loaded"</pre>  My mistake was attempting to be a little too
                clever and providing my own bitstream from the database. This flash player instead wants to reference a file on disk-- as long as you can do that
                (perhaps by using the shortcut $\{createLinkTo (dir:”movies”, file:”my_movie.mov”)\} you should be in good shape. In fact, little or no manipulation at
                the controller level was necessary, all of my manipulations or at the view level in the GSP, and the plug-in take care of all the hard work behind
                the scenes. </p>
            </td>
        </tr>
        <tr>
            <td align="center">
               <g:form name="back" action="list">
                    <g:actionSubmit value="return" action="list"/>
                </g:form>

            </td>
        </tr>
    </table>


    <table id="tablewrapper7">
        <tr>
            <td>
                <h1>Technical blog:Setting up MySQL</h1>
                <h5>January 29, 2012</h5>
            </td>
        </tr>
        <tr>
            <td>
                <p>It should be simple enough to set up MySQL from Grails. And it is, given strictly the default configuration,
                which has the driving application and the database running on the same machine. In any sort of realistic
                production scenario, however,  you're going to need to house a database elsewhere, and there are more
                than just a couple tricks to get MySQL to communicate remotely, which it absolutely does NOT do
                by default. The following paragraphs capture the tricks you'll need</p>
            </td>
        </tr>
        <tr>
            <td>
                After you  download MySQL and install it, copy the jar to the lib directory of your
                Grails application. Then go to DataSource.groovy and prepare your app to point to the remote
                machine. There are plenty of complicated extensions, many of which are provided in a comment by Grails itself,
                but the most essential changes are these:
            </td>
        </tr>
        <tr>
            <td>
                <pre>dataSource {
                dbCreate = "update"
                driverClassName = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://192.168.1.110:3306/cp?autoreconnect=true"
                username = "root"
                password = "myPassw0rd"
                </pre>
            </td>
        </tr>
        <tr>
            <td>
                <p>Next, go to the machine that's running MySQL and examine the configuration files. These might be called
                my.CFG, or (on Windows) may be called my.INI, and they might be located in a wide  variety of directories
                (a little bit LESS flexibility would make this process easier, not harder) but I eventually found
                by looking at the Windows service definition and seeing where the command string pointed,  which turned
                out to be the root directory of the MySQL installation.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>In addition to configuration files you'll also need to tell the running SQL software that
                you feel comfortable with someone coming in remotely. After logging into the database as root,
                you'll need a series of commands along these lines:</p>
            </td>
        </tr>
        <tr>
            <td>
                <pre>
                    //grant access to user-remote host combination
                    GRANT USAGE ON *.* to root@'yourremotehost' IDENTIFIED BY 'myPassw0rdthis';
                    //force update of authorization changes
                    FLUSH PRIVILEGES;
                </pre>
            </td>
        </tr>
         <tr>
            <td>
                And then, Grails reports and absolutely indecipherable error message while trying to make the remote connection
                15 or 20 times, go back and carefully check your Windows 7 firewall. The process of opening up the firewall
                looks like this:
            </td>
        </tr>
        <tr>
            <td>
                <pre>Control panel /
                     Windows Firewall /
                     Advanced Settings /
                     Inbound Rules
                     New rule
                     (select port, and apply additional parameters as requested by the wizard)</pre>
            </td>
        </tr>
        <tr>
            <td>
                <p>... and then finally the remote system starts acting like local system, and you returned to the land of joy.
                I wish all of these instructions have been in one place, since I can't afford to lose any additional hair unnecessarily,
                but at least the whole systems working now.</p>
                <p />
            </td>
        </tr>
        <tr>
            <td align="center">
                <g:form name="back" action="list">
                    <g:actionSubmit value="return" action="list"/>
                </g:form>

            </td>
        </tr>
    </table>



	</body>
</html>
