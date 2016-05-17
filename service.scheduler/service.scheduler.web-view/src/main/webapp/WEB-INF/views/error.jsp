<%@page import="org.springframework.webflow.execution.ActionExecutionException"%>
<%@page import="service.scheduler.core.exceptions.SystemException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags" %>

<c:if test="${not empty errDescription}">
	<table border="0" style="width:75%">
	<caption> <h4><spring:message code="exception.table.title" /> </h4></caption>
		
		<tr>
			<td >
			<button id="info_start_btn">Send message to administrator and go to start</button>
			</td>
		</tr>
		<tr align="left">
			<td>
				${errDescription}
			</td>
		</tr>
			<c:forEach items="${errorMap}" var="errorEntry" varStatus="status">
		<tr align="left">
			        <td>
			      		${errorEntry.value}
			        </td>
		            <%-- <input name="contactMap['${errorEntry.key}']" value="${errorEntry.value}"/></td> --%> 
		</tr>
		    </c:forEach>
		    
	</table>

	<div hidden="true" >
		<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
	
		<c:if test="${exception != null}">
		  <h4>${exception}</h4>
		    <div id="exception_description" align="left">
			  <c:forEach var="stackTraceElem" items="${exception.stackTrace}">
				    <c:out value="${stackTraceElem}"/><br/>
			  </c:forEach>
		    </div>
		</c:if>
	</div>
</c:if>

<script type="text/javascript">
		$(document).ready(function(){
			
			
		    
		    $('#info_start_btn').click( function () {
				var exception_data = $('#exception_description').html();
				//console.log ( stringData );
			    
				$.ajax({
			        type: 'POST',
			        url : '${pageContext.request.contextPath}/ajax/storeexception',
			        contentType: "application/json; charset=utf-8",
			        data: exception_data ,
			        //dataType: 'json',
			        async: false, 
			        processData:false,
			        cache: false,
			        success: function (savingStatus) {
			          // alert("success");
			        },
			        error: function (xhr, ajaxOptions, thrownError) {
			            //alert(thrownError)
			            console.log(thrownError);
			        }
			    });
				
		    	window.location.href = "${pageContext.request.contextPath}/";
		    	
		    } );
		    
		    
			console.log ( 'import contact form loaded' );
		})

</script>