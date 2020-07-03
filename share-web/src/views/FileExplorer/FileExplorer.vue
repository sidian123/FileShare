<template>
  <div class="file-explorer">

  </div>
</template>

<script>
  import api from "./api";

  export default {
    name: "FileExplorer",
    data(){
      return{
        tableData:[],
      }
    },
    created() {
      this.refreshTable();
    },
    methods:{
      async refreshTable(){
        this.tableData=await api.list();
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>