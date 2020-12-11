<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Usuario
</div>
<div class="card-body">

    <form>

        <div class="row">
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Nombre</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Apellido</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
            <div class="form-group col-4">
                <label for="exampleFormControlInput1">Carnet</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Escribe aqui">
            </div>
        </div>
        <button type="submit" class="btn btn-primary"> Guardar</button>
        <button type="reset" class="btn btn-info">Limpiar</button>

    </form>



    <table class="table">
        <thead>
            <tr>
                <th scope="col">Codigo</th>
                <th scope="col">Nombre</th>
                <th scope="col">Apellido</th>
                <th scope="col">Carnet</th>
                <th scope="col">Options</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Juan</td>
                <td>Gomez</td>
                <td>JG100985</td>
                <td>
                     <button type="submit" class="btn btn-info"> Editar</button>
        <button type="reset" class="btn btn-danger">Borrar</button> 
                    
                </td>
            </tr>
        </tbody>
    </table>
</div>
<%@ include file = "../template/footer.jsp" %>
