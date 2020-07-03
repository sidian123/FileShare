# FieldItem

* 表示表单字段

* 支持`v-model`

------------

属性如下

* `type ` 字段类型
  
  * text 单行文本
  * textarea 多行文本
  * number 数字
  * select 仅选择
  * select-remote 远程选择,不可创建条目
  * select-remote-create 远程选择,可创建条目
  
* `info ` 字段详细信息

    `type`的不同取值, 所对应的不同`info`属性格式:
    
    * 通用字段
        * `placeholder` 占位符, 默认`undefined`

    * `text` 单行文本

    * `textarea` 多行文本

    * `number` 数字

    * `select-remote-create` 远程选择,可创建条目

        `info`属性示例

        ```
        {
            getOptions:query=>{...}, // 查询选项的方法, 支持异步. this指向当前描述对象, 参数query为查询内容, 返回值格式[{label,value},{...},...]
            multiple:false, // 是否可多项选择, 默认false
            loading:false, // 组件加载状态, 默认false
            options:[] // 组件可选项, 默认[]
        }
        ```
        
    * `select-remote` 远程选择,不可创建条目

        ```
        {
            getOptions, // 查询选项的方法, 支持异步. this指向当前描述对象, 参数为查询内容, 返回值格式[{label,value},{...},...]
            multiple:false, // 是否可多项选择, 默认false
            loading:false, // 组件加载状态, 默认false
            options:[] // 组件可选项, 默认[]
        }
        ```
        
    * `select` 仅选择
    
        ```
        {
        	options:[
    		{label:'男',value:0},
        		{label:'女',value:1}
            ]
        }
        ```
    
    * `file` 文件
    
        ```
        {
            multiple:false, // 是否多文件上传, 默认false
            limit:1, // 最大上传数量, 默认1
            handleFileExceed: ()=>{} // 文件上传数量超出处理器, 默认提示
        }
        ```

# Form

* `formInfo`

    ```
    {
        // 字段信息
        fields:[],
        // 字段规则
        rules:{},
        // 表单对象
        item:{}
    }
    ```

* `fields`

    ```
    [
        {
            // 显示标签
            label: '姓名',
            // 字段名
            prop: 'name',
            // 字段类型, 默认text
            type: 'text',
            // 是否显示, 默认true. 类型: Boolean|Function
            isShow: true,
            // 其他属于FieldItem的字段
            ...
        },
        ...
    ]
    ```

# Table

* slot
  
    * `default` 自定义行的操作, 参数为`{row}`
    
    * `toolbar` 自定义工具栏的内容
    
* event
  
    `pageInfoChange` 分页信息改变时触发, 参数为`pageInfo`
    
* 属性
  
    * `tableInfo` 表格信息
    
        格式如下
        ```
        {
            //表格数据
            data: [],
            // 列信息
            columns: []
        }
        ```
    
    * `columns` 列信息
    
        ```
        [
            {
                // 显示标签
                label: '姓名',
                // 字段名
                prop: 'name',
                // 显示宽度, 默认''
                width: '50px',
                // 格式化器(暂且支持同步), 默认null
                formatter: function(val){...},
                // 是否显示该列, 默认true. 仅配合isExpand使用
                // false时, 该字段仅在行展开时显示
                isShow:true,
            },
            ...
        ]
        ```
      
    * `pageInfo` 分页信息
    
        格式如下
        ```
        {
            pageNum: 1, // 页码
            pageSize: 10, // 每页大小,
            total: 0, // 总页数
        }
        ```
    


# ModelView

* `fields` 字段信息
  
    格式如下
    ```
    [
        {
            label:'姓名',
            prop:'name',
            // 格式化器, 默认null
            formatter: null,
            // 是否显示, 默认true. 类型: Boolean|Function
            isShow: true,
            // 显示模式, 默认text, 可选值text|html
            displayMode:'text'
        },
        ...
    ]
    ```


























