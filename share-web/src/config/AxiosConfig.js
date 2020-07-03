import axios from "axios";

function init(){
  axios.interceptors.response.use( (response)=> {
    let apiResponse=response.data;
    if(apiResponse.code==="ok") {
      return response.data.data;
    }else if(apiResponse.code==="info"){
      this.$message.info(apiResponse.message);
      throw new Error(apiResponse.message);
    }else if(apiResponse.code==="fail"){
      throw new Error(apiResponse.message);
    }
  }, function (error) {
    return error;
  })
}

export default init;