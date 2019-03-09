package logic.yjkbreath.fileTest.api;

import lombok.Data;

/**
 * @Auther fanhanxi
 * @Date 2019/1/5
 * @Description:
 */
@Data
public class ApiResult {

    private int code;
    private String message;
    private Object object;

    public static ApiResult createSuccessResult(String message, Object data) {
        return buildResult(1, message, data);
    }

    public static ApiResult createFailResult(String message) {
        return buildResult(0, message, null);
    }

    private static ApiResult buildResult(int code, String message, Object data) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMessage(message);
        result.setObject(data);
        return result;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", object=" + object.toString() +
                '}';
    }
}
