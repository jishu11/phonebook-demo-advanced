<%@ include file="common/header.jspf" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
    <h1> Contact Info </h1> <br/>
    <form:form method="post" commandName="contacts">
    <fieldset class="form-group">
    <form:hidden path="id"/>
    <form:label path="name">Name</form:label>
    <form:input path="name" type="text" name="name" class="form-control" required="required" />
    <form:label path="phoneNumber">PhoneNumber</form:label>
    <form:input path="phoneNumber" type="text" name="phoneNo" class="form-control" required="required" />
    <form:label path="address">Address</form:label>
    <form:input path="address" type="text" name="contactAddress" class="form-control" required="required" />
    </fieldset>
    <br/>
    <input type="submit" value="Add/Update" class="btn btn-success">
    </form:form>
    <a href="/phonebook" class="btn btn-warning">Go back</a>
    </table>
    <div>
        <label><font color="red">${isPresent}</font></label>
    </div>
</div>
<%@ include file="common/footer.jspf" %>