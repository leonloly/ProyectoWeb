<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Estantes
</div>
<div id="vue-app" class="card-body">

    <b-form-group label="Cajones: *" label-for="cajones">
        <b-form-input id="cajon"  v-model="form.cajones"></b-form-input>
        <b-form-invalid-feedback>Numero de cajones</b-form-invalid-feedback>
    </b-form-group>

    <b-form-group label="Sucursales: *" label-for="sucursal">
        <b-form-select name="sucursal" id="sucursal" v-model="form.sucursal" :options="selects.sucursales">
            <template #first>
                <b-form-select-option :value="null" disabled>-- Seleccionar sucursal --</b-form-select-option>
            </template>
        </b-form-select>
        <b-form-invalid-feedback>Seleccionar sucursal</b-form-invalid-feedback>
    </b-form-group>
    <b-button @click="save()" variant="success"   >Guardar</b-button>

    <v-table :fields="table.collums" :data="table.data" >

        <template slot="codigoSucuarsal" slot-scope="{row}" > {{row.codigoSucuarsal.nombre}}</template>

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
                        sucursales: []
                    },
                    form: {},
                    table: {
                        collums: [
                            {key: 'codigo', label: 'Codigo', sortable: true},
                            {key: 'cajones', label: 'Cajones', sortable: true},
                            {key: 'codigoSucuarsal', label: 'Sucursal', sortable: true},
                            {key: 'options', label: 'Opciones', sortable: false}
                        ],
                        data: []
                    }
                }),
            methods: {
                setUpdate(row) {
                    this.form = {
                        cajones: row.cajones,
                        sucursal: row.codigoSucuarsal.codigo,
                        codigo: row.codigo
                    }
                },
                async getEstantes() {
                    const {status, data} = await httpClient.get("/Proyecto2/Estantes-Controller", this.form);
                    this.table.data = data;
                },
                async save() {
                    if (this.valid()) {
                        const {status} = await httpClient.post("/Proyecto2/Estantes-Controller", this.form);
                        console.log(status)
                        if (status) {
                            this.getSucursal();
                            this.form = {};
                        }
                    }
                },
                valid() {
                    if (!this.form.cajones)
                        $("#cajon").addClass('is-invalid');

                    if (!this.form.sucursal)
                        $("#sucursal").addClass('is-invalid');

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
                async getSucursal() {
                    const {status, data} = await httpClient.get("/Proyecto2/Sucursal-controller");
                    if (status) {
                        this.selects.sucursales = data.map(sucursal => ({value: sucursal.codigo, text: sucursal.nombre}));
                    }

                }
            }, mounted() {
                this.getEstantes();
                this.getSucursal();
            }
        })
    })
</script>

<%@ include file = "../template/footer.jsp" %>