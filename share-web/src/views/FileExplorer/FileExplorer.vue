<template>
  <div class="file-explorer">
    <el-table :data="tableData">
      <el-table-column prop="name" label="文件名" min-width="120">
        <template v-slot="{row}">
          <i class="iconfont" :class="getIcon(row.type)"></i>
          {{row.name}}
        </template>
      </el-table-column>
      <el-table-column prop="size" label="大小" min-width="50"></el-table-column>
      <el-table-column prop="modifiedTime" label="修改日期" min-width="50">
        <template v-slot="{row}">{{dateFormat(row.modifiedTime)}}</template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import api from "./api";
  import JyTable from "../../components/Common/JyTable";
  import utils from "../../components/Common/utils";

  export default {
    name: "FileExplorer",
    components: {JyTable},
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
        this.tableData = await api.list();
      },
      async addDir(){
        let name=(await this.$prompt('请输入目录名','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /\w+/,
          inputErrorMessage: '目录名不正确'
        })).value
        await api.mkdir(name);
        this.$notify.success('新增成功')
        await this.refreshTable();
      },
      async addFile(){
        let name=(await this.$prompt('请输入文件名','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /\w+/,
          inputErrorMessage: '文件名不正确'
        })).value
        await api.touch(name);
        this.$notify.success('新增成功')
        await this.refreshTable();
      },
      dateFormat:utils.dateFormat,
      getIcon(type){
        if(type==='file'){
          return 'icon-file'
        }else if(type==='directory'){
          return 'icon-folder'
        }
        return '';
      }
    },
  }
</script>

<style lang="scss" scoped>
  .file-explorer{
    .table{
      min-height: 700px;
    }
  }
</style>