// vue.config.js
module.exports = {
  devServer:{
    port:8000,
    //配置代理
    proxy:{
      //拦截的url
      '/api':{
        //转发到目标服务器的url
        target:"http://localhost:8080",
        //是否修改Host头部，可选
        changeOrigin:true,
        //修改请求路径,支持正则
        pathRewrite:{'/api':''},
      }
    }
  }
}
