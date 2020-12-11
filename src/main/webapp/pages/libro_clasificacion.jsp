<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Libro clasificacion
</div>
<div class="card-body">

    <form>

        <div class="row">
            <div class="form-group col-6">
                <label for="exampleFormControlInput1">Nombre de la clasificacion</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Nombre de la clasificacion">
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
                <th scope="col">Options</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Suspenso</td>
                <td>
                     <button type="submit" class="btn btn-info"> Editar</button>
        <button type="reset" class="btn btn-danger">Borrar</button> 
                    
                </td>
            </tr>
        </tbody>
    </table>
</div>
<%@ include file = "../template/footer.jsp" %>
