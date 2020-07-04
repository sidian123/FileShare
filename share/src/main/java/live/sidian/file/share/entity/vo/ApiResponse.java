package live.sidian.file.share.entity.vo;

import lombok.*;

/**
 * @author sidian
 * @date 2020/7/3 14:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    String code;
    String message;
    T data;

    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.<T>builder().code(CodeType.OK.toString()).message("ok").data(data).build();
    }
    public static <T> ApiResponse<T> success(){
        return ApiResponse.<T>builder().code(CodeType.OK.toString()).message("ok").data(null).build();
    }
    public static <T> ApiResponse<T> fail(String message,T data){
        return ApiResponse.<T>builder().code(CodeType.FAIL.toString()).message(message).data(data).build();
    }
    public static <T> ApiResponse<T> fail(String message){
        return ApiResponse.<T>builder().code(CodeType.FAIL.toString()).message(message).data(null).build();
    }
    public static <T> ApiResponse<T> fail(){
        return ApiResponse.<T>builder().code(CodeType.FAIL.toString()).message("").data(null).build();
    }
    public static <T> ApiResponse<T> fail(Exception e){
        return ApiResponse
                .<T>builder()
                .code(CodeType.FAIL.toString())
                .message(e.getClass().getCanonicalName()+":"+e.getMessage())
                .data(null)
                .build();
    }
    public static <T> ApiResponse<T> info(String message,T data){
        return ApiResponse.<T>builder().code(CodeType.INFO.toString()).message(message).data(data).build();
    }
    public static <T> ApiResponse<T> info(String message){
        return ApiResponse.<T>builder().code(CodeType.INFO.toString()).message(message).data(null).build();
    }

    static public enum  CodeType{
        OK("ok"),FAIL("fail"),INFO("info");

        private final String type;
        CodeType(String type){
            this.type=type;
        }
        @Override
        public String toString(){
            return type;
        }
    }
}
