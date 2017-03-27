package rxjava.simpledemo.com.base;

import android.app.Application;
import android.content.Context;

/**
 * application基类
 *
 * Created by wangwq.2017/3/27
 */

public class BaseApplication extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext = this.getApplicationContext();
    }
}
