<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Libro
</div>
<div class="card-body">

    <form>

        <div class="row">
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Titulo</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Etiqueta</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Publicado</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Edicion</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">ISBN</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Precio renta</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="form-group col-4">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Clasificacion
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </div>
            </div>
            <div class="form-group col-4">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Autor
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </div>
            </div>
            <div class="form-group col-4">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Tipo
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <button type="submit" class="btn btn-primary"> Guardar</button>
        <button type="reset" class="btn btn-info">Limpiar</button>

    </form>



    <table class="table">
        <thead>
            <tr>
                <th scope="col">Codigo</th>
                <th scope="col">Titulo</th>
                <th scope="col">Etiqueta</th>
                <th scope="col">Publicado</th>
                <th scope="col">Edicion</th>
                <th scope="col">ISBN</th>
                <th scope="col">Precio renta</th>
                <th scope="col">Options</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">1</th>
                <td>principito</td>
                <td>dew</td>
                <td>2011-01-01</td>
                <td>1</td>
                <td>d3de33</td>
                <td>19.99</td>
                <td>
                    <button type="submit" class="btn btn-info"> Editar</button>
                    <button type="reset" class="btn btn-danger">Borrar</button> 

                </td>
            </tr>
        </tbody>
    </table>
</div>
<%@ include file = "../template/footer.jsp" %>

