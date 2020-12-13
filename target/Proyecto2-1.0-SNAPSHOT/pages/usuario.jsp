<%@ include file = "../template/header.jsp" %>
<div class="card-header">
    Usuario
</div>
<div id="vue-app" class="card-body">

    <div class="row">
        <div class="form-group col-3">
            <b-form-group label="Nombre: *" label-for="nombre">
                <b-form-input id="name"  v-model="form.nombre"></b-form-input>
                <b-form-invalid-feedback>Nombre</b-form-invalid-feedback>
            </b-form-group>
        </div>
        <div class="form-group col-3">
            <b-form-group label="Apellido *" label-for="apellido">
                <b-form-input id="apellido"  v-model="form.apellido"></b-form-input>
                <b-form-invalid-feedback>Apelldio</b-form-invalid-feedback>
            </b-form-group>
        </div>
        <div class="form-group col-3">   
            <b-form-group label="Carnet: *" label-for="carnet:">
                <b-form-input id="carnet:"  v-model="form.carnet:"></b-form-input>
                <b-form-invalid-feedback>Carnet:</b-form-invalid-feedback>
            </b-form-group>
        </div>
        <div class="form-group col-3">   
            <b-form-group label="Password: *" label-for="password:">
                <b-form-input id="password:"  v-model="form.password:"></b-form-input>
                <b-form-invalid-feedback>Password:</b-form-invalid-feedback>
            </b-form-group>
        </div>
    </div>

    <b-form-group label="Perfil usuario*" label-for="perfil">
        <b-form-select name="perfil" id="perfil" v-model="form.perfil" :options="selects.perfiles">
            <template #first>
                <b-form-select-option :value="null" disabled>-- Seleccionar perfil --</b-form-select-option>
            </template>
        </b-form-select>
        <b-form-invalid-feedback>Seleccionar perfil</b-form-invalid-feedback>
    </b-form-group>
    
    <b-button @click="save()" variant="success"   >Guardar</b-button>

    <v-table :fields="table.collums" :data="table.data" >

        <template slot="codigo_perfil" slot-scope="{row}" > {{row.codigo_perfil.nombre}}</template>

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
                        perfiles: []
                    },
                    form: {},
                    table: {
                        collums: [
                            {key: 'codigo', label: 'Codigo', sortable: true},
                            {key: 'nombre', label: 'Nombre', sortable: true},
                            {key: 'apellido', label: 'Apellido', sortable: true},
                            {key: 'carnet', label: 'Carnet', sortable: true},
                            {key: 'password', label: 'Password', sortable: true},
                            {key: 'codigo_perfil', label: 'Perfil', sortable: true},
                            {key: 'options', label: 'Opciones', sortable: false}
                        ],
                        data: []
                    }
                }),
            methods: {
                setUpdate(row) {
                    this.form = {
                        nombre: row.nombre,
                        apellido: row.apellido,
                        carnet: row.carnet,
                        password: row.password,
                        perfil row.codigo_perfil.codigo,
                        codigo: row.codigo
                    }
                },
                async getUsuarioController() {
                    const {status, data} = await httpClient.get("/Proyecto2/UsuarioController", this.form);
                    this.table.data = data;
                },
                async save() {
                    if (this.valid()) {
                        const {status} = await httpClient.post("/Proyecto2/UsuarioController", this.form);
                        console.log(status)
                        if (status) {
                            this.getUsuarioController();
                            this.form = {};
                        }
                    }
                },
                valid() {
                    if (!this.form.nombre)
                        $("#name").addClass('is-invalid');
                    if (!this.form.apellido)
                        $("#name").addClass('is-invalid');
                    if (!this.form.carnet)
                        $("#name").addClass('is-invalid');
                    if (!this.form.password)
                        $("#name").addClass('is-invalid');

                    if (!this.form.perfil)
                        $("#perfil").addClass('is-invalid');

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
                async getperfiles() {
                    const {status, data} = await httpClient.get("/Proyecto2/perfilUsuario-controller");
                    if (status) {
                        this.selects.perfiles = data.map(perfil => ({value: perfil.codigo, text: perfil.nombre}));
                    }

                }
            }, mounted() {
                this.getperfiles();
                this.getUsuarioController();
            }
        })
    })
</script>

<%@ include file = "../template/footer.jsp" %>