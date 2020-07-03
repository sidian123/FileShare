import dateFormat from 'date-format'

export default {
  /**
   * 日期格式化
   * @param date 日期
   * @returns {string} yyyy-MM-dd hh:mm:ss类型的日期格式
   */
  dateFormat(date){
    return date === 0 ? '' : dateFormat('yyyy-MM-dd hh:mm:ss', new Date(date))
  },
  /**
   * 将items转化为FormData对象
   * @param {Array} items 所有字段
   */
  toFormData(items){
    let formData = new FormData()
    for(let key in items){
      if(Array.isArray(items[key])){ // 是数组
        for(let obj of items[key]){
          formData.append(key,obj)
        }
      }else{
        formData.append(key,items[key])
      }
    }
    return formData
  }
}

export const dicMap={
  'add':'新增',
  'update':'修改',
  'view':'查看'
}

export const CommonFormRules={
  NotEmpty:[{required: true, message: '不能为空', trigger: 'blur'}]
}