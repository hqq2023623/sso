<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="includes/top.jsp"/>

<%--<c:if test="${not pageContext.request.secure}">
  <div id="msg" class="errors">
    <h2>Non-secure Connection</h2>
    <p>You are currently accessing CAS over a non-secure connection.  Single Sign On WILL NOT WORK.  In order to have single sign on work, you MUST log in over HTTPS.</p>
  </div>
</c:if>--%>

<div class="container" id="login">
    <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true" class="form-signin">

        <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false"/>

        <h2 class="form-signin-heading">
          <spring:message code="screen.welcome.instructions" />
        </h2>
        <label for="username" class="sr-only"><spring:message code="screen.welcome.label.netid"/></label>
        <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey"/>
        <form:input cssClass="required form-control" cssErrorClass="error" id="username" size="25" tabindex="1"
                    accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true"
                    />
        <br/>
        <label for="password" class="sr-only"><spring:message code="screen.welcome.label.password"/></label>
        <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey"/>
        <form:password cssClass="required form-control" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"
                       accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off"/>
        <%--验证码--%>
        <form:input cssClass="required form-control-code" cssErrorClass="error" id="captcha" tabindex="3" path="captcha"
                    placeholder="captcha" accesskey="${captchaAccessKey}" autocomplete="off" htmlEscape="true"/>
        <img id="imgObj" src="captcha.jpg" onclick="this.src='captcha.jpg?rnd='+Math.random();">
        <br/>
        <br/>

        <input type="hidden" name="lt" value="${loginTicket}"/>
        <input type="hidden" name="execution" value="${flowExecutionKey}"/>
        <input type="hidden" name="_eventId" value="submit"/>

        <input name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4"
               type="submit" class="btn btn-lg btn-primary btn-block"/>

    </form:form>
</div>

<%--<div id="sidebar">
  <div class="sidebar-content">
    <p><spring:message code="screen.welcome.security" /></p>

    <div id="list-languages">
      <%final String queryString = request.getQueryString() == null ? "" : request.getQueryString().replaceAll("&locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]|^locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]", "");%>
      <c:set var='query' value='<%=queryString%>' />
      <c:set var="xquery" value="${fn:escapeXml(query)}" />

      <h3>Languages:</h3>

      <c:choose>
        <c:when test="${not empty requestScope['isMobile'] and not empty mobileCss}">
          <form method="get" action="login?${xquery}">
            <select name="locale">
              <option value="en">English</option>
              <option value="zh_CN">Chinese (Simplified)</option>
            </select>
            <input type="submit" value="Switch">
          </form>
        </c:when>
        <c:otherwise>
          <c:set var="loginUrl" value="login?${xquery}${not empty xquery ? '&' : ''}locale=" />
          <ul>
            <li class="first"><a href="${loginUrl}en">English</a></li>
            <li><a href="${loginUrl}zh_CN">Chinese (Simplified)</a></li>
          </ul>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>
  --%>

<%--
<jsp:directive.include file="includes/bottom.jsp" />--%>

