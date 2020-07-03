import axios from "axios";

function init(vueInstance){
  axios.interceptors.response.use(function (response) {
    let apiResponse=response.data;
    if(apiResponse.code==="ok") {
      return response.data.data;
    }else if(apiResponse.code==="info"){
      vueInstance.$message.info(apiResponse.message);
      return response.data.data;
    }else if(apiResponse.code==="fail"){
      throw new Error(apiResponse.message);
    }
  }, function (error) {
    return error;
  })
}

export default init;