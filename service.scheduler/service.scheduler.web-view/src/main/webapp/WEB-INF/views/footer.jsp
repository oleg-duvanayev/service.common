<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
Copyright &copy; rocor<br />
<h6><spring:message code="system.environment.title" /> <% out.print(System.getProperty("common.service.env")); %>
