package rxjava.simpledemo.com.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rxjava.simpledemo.com.api.LoginApi;
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
        LoginRequestBody loginRequestBody = new LoginRequestBody("18916952130", "A0000055DA601F", "bv16icasfai4do24ne3e72t8bc4eobcm92c75nb105609202");
        loginApi.getLogin(loginRequestBody, new RxJavaSubscribeHelp(this, true) {
            @Override
            public void _onNext(Object t) {
                Log.i("---msg---", t.toString());
            }
        });
    }
}
