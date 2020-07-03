<template>
  <el-form ref="form"
           :model="formInfo.item"
           label-width="80px"
           :rules="formInfo.rules"
  >
    <slot name="head"></slot>
    <!-- eslint-disable -->
    <el-form-item v-for="(info,index) in formInfo.fields"
                  v-if="isShow(info.isShow)"
                  :key="index"
                  :label="info.label"
                  :prop="info.prop"
    >
      <field-item
          :type="info.type?info.type:'text'"
          :info="info"
          :ref="info.prop"
          v-model="formInfo.item[info.prop]"
      ></field-item>
    </el-form-item>
    <!-- eslint-enable -->
    <slot name="tail"></slot>
  </el-form>
</template>

<script>
  import FieldItem from './FieldItem'
  export default {
    name: 'JyForm',
    components: {FieldItem},
    props:{
      // 表单信息
      formInfo: {
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
    },
    watch:{
      formInfo:{
        handler(val){
          // 查找文件字段
          let fileInfo = val.fields.find(item=>item.type==='file')
          if(fileInfo==null){
            return
          }
          // 校验是否为空
          let fieldValue = val.item[fileInfo.prop]
          if(fieldValue==null || fieldValue.length===0){ // 文件字段无, 或待上传文件数为0
            // 清空文件
            this.$refs[fileInfo.prop][0].clearFiles()
          }
        },
        deep: true
      }
    },
    methods:{
      // 清除校验效果
      clearValidate(){
        if(this.$refs.form){ // 非第一次初始化
          this.$refs.form.clearValidate()
        }
      },
      isShow(val){
        if(typeof val==='boolean'){
          return val
        }else if(typeof val==='function'){
          return val()
        }else{
          return true
        }
      },
      validate(){
        return this.$refs.form.validate()
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>