import Axios from "axios";

export default {
  list(path){
    return Axios.get('/api/file/list',{
      params:{path}
    })
  },
  mkdir(path){
    return Axios.get('/api/file/make/dir',{
      params:{path}
    })
  },
  touch(path){
    return Axios.get('/api/file/touch/file',{
      params:{path}
    })
  },
  rm(path){

  }
}