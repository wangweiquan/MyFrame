package rxjava.simpledemo.com.manager;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rxjava.simpledemo.com.base.BaseApplication;
import rxjava.simpledemo.com.util.NetWorkUtils;
import rxjava.simpledemo.com.util.SSLUtils;

/**
 * refrofit做okhttp请求
 * Created by wangwq.2017/4/5
 */

public class RetrofitClient {

    //读取超时时长（毫秒）
    private static final int READ_TIMEOUT = 1000*5;
    //连接超时时长（毫秒）
    private static final int CONNECT_TIMEOUT = 1000*5;

    /**
     * Retrofit层
     * @param baseUrl
     * @param <T>
     * @return
     */
    public static <T> T createApi(Class<T> clazz,String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient()) //方法指定网络执行器为okhttp
                .build();
        return retrofit.create(clazz);
    }

    /**
     * okHttp层
     * @return
     */
    private static OkHttpClient getClient(){

        /**
         * 请求缓存策略
         */
        Interceptor INTERCEPTOR_CACHE = getInterceptor();

        /**
         * 请求token策略
         */
        Interceptor INTERCEPTOR_TOKEN = getToken();

        /**
         * ssl
         */
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(null,null,null);

        /**
         * 本地缓存
         */
        File cacheFile = new File(BaseApplication.mContext.getCacheDir(), "myCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.MILLISECONDS)
                .addInterceptor(INTERCEPTOR_CACHE)
                .addNetworkInterceptor(INTERCEPTOR_TOKEN)
                .sslSocketFactory(sslParams.sslSocketFactory,sslParams.trustManager)
                .cache(cache)
                .build();
        return okHttpClient;
    }

    /**
     * 配置缓存策略
     *
     * @return
     */
    protected static Interceptor getInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //获取请求
                Request request = chain.request();
                //前置拦截逻辑
                //没有网络的时候强制使用缓存数据
                if (!NetWorkUtils.isNetConnected(BaseApplication.mContext)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Log.i("---intercept---", "no network");
                }
                //传递Interceptor
                Response originalResponse = chain.proceed(request);
                //后置拦截逻辑
                if (NetWorkUtils.isNetConnected(BaseApplication.mContext)) {
                    //有网环境下读取接口@Headers里的配置内容
                    //@Headers("Cache-Control: public,max-age = 60")
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public,only-if-cached,max-stale =" + 60 * 60 * 1)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
        return interceptor;
    }

    /**
     * 配置请求token
     * @return
     */
    protected static Interceptor getToken(){
        final String token = null;
        Interceptor tokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder();
                if(!TextUtils.isEmpty(token)){
                    newRequest.header("Authorization",token);
                }
                return chain.proceed(newRequest.build());
            }
        };
        return tokenInterceptor;
    }
}
