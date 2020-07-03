import axios from "axios";

function init(){
  axios.interceptors.response.use(function (response) {
    let apiResponse=response.data;
    if(apiResponse.code==="ok" || apiResponse.code==="info"){
      return response.data.data;
    }else if(apiResponse.code==="fail"){
      throw new Error(apiResponse.message);
    }
  }, function (error) {
    return error;
  })
}

export default init;