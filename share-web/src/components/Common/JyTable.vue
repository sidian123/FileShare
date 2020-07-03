<template>
  <div class="table">
    <div class="operation-wrap">
      <slot name="toolbar">
        <el-button type="primary" @click="action.add">新 增</el-button>
      </slot>
    </div>
    <div class="table-wrap">
      <el-table :data="tableInfo.data" border stripe tooltipEffect="light" height="100%">
        <!--  要张开的列  -->
        <el-table-column type="expand" v-if="isExpand">
          <template slot-scope="{row}">
            <model-view :fields="tableInfo.columns" :model="row"></model-view>
          </template>
        </el-table-column>
        <!-- 序号列 -->
        <el-table-column v-if="hasOrder" label="序号" type="index" width="50px" :index="handleIndex"/>
        <!-- 正常列 -->
        <!-- eslint-disable -->
        <el-table-column
            v-for="(column,index) in tableInfo.columns"
            v-if="!(isExpand && !(column.isShow!=null || !column.isShow))"
            :key="index"
            :prop="column.prop"
            :label="column.label"
            :width="column.width?column.width:''"
            show-overflow-tooltip
        >
          <template v-if="column.formatter" #default="{row}">
            {{column.formatter(row[column.prop])}}
          </template>
        </el-table-column>
        <!-- eslint-enable -->
        <!-- 操作列 -->
        <el-table-column v-if="hasRowAction" label="操作" :width="actionColumnWidth" fixed="right">
          <template #default="{row}">
            <slot :row="row">
              <el-button type="text" size="mini" @click="action.update(row)">修改</el-button>
              <el-button type="text" size="mini" @click="action.remove(row)">删除</el-button>
            </slot>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-wrap">
      <el-pagination
          @size-change="onPageInfoChange"
          @current-change="onPageInfoChange"
          :current-page.sync="pageInfo.pageNum"
          :page-size.sync="pageInfo.pageSize"
          :total="pageInfo.total"
          :page-sizes="[10, 20, 30, 40]"
          layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
  import ModelView from './ModelView'
  export default {
    name: 'JyTable',
    components: {ModelView},
    props: {
      // 是否有序号
      hasOrder: {
        type: Boolean,
        default: true,
      },
      // 行是否可以展开
      isExpand: {
        type: Boolean,
        default: false
      },
      actionColumnWidth:{
        type:String,
        default:'100px'
      },
      // 表格信息
      tableInfo: {
        type: Object,
        default() {
          return {
            //表格数据
            data: [],
            // 列信息
            columns: []
          }
        }
      },
      // 分页信息
      pageInfo:{
        type:Object,
        default(){
          return {
            pageNum: 1, // 页码
            pageSize: 10, // 每页大小,
            total: 0, // 总页数
          }
        }
      },
      // 是否使用默认按钮
      useDefaultButton:{
        type:Boolean,
        default:true
      },
      // 默认按钮的对应的操作
      action:{
        type:Object,
        default() {
          return {
            // 添加操作
            add:()=>{},
            // 更新操作
            update:()=>{},
            // 删除操作
            remove:()=>{},
          }
        }
      },
      // 是否有操作, 默认true
      hasRowAction:{
        type:Boolean,
        default:true
      }
    },
    methods: {
      /**
       * 表格序号生成器
       */
      handleIndex(index) {
        return (this.pageInfo.pageNum - 1) * this.pageInfo.pageSize + index + 1
      },
      /**
       * 分页信息该表
       */
      onPageInfoChange(){
        this.$emit('pageInfoChange',this.pageInfo)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .table {
    flex-grow: 1;
    display: flex;
    flex-direction: column;

    .operation-wrap{
      padding:16px 0;
    }

    .table-wrap {
      flex-grow: 1;
    }
    .pagination-wrap {
      text-align: right;
    }
  }
</style>