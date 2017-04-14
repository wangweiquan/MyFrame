package rxjava.simpledemo.com.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * NetWorkUtils
 * Created by wangwq.2017/4/7
 */

public class NetWorkUtils {

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            return true;
        return false;
    }
}
