<#import "parts/common.ftlh" as c>
<@c.page>

    <h5>ID : ${car.id} ; brand : ${car.carBrand} ; model : ${car.model} ; car number : ${car.carNumber} </h5>
    <h5>Owner : <#if (car.user.surname)??>${car.user.surname}<#else> Didn't have owner</#if>
        ; Owner's id :  <#if (car.user.id)??>${car.user.id}<#else> none </#if> </h5>

    <h5><p style="color:red">${message!}</p></h5>

    <form action="/saveCar/${car.id}" method="post">
        <div class="container">
            <br>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Brand: </label>
                <div class="col-sm-3">
                    <input type="text" name="carBrand" class="form-control" value="${car.carBrand}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Model: </label>
                <div class="col-sm-3">
                    <input type="text" name="carModel" class="form-control" value="${car.model}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Year: </label>
                <div class="col-sm-3">
                    <input type="number" name="year" class="form-control" placeholder="${car.year!}"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Car number: </label>
                <div class="col-sm-3">
                    <input type="text" name="carNumber" class="form-control" value="${car.carNumber}"/>

                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Owner ID: </label>
                <div class="col-sm-3">
                    <input type="number" name="ownerId" class="form-control" value="${(car.user.id)!}"/>
                </div>
            </div>



        </div>

        <input type="hidden" name="carId" value="${car.id}">

        <button class="btn btn-primary" type="submit">Save</button>
        <br><br>
        <a href="/show_cars"
           class="btn btn-dark"> Back
        </a>

    </form>
    <br><br>

    <form action="/carSave" method="post">

    </form>

</@c.page>