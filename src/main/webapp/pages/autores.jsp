<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Autores
</div>
<div id="vue-app" class="card-body">

    <b-form-group label="Nombre: *" label-for="nombre">
        <b-form-input id="name"  v-model="form.nombre"></b-form-input>
        <b-form-invalid-feedback>Favor completar nombre</b-form-invalid-feedback>
    </b-form-group>

    <b-form-group label="Pais: *" label-for="pais">
        <b-form-select name="pais" id="pais" v-model="form.pais" :options="selects.paises">
            <template #first>
                <b-form-select-option :value="null" disabled>-- Seleccionar Pais --</b-form-select-option>
            </template>
        </b-form-select>
        <b-form-invalid-feedback>Seleccionar Pais</b-form-invalid-feedback>
    </b-form-group>
    
    <b-button @click="save()" variant="success"   >Guardar</b-button>

    <v-table :fields="table.collums" :data="table.data" >

        <template slot="codigoPais" slot-scope="{row}" > {{row.codigoPais.nombre}}</template>

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
                        paises: []
                    },
                    form: {},
                    table: {
                        collums: [
                            {key: 'codigo', label: 'Codigo', sortable: true},
                            {key: 'nombre', label: 'Nombre', sortable: true},
                            {key: 'codigoPais', label: 'Pais', sortable: true},
                            {key: 'options', label: 'Opciones', sortable: false}
                        ],
                        data: []
                    }
                }),
            methods: {
                setUpdate(row) {
                    this.form = {
                        nombre: row.nombre,
                        pais: row.codigoPais.codigo,
                        codigo: row.codigo
                    }
                },
                async getAutores() {
                    const {status, data} = await httpClient.get("/Proyecto2/autores-controller", this.form);
                    this.table.data = data;
                },
                async save() {
                    if (this.valid()) {
                        const {status} = await httpClient.post("/Proyecto2/autores-controller", this.form);
                        console.log(status)
                        if (status) {
                            this.getAutores();
                            this.form = {};
                        }
                    }
                },
                valid() {
                    if (!this.form.nombre)
                        $("#name").addClass('is-invalid');

                    if (!this.form.pais)
                        $("#pais").addClass('is-invalid');

                    $(".is-invalid").change(function () {
                        $(this).removeClass('is-invalid');
                    })

                    if ($(".is-invalid").length > 0) {
                        let firstInvalid = $($(".is-invalid")[0]);
                        firstInvalid.focusWithoutScrolling();
                        return false;
                    }
                    ;
                    return true;
                },
                async getPaises() {
                    const {status, data} = await httpClient.get("/Proyecto2/paises-controller");
                    if (status) {
                        this.selects.paises = data.map(pais => ({value: pais.codigo, text: pais.nombre}));
                    }

                }
            }, mounted() {
                this.getPaises();
                this.getAutores();
            }
        })
    })
</script>

<%@ include file = "../template/footer.jsp" %>