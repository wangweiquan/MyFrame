package rxjava.simpledemo.com.api;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import rxjava.simpledemo.com.api.net.BaseRequestBody;
import rxjava.simpledemo.com.base.BaseApi;
import rxjava.simpledemo.com.entity.LoginEntity;
import rxjava.simpledemo.com.entity.body.LoginRequestBody;
import rxjava.simpledemo.com.manager.RequestManager;

/**
 * api for login
 *
 * Created by wangwq.2017/3/27
 */

public class LoginApi extends BaseApi{

    /**
     * 定义接口api
     */
    public interface Api {
        @POST("dzcx_cz/nm/regist/logon2")
        Observable<LoginEntity> Login(@Body RequestBody requestBody);
    }

    private Api api;

    /**
     * 声明请求
     */
    public LoginApi(){
        api = RequestManager.getRequest(Api.class,"https://test01.letzgo.com.cn:12016/");
    }


    public void getLogin(LoginRequestBody loginRequestBody,){
        api.Login(new BaseRequestBody<LoginRequestBody>(loginRequestBody).toRequestBody())
                .compose()

    }
}
