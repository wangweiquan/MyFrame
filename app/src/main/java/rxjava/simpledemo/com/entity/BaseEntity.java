package rxjava.simpledemo.com.entity;

/**
 * Created by wangwq.2017/5/23
 */

public class BaseEntity {
    /**
     * success : 0
     * msg :{}
     */
    private int success;
    private String msg;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseEntity [success=" + success + ", msg=" + msg + "]";
    }
}
