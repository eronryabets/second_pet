<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>All users : </h5>
    <br>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new User
    </a>
    <br>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/userAdd" method="post">
                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="surname" placeholder="Surname"
                           required="required" minlength = "2"/>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="name" placeholder="Name"
                           required="required" minlength = "1"/>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

    <br>


    <table class="table table-sm table-dark">
        <thead>
        <tr>
            <th>id</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Options</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users?sortBy("id") as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td><a href="/user/edit/${user.id}" class="btn btn-dark">Edit</a>
                <a href="/user/delete/${user.id}" class="btn btn-danger">Delete</a></td>

            </tr>
        <#else >
            No users
        </#list>
        </tbody>
    </table>

</@c.page>