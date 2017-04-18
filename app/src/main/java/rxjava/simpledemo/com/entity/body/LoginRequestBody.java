package rxjava.simpledemo.com.entity.body;

/**
 * body for loginin
 * Created by wangwq.2017/3/31
 */

public class LoginRequestBody {
    private String phone;
    private String imei;
    private String password;

    public LoginRequestBody(String phone,String imei,String password){
        this.phone = phone;
        this.imei = imei;
        this.password = password;
    }
}
