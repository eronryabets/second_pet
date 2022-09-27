<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>All cars : </h5>
    <br><br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>id</th>
            <th>Car brand</th>
            <th>Model</th>
            <th>Year</th>
            <th>Car number</th>
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

            </tr>
        <#else >
            No cars
        </#list>
        </tbody>
    </table>

</@c.page>