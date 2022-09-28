<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>All cars : </h5>
    <h5><p style="color:red">${message!}</p></h5>
    <br>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Car
    </a>
    <br>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/show_cars" method="post">
                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="carBrand" placeholder="car brand"
                           required="required" minlength = "1"/>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="carModel" placeholder="car model"
                           required="required" minlength = "1"/>
                </div>

                <div class="form-group">
                    <input type="number" class="form-control col-sm-4" name="year" placeholder="car year"
                           required="required" minlength = "4"/>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control col-sm-4" name="carNumber" placeholder="car number"
                           required="required" minlength = "1"/>
                </div>

                <div class="form-group">
                    <input type="number" class="form-control col-sm-4" name="ownerId" placeholder="car owner"
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
                <td><#if (car.user.surname)??>${car.user.id}<#else> Hasn't owner</#if></td>
                <td>${(car.user.id)!}</td>

                <td><a href="/car/edit/${car.id}" class="btn btn-dark">Edit</a></td>

            </tr>
        <#else >
            No cars
        </#list>
        </tbody>
    </table>

</@c.page>