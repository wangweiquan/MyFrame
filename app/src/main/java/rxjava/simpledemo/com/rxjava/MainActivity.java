package rxjava.simpledemo.com.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rxjava.simpledemo.com.rxjava.LoginEntity;
import rxjava.simpledemo.com.rxjava.MyService;
import rxjava.simpledemo.com.rxjava.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        //RxJava
//        Observable.just("Hello", "World").subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i("--observer--", s);
//            }
//        });

        //RxJava+Retrofit
        JSONObject jsonhead = new JSONObject();
        JSONObject jsonbody = new JSONObject();
        JSONObject json = new JSONObject();
        try {
            jsonhead.put("aid","大众出行司机");
            jsonhead.put("businessType",0);
            jsonhead.put("cd","896371f19669c06c7f630b75a52a2bb52563973a8c885806");
            jsonhead.put("de","2017-03-20 17:03:39");
            jsonhead.put("id",20093);
            jsonhead.put("mos","android4.4.4");
            jsonhead.put("phone","18235139790");
            jsonhead.put("screenx",720);
            jsonhead.put("screeny",1280);
            jsonhead.put("ver","2.0.20");

            jsonbody.put("imei","A0000055DA5E28");
            jsonbody.put("password","bv16icasfai4do24ne3e72t8bc4eobcm92c75nb105609202");
            jsonbody.put("phone","18235139790");

            json.put("head",jsonhead);
            json.put("body",jsonbody);
        } catch (JSONException e) {
            e.printStackTrace();
        }





        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://test-ck.letzgo.com.cn:12015/")
                .build();

        MyService myService = retrofit.create(MyService.class);

        Observable<LoginEntity> observable = myService.getToken(json);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.e("---onCompledted---","----");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("---throwable---",e.toString());
                    }

                    @Override
                    public void onNext(LoginEntity loginEntity) {
                        Log.e("---onNext---",loginEntity.toString());
                    }
                });

    }
}
