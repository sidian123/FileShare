<template>
  <div class="model-view">
    <!-- eslint-disable -->
    <div class="row"
         v-for="(item,index) in fields"
         v-if="isShow(item.isShow)"
         :key="index"
    >
      <div class="label">{{item.label}}</div>
      <div class="value" v-if="!(item.displayMode&&item.displayMode!=='text')">{{ getFormattedValue(item) }}</div>
      <div class="value" v-else-if="item.displayMode==='html'" v-html="getFormattedValue(item)"></div>
    </div>
    <!-- eslint-enable -->
  </div>
</template>

<script>
  export default {
    name: 'ModelView',
    props:{
      fields:{
        type:Array,
        default(){
          return []
        }
      },
      model:{
        type:Object,
        default(){
          return {}
        }
      }
    },
    methods:{
      isShow(val){
        if(typeof val==='boolean'){
          return val
        }else if(typeof val==='function'){
          return val()
        }else{
          return true
        }
      },
      getFormattedValue(field){
        return field.formatter?field.formatter(this.model[field.prop]):this.model[field.prop]
      }
    }
  }
</script>

<style lang="scss" scoped>
  .model-view{
    .row{
      display: flex;
      align-items: flex-start;
      margin-bottom: 1rem;
      .label{
        flex-basis: 90px;
        flex-shrink: 0;
        color:#99a9bf;
      }
    }
  }
</style>