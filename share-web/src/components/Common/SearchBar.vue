<template>
  <div class="search-bar">
    <el-form
        label-width="80px"
        ref="searchForm"
        :rules="searchInfo.rules"
        :model="searchInfo.item"
    >
      <el-row
          :gutter="24"
          v-for="(row,index) in fieldsPos"
          :key="index"
      >
        <el-col
            :span="6"
            v-for="(pos,index2) in row"
            :key="index2"
        >
          <!--     搜索字段       -->
          <el-form-item
              v-if="pos!==-1"
              :label="searchInfo.fields[pos].label"
              :prop="searchInfo.fields[pos].prop"
          >
            <field-item
                :type="searchInfo.fields[pos].type"
                :info="searchInfo.fields[pos]"
                v-model="searchInfo.item[searchInfo.fields[pos].prop]"
            ></field-item>
          </el-form-item>
          <!--     操作行       -->
          <div v-else class="action-row">
            <el-button type="primary" @click="search">搜 索</el-button>
            <el-button @click="onReset">重 置</el-button>
          </div>
        </el-col>
      </el-row>
    </el-form>
  </div>

</template>

<script>
  import FieldItem from './FieldItem'
  export default {
    name: 'SearchBar',
    components: {FieldItem},
    props:{
      /**
       * 搜索参数
       */
      searchInfo:{
        type:Object,
        default(){
          return {
            // 字段信息
            fields:[],
            // 字段规则
            rules:{},
            // 表单对象
            item:{}
          }
        }
      },
      /**
       * 搜索方法
       */
      search:{
        type:Function,
        default:()=>{}
      }
    },
    computed:{
      /**
       * 字段位置确认
       */
      fieldsPos(){
        // 参数校验
        if(this.searchInfo.fields.length===0){
          return []
        }

        let res=[]
        let row // 当前操作组
        // 遍历
        for(let i=0;i<this.searchInfo.fields.length;i++){
          if(i%4===0){ // 新的一组
            // 新增组
            row=[]
            res.push(row)
          }
          // 添加
          res[res.length-1].push(i)
        }
        // 添加操作列标志
        if(res[res.length-1].length!==4){ // 最后一行未满
          res[res.length-1].push(-1)
        }else{ // 已满
          // 换行标记
          res.push([-1])
        }
        return res
      },
    },
    methods:{
      onReset() {
        if(this.$refs.searchForm){
          this.$refs.searchForm.resetFields()
        }
      },
    }
  }
</script>

<style lang="scss" scoped>

</style>