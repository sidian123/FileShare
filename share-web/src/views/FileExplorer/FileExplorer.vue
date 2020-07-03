<template>
  <div class="file-explorer">
    <jy-table class="table" :table-info="tableInfo" :page-info="pageInfo" @pageInfoChange="refreshTable">
      <template v-slot:toolbar>
        <el-button type="primary" size="small" @click="addDir">新增目录</el-button>
        <el-button type="primary" size="small" @click="addFile">新增文件</el-button>
      </template>
    </jy-table>
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
        tableInfo:{
          data:[],
          columns:[
            {label:"名字",prop:"name"},
            {label:'修改时间',prop:'modifiedTime',formatter:utils.dateFormat},
            {label:'创建时间',prop:'createdTime',formatter: utils.dateFormat},
            {label:"类型",prop:"type"},
          ]
        },
        pageInfo:{
          pageNum: 1, // 页码
          pageSize: 10, // 每页大小,
          total: 0, // 总页数
        }
      }
    },
    created() {
      this.refreshTable();
    },
    methods:{
      async refreshTable(){
        let list = await api.list();
        this.tableInfo.data=utils.fakePaging(list,this.pageInfo);
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
      }
    }
  }
</script>

<style lang="scss" scoped>
  .file-explorer{
    .table{
      min-height: 700px;
    }
  }
</style>