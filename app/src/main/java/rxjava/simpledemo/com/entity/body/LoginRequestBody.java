package rxjava.simpledemo.com.entity.body;

/**
 * body for loginin
 * Created by wangwq.2017/3/31
 */

public class LoginRequestBody {
    private String phone;
    private String password;
    private String imei;

    public LoginRequestBody(String phone,String password,String imei){
        this.phone = phone;
        this.password = password;
        this.imei = imei;
    }
}
