import Axios from "axios";

export default {
  list(name){
    return Axios.get('/api/file/list',{
      params:{name}
    })
  }
}