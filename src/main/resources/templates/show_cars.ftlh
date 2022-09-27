<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>All cars : </h5>
    <br><br>

    <table class="table table-sm table-dark">
        <thead>
        <tr>
            <th>id</th>
            <th>Car brand</th>
            <th>Model</th>
            <th>Year</th>
            <th>Car number</th>
            <th>Car owner</th>
            <th>Car owner id</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list cars?sortBy("id")as car>
            <tr>
                <td>${car.id}</td>
                <td>${car.carBrand}</td>
                <td>${car.model}</td>
                <td>${car.year}</td>
                <td>${car.carNumber}</td>
                <td>${car.user.surname}</td>
                <td>${car.user.id}</td>
                <td><a href="/car/edit/${car.id}" class="btn btn-dark">Edit</a></td>

            </tr>
        <#else >
            No cars
        </#list>
        </tbody>
    </table>

</@c.page>