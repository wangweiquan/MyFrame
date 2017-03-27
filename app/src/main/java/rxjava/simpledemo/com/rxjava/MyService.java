package rxjava.simpledemo.com.rxjava;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * calssName
 * Created by wangwq.
 */

public interface MyService {

    @POST("dzcx_cz/nm/regist/logon2")
    Observable<LoginEntity> getToken(@Body JSONObject token);
}
