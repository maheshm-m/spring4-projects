<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url var="list" value="/" />

<br />

<form:form id="employeeForm" action="${list}" method="post"
  modelAttribute="employee">
  <div>
    <label id="label1">Id:</label>
    <form:input id="id" path="id" maxLength="10" />
    <br />
    <label id="label1">Name:</label>
    <form:input id="name" path="name" maxLength="50" />
  </div>
  <br />
  <br />
  <div>
    <input type="submit" name="ok" value="Ok"> &nbsp;
    <input type="submit" name="cancel" value="Cancel" />
  </div>
</form:form>
