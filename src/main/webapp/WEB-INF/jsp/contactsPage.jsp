<%@ include file="common/header.jspf" %>
<div class="container">
<h1> Contact Info </h1> <br/>
<form method="post">
<table class="table">
 <tr>
     <td> Name: <input type="text" name="name" > </td>
     <td> PhoneNumber: <input type="text" name="phoneNo" > </td>
     <td> Address: <input type="text" name="contactAddress" > </td>
     <td> <input type="submit" value="Update" class="btn btn-success"></td>
</tr>
</form>
</table>
<div>
<label><font color="red">${isPresent}</font></label>
</div>
</div>
<%@ include file="common/footer.jspf" %>