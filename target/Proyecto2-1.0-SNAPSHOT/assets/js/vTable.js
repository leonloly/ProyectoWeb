const template =`
<div class="row">
    <b-col>
        <b-form-group
          label-cols-sm="3"
          label-align-sm="right"
          label-size="sm"
          label-for="filterInput"
          class="float-right"
        >
          <b-input-group size="sm">
            <b-form-input
              v-model="filter"
              type="search"
              placeholder="Buscar"
            ></b-form-input>
            <b-input-group-append>
              <b-button :disabled="!filter" @click="filter = ''">Limpiar</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
     </b-col>

    <b-table 
        responsive   
        hover 
        :fields="fields" 
        :items="data" 
        show-empty
         head-variant="dark" 
        bordered="true"
        show-empty
        small
        stacked="md"
        :current-page="currentPage"
        :per-page="perPage"
        :filter="filter"
    >
        <template v-slot:[ 'cell(' +item.key+ ')' ]="data" v-for="(item,index) in fields">
            <slot :name="item.key" :row="data.item"><span v-text="data.item[item.key]"></span> </slot>
        </template>
    </b-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="data.length"
      :per-page="perPage"
      align="fill"
      size="sm"
      class="my-0"
    ></b-pagination>
<div>
`;

Vue.component("vTable", {
    template,
    props:['fields','data'],
    data:()=>({
        currentPage:0,
        perPage:10,
        filter:"",
    })
});