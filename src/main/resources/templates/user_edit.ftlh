<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>ID : ${user.id} ; Surname : ${user.surname} </h5>

    <form action="/userSave" method="post">
        <div class="container">
            <br>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Surname: </label>
                <div class="col-sm-3">
                    <input type="text" name="surname" class="form-control" value="${user.surname}"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Name: </label>
                <div class="col-sm-3">
                    <input type="text" name="name" class="form-control" value="${user.name}"/>
                </div>
            </div>

        </div>

        <input type="hidden" name="userId" value="${user.id}">

        <button class="btn btn-primary" type="submit">Save</button>
        <br><br>
        <a href="/show_users"
           class="btn btn-dark"> Back
        </a>

    </form>
    <br><br>

    <form action="/userSave" method="post">

    </form>

</@c.page>