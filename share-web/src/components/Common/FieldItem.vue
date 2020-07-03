<template>
  <el-input v-if="type==='text'"
            :value="value"
            @input="onInput"
            :placeholder="info.placeholder"
  ></el-input>
  <el-input v-else-if="type==='textarea'"
            :value="value"
            @input="onInput"
            type="textarea"
            :placeholder="info.placeholder"
  ></el-input>
  <el-input-number v-else-if="type==='number'"
                   :value="value"
                   @input="onInput"
                   :min="1"
                   :max="10"
  ></el-input-number>
  <el-select v-else-if="type==='select'"
             :value="value"
             @input="onInput"
             filterable
             clearable
             :placeholder="info.placeholder"
  >
    <el-option
        v-for="option in info.options"
        :key="option.value"
        :label="option.label"
        :value="option.value"
        :disabled="option.disabled"
    ></el-option>
  </el-select>
  <el-select v-else-if="type==='select-remote-create'"
             :value="value"
             @input="onInput"
             remote
             filterable
             clearable
             allow-create
             default-first-option
             :placeholder="info.placeholder"
             :multiple="info.multiple"
             :remote-method="query=>remoteMethod(query,info,info.getOptions)"
             :loading="info.loading">
    <el-option
        v-for="option in info.options"
        :key="option.value"
        :label="option.label"
        :value="option.value">
    </el-option>
  </el-select>
  <el-select v-else-if="type==='select-remote'"
             :value="value"
             @input="onInput"
             remote
             filterable
             :placeholder="info.placeholder"
             clearable
             :multiple="info.multiple"
             :remote-method="query=>remoteMethod(query,info,info.getOptions)"
             :loading="info.loading">
    <el-option
        v-for="option in info.options"
        :key="option.value"
        :label="option.label"
        :value="option.value">
    </el-option>
  </el-select>
  <el-upload
      v-else-if="type==='file'"
      action=""
      ref="file"
      :multiple="info.multiple==null?false:info.multiple"
      :limit="info.limit?info.limit:1"
      :auto-upload="false"
      :on-change="handleFileChange"
      :on-remove="handleFileChange"
      :on-exceed="info.handleFileExceed?info.handleFileExceed:handleFileExceed"
  >
    <el-button type="primary">添加文件</el-button>
  </el-upload>
</template>

<script>

  export default {
    name: 'FieldItem',
    props:{
      /**
       * 字段类型
       * text 单行文本
       * textarea 多行文本
       * number 数字
       * select-remote-create 远程选择,可创建条目
       * select-remote 远程选择,不可创建条目
       * select 仅选择
       */
      type:{
        type:String,
        default:'text'
      },
      /**
       * 字段描述
       */
      info:{
        type:Object,
        default(){
          return {}
        }
      },
      value:{}
    },
    methods:{
      onInput(value){
        this.$emit('input',value)
      },
      /**
       * 远程搜索, 用于select组件
       * @param query 查询内容
       * @param info 字段描述对象
       * @param callback 获取数据的回调方法, this指向字段描述对象, 参数为搜索内容, 结果对象格式:[{label,value},{...},...]
       * @returns {Promise<void>}
       */
      // eslint-disable-next-line no-unused-vars
      async remoteMethod(query,info,callback=query=>[]){
        // 保证参数存在
        if(info.options==null){ // 不存在
          this.$set(info,'options',[])
        }
        if(info.loading==null){
          this.$set(info,'loading',false)
        }
        // 查询
        if(query===''){
          info.options=[]
          return
        }
        info.loading=true
        info.options = await callback.call(info,query)
        info.loading=false
      },
      handleFileExceed(){
        if(!this.info.limit){
          this.info.limit=1
        }
        this.$message.warning(`一次仅能上传${this.info.limit}个文件`)
      },
      handleFileChange(file, fileList){
        this.$emit('input',fileList.map(item=>item.raw))
      },
      clearFiles(){
        this.$refs.file.clearFiles()
      }
    }
  }
</script>

<style lang="scss" scoped>
  .el-select{
    width:100%;
  }
</style>