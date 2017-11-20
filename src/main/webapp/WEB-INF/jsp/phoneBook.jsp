<%@ include file="common/header.jspf" %>
<div class="container">
<h1> Phonebook </h1> <br/>
<div class="container">
<table class="table">
    <tr>
        <td>
        <form method="post">
        <input type="text" name="contact" value="Search by name..."
        onfocus="if(this.value == 'Search by name...') {this.value=''}" onblur="if(this.value == ''){this.value ='Search by name...'}">
        <input type="submit" value="Search" class="btn btn-success">
        &nbsp;
        <a href="/phonebook" class="btn btn-warning">Reset</a>
        &nbsp;
        <a href="/contacts-info" class="btn btn-warning">Add New</a>
        </form>
        </td>
        <td>
        <div class="dropdown">
          <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort by
          <span class="caret"></span></button>
          <ul class="dropdown-menu">
            <li><a href="/phonebook?sort=asc">Name asc</a></li>
            <li><a href="/phonebook?sort=desc">Name desc</a></li>
          </ul>
        </div>
        </td>
    </tr>
</table>
</div>
<table class="table table-striped">
    <thead>
        <th> Id </th>
        <th> Name </th>
        <th> Phone number </th>
        <th> Address </th>
    </thead>
    <tbody>
    <c:forEach items="${phoneBook}" var="contactInfo">
    <tr>
        <td>${contactInfo.id}</td>
        <td>${contactInfo.name}</td>
        <td>${contactInfo.phoneNumber}</td>
        <td>${contactInfo.address}</td>
        <td><a type="button" class="btn btn-success"
                                		href="/phonebook-update?name=${contactInfo.name}">Update</a></td>
        <td><a type="button" class="btn btn-warning"
                        		href="/phonebook-delete?name=${contactInfo.name}">Delete</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<%@ include file="common/footer.jspf" %>