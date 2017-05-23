package rxjava.simpledemo.com.manager;

/**
 * Created by wangwq.2017/5/23
 */

public class InterfaceError extends RuntimeException {
    private int success;
    private String msg;
    private Object response;

    public InterfaceError(int status, String msg, Object response) {
        this.success = status;
        this.msg = msg;
        this.response = response;

    }

    public int getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public Object getResponse() {
        return response;
    }
}
