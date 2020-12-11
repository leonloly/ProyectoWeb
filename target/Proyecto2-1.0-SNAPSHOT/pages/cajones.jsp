<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Cajones
</div>
<div class="card-body">

    <form>
        <div class="row">
            <div class="form-group col-8">
                <label for="exampleFormControlInput1">Cantidad</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe ">
            </div>
            <div class="form-group col-4">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Estante
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">1</a>
                        <a class="dropdown-item" href="#">2</a>
                        <a class="dropdown-item" href="#">3</a>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"> Guardar</button>
        <button type="reset" class="btn btn-info">Limpiar</button>

    </form>



<table class="table">
    <thead>
        <tr>
            <th scope="col">Codigo</th>
            <th scope="col">Cantidad</th>
            <th scope="col">Estante</th>
            <th scope="col">Options</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">1</th>
            <td>4</td>
            <td>229</td>

            <td>
                <button type="submit" class="btn btn-info"> Editar</button>
                <button type="reset" class="btn btn-danger">Borrar</button> 

            </td>
        </tr>
    </tbody>
</table>
</div>
<%@ include file = "../template/footer.jsp" %>