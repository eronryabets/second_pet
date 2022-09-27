<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>All users : </h5>
    <br><br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Surname</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users?sortBy("surname") as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.surname}</td>
                <td>${user.name}</td>

            </tr>
        <#else >
            No users
        </#list>
        </tbody>
    </table>

</@c.page>