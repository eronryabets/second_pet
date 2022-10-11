<#import "parts/common.ftlh" as c>
<#import "spring.ftl" as spring />
<@c.page>

    <h5>ID : ${user.id} ; Surname : ${user.surname} </h5>

<form action="/user/edit/${user.id}" method="post">
    <div class="container">
        <br>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Surname: </label>
            <div class="col-sm-3">
                <@spring.bind "user.surname" />
                <input type="text" name="surname"
                       class="form-control <#if (spring.status.isError())>is-invalid<#else>is-valid</#if>"
                       value="${user.surname}"/>

                <div class="invalid-feedback">
                    <@spring.bind "user.surname" />
                    <@spring.showErrors "<br>"/>
                </div>

            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Name: </label>
            <div class="col-sm-3">
                <@spring.bind "user.name" />
                <input type="text" name="name"
                       class="form-control <#if (spring.status.isError())>is-invalid<#else>is-valid</#if>"
                       value="${user.name}"/>

                <div class="invalid-feedback">
                    <@spring.bind "user.name" />
                    <@spring.showErrors "<br>"/>
                </div>

            </div>
        </div>

    </div>

    <input type="hidden" name="user" value="${user.id}">

    <button class="btn btn-primary" type="submit">Save</button>
    <br><br>
    <a href="/show_users"
       class="btn btn-dark"> Back
    </a>


</@c.page>