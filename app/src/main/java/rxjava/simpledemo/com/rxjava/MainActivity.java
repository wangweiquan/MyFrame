package rxjava.simpledemo.com.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rxjava.simpledemo.com.api.LoginApi;
import rxjava.simpledemo.com.entity.LoginEntity;
import rxjava.simpledemo.com.entity.body.LoginRequestBody;
import rxjava.simpledemo.com.manager.RxJavaSubscribeHelp;

public class MainActivity extends AppCompatActivity {

    private LoginApi loginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        loginApi = new LoginApi();
        LoginRequestBody loginRequestBody = new LoginRequestBody("18916952130","A0000055DA601F","bv16icasfai4do24ne3e72t8bc4eobcm92c75nb105609202");
        loginApi.getLogin(loginRequestBody, new RxJavaSubscribeHelp(this,true) {
            @Override
            public void _onNext(Object t) {
                Log.i("---msg---",t.toString());
            }
        });
    }
}
