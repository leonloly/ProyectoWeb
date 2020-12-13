<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Proveedor
</div>
<div id="vue-app" class="card-body">
    <div class="row">
        <div class="form-group col-4">
            <b-form-group label="Nombre: *" label-for="nombre">
                <b-form-input id="name"  v-model="form.nombre"></b-form-input>
                <b-form-invalid-feedback>Nombre del proveedor</b-form-invalid-feedback>
            </b-form-group>
        </div>
        <div class="form-group col-4">
            <b-form-group label="Telefono: *" label-for="telefono">
                <b-form-input id="phone"  v-model="form.telefono"></b-form-input>
                <b-form-invalid-feedback>Telefono</b-form-invalid-feedback>
            </b-form-group>
        </div>
        <div class="form-group col-4">   
            <b-form-group label="Correo: *" label-for="correo">
                <b-form-input id="mail"  v-model="form.correo"></b-form-input>
                <b-form-invalid-feedback>Correo</b-form-invalid-feedback>
            </b-form-group>
        </div>
    </div>

    <b-button @click="save()" variant="success" >Guardar</b-button>

    <v-table :fields="table.collums" :data="table.data" >

        <template #options="{row}"> 

            <b-button @click="setUpdate(row)"  variant="success" size="sm">
                <span class="fas fa-pencil-alt"></span> Editar
            </b-button>

            <b-button variant="danger" size="sm">
                <span class="fas fa-trash-alt"></span> Borrar
            </b-button>

        </template>
    </v-table>
</div>

<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        new Vue({
            el: "#vue-app",
            data: () => ({
                    selects: {
                    },
                    form: {},
                    table: {
                        collums: [
                            {key: 'codigo', label: 'Codigo', sortable: true},
                            {key: 'nombre', label: 'Nombre', sortable: true},
                            {key: 'telefono', label: 'Telefono', sortable: true},
                            {key: 'correo', label: 'Correo', sortable: true},
                            {key: 'options', label: 'Opciones', sortable: false}
                        ],
                        data: []
                    }
                }),
            methods: {
                setUpdate(row) {
                    this.form = {
                        nombre: row.nombre,
                        telefono: row.telefono,
                        correo: row.correo,
                        codigo: row.codigo
                    };
                },
                async getProveedor() {
                    const {status, data} = await httpClient.get("/Proyecto2/Proveedores-controller", this.form);
                    this.table.data = data;
                },
                async save() {
                    if (this.valid()) {
                        const {status} = await httpClient.post("/Proyecto2/Proveedores-controller", this.form);
                        console.log(status);
                        if (status) {
                            this.getproveedor();
                            this.form = {};
                        }
                    }
                },
                valid() {
                    if (!this.form.nombre)
                        $("#name").addClass('is-invalid');
                    if (!this.form.telefono)
                        $("#phone").addClass('is-invalid');
                    if (!this.form.correo)
                        $("#mail").addClass('is-invalid');

                    $(".is-invalid").change(function () {
                        $(this).removeClass('is-invalid');
                    });

                    if ($(".is-invalid").length > 0) {
                        let firstInvalid = $($(".is-invalid")[0]);
                        firstInvalid.focusWithoutScrolling();
                        return false;
                    }
                    ;
                    return true;
                }
            }, mounted() {
                this.getProveedor();
            }
        });
    });
</script>

<%@ include file = "../template/footer.jsp" %>