package rxjava.simpledemo.com.manager;

import java.util.HashMap;

/**
 * RequestManager
 * Created by wangwq.2017/3/28
 */

public class RequestManager {

    /**
     * 此处创建hashmap管理已封装的请求
     */
    protected static HashMap<Class,Object> requestList = new HashMap<>();

    public static <T> T getRequest(Class<T> clazz,String Url) {
        T t = (T) requestList.get(clazz);
        if(t == null ){
            t = RetrofitClient.createApi(clazz,Url);
            requestList.put(clazz,t);
        }
        return t;
    }
}
