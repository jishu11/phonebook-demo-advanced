<%@ include file="common/header.jspf" %>
<div class="container">
    <h1> Contact Info </h1> <br/>
    <form method="post">
    <fieldset class="form-group">
    <label>Name</label>
    <input type="text" name="name" class="form-control" required="required">
    <label>PhoneNumber</label>
    <input type="text" name="phoneNo" class="form-control" required="required">
    <label>Address</label>
    <input type="text" name="contactAddress" class="form-control" required="required">
    </fieldset>
    <br/>
    <input type="submit" value="Add/Update" class="btn btn-success">
    </form>
    </table>
    <div>
        <label><font color="red">${isPresent}</font></label>
    </div>
</div>
<%@ include file="common/footer.jspf" %>