<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Paises
</div>
<div id="vue-app" class="card-body">


    <b-form-group label="Nombre: *" label-for="nombre">
        <b-form-input id="name"  v-model="form.nombre"></b-form-input>
        <b-form-invalid-feedback>Nombre perfil</b-form-invalid-feedback>
    </b-form-group>

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
                            {key: 'options', label: 'Opciones', sortable: false}
                        ],
                        data: []
                    }
                }),
            methods: {
                setUpdate(row) {
                    this.form = {
                        nombre: row.nombre,
                        codigo: row.codigo
                    };
                },
                async getperfilUsuario() {
                    const {status, data} = await httpClient.get("/Proyecto2/perfilUsuario-controller", this.form);
                    this.table.data = data;
                },
                async save() {
                    if (this.valid()) {
                        const {status} = await httpClient.post("/Proyecto2/perfilUsuario-controller", this.form);
                        console.log(status);
                        if (status) {
                            this.getperfilUsuario();
                            this.form = {};
                        }
                    }
                },
                valid() {
                    if (!this.form.nombre)
                        $("#name").addClass('is-invalid');

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
                this.getperfilUsuario();
            }
        });
    });
</script>

<%@ include file = "../template/footer.jsp" %>