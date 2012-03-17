<%@ page import="re.disp.*" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'papa.label', default: 'Ben')}"/>
    <g:javascript src="swfobject.js"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-papa" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<h2>Ben's page</h2>

    <table id="tablewrapper">

        <tr>
            <td>
                <div class="greet">  Hello!  My name is Ben, and I am the father of the household.</div>
            </td>
            <td>
                <img src="${resource(dir: 'images', file: 'benWithEyebrow.JPG')}" width="400" height="300" alt="hi"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="emphasize">My daughter, our household technology savant, has recently exploring a handheld
                device called the iPod Touch, which can modify captured images in strange ways.  For better or worse,
                I have acted as the subject for some of her experiments. The results are presented below:</div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table id="tablewrapper2">
                    <tr>
                        <td><img src="${resource(dir: 'images', file: 'big_mouth2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'block_head2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'Morfo2.jpg')}" width="200" height="200" alt="hi"/></td>
                    </tr>
                    <tr>
                        <td><img src="${resource(dir: 'images', file: 'one_eye2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'small_head2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'unicorn2.jpg')}" width="200" height="200" alt="hi"/></td>
                    </tr>
                    <tr>
                        <td><img src="${resource(dir: 'images', file: 'Morfo_flower2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'bearded_lucas.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'foureyes2.jpg')}" width="200" height="200" alt="hi"/></td>
                    </tr>
                    <tr>
                        <td><img src="${resource(dir: 'images', file: 'Fatman.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'ghostparents.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'potato_head.jpg')}" width="200" height="200" alt="hi"/></td>
                    </tr>
                    <tr>
                        <td><img src="${resource(dir: 'images', file: 'sharednose2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'Morfo_isis2.jpg')}" width="200" height="200" alt="hi"/></td>
                        <td><img src="${resource(dir: 'images', file: 'yellowbeard.jpg')}" width="200" height="200" alt="hi"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table id="tablewrapper3">
                    <tr>
                        <td></td>
                        <td></td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td><div class="emphasize"colspan="3">
                             As well as still images, clips with motion are supported...
                        </div></td>
                    </tr>
                    <tr>
                       <td>
                           <table  id="tablewrapper4">
                               <tr>
                                   <td>
                                       <div id="test1">
                                           <p>You need <a href="http://www.adobe.com/go/getflashplayer">Flash Player</a>
                                               installed and JavaScript enabled to play this media.</p>
                                       </div>

                                       <g:flashPlayer id="test1" varFile="${createLinkTo(dir: 'movies', file: 'Take_helicopter.mov')}"/>

                                   </td>
                               </tr>
                               <tr>
                                   <td>
                                       <g:form name="create" action="show">
                                           <input type="hidden" name="movieName" value="Take_helicopter.mov"/>
                                           <g:actionSubmit value="Download movie clip" action="show" params="[movieName:'Take_helicopter.mov']"/>
                                       </g:form>
                                   </td>
                               </tr>
                           </table>
                       </td>
                        <td>
                          <table  id="tablewrapper5">
                              <tr>
                                  <td>
                                      <div id="test2">
                                          <p>You need <a href="http://www.adobe.com/go/getflashplayer">Flash Player</a>
                                              installed and JavaScript enabled to play this media.</p>
                                      </div>

                                      <g:flashPlayer id="test2" varFile="${createLinkTo(dir: 'movies', file: 'Take_0004.mov')}"/>

                                  </td>
                              </tr>
                              <tr>
                                  <td>
                                      <g:form name="create" action="show">
                                          <input type="hidden" name="movieName" value="Take_0004.mov"/>
                                          <g:actionSubmit value="Download movie clip" action="show" params="[movieName:'Take_0004.mov']"/>
                                      </g:form>
                                  </td>
                              </tr>
                          </table>
                        </td>

                        <td>
                            <table  id="tablewrapper6">
                                <tr>
                                    <td>
                                        <div id="test3">
                                            <p>You need <a href="http://www.adobe.com/go/getflashplayer">Flash Player</a>
                                                installed and JavaScript enabled to play this media.</p>
                                        </div>

                                        <g:flashPlayer id="test3" varFile="${createLinkTo(dir: 'movies', file: 'morfo_viking.mp4')}"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <g:form name="create" action="show">
                                            <input type="hidden" name="movieName" value="morfo_viking.mp4"/>
                                            <g:actionSubmit value="Download movie clip" action="show" params="[movieName:'Take_0004.mov']"/>
                                        </g:form>
                                    </td>
                                </tr>
                            </table>
                        </td>



                    </tr>

                </table>
            </td>
        </tr>
        <hr>
        <tr>
            <td colspan="2">
                <div class="emphasize">Technical Blog <a href="./edit/">here</a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="emphasize">Note: if you have come looking for my chemistry portal page, then be advised
                that I have moved it. The page may now be found <a href="/ChemPortal/">here</a></div>
            </td>
        </tr>

     </table>

</body>
</html>
