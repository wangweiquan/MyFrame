package rxjava.simpledemo.com.api.net;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rxjava.simpledemo.com.base.BaseApplication;
import rxjava.simpledemo.com.util.ClientUtils;

/**
 * 通用网络请求体
 * Created by wangwq.2017/3/31
 */

public class BaseRequestBody<T> implements Serializable{

    private T body;
    private BaseRequestHead head;

    public BaseRequestBody(T body){
        this.body = body;
        this.head = new BaseRequestHead().init();
    }

    private class BaseRequestHead {

        private String screeny;     //屏幕尺寸高
        private String screenx;     //屏幕尺寸宽
        private String mos;         //系统版本
        private String ver;         //应用版本号
        private String de;          //当前时间
        private String aid;         //应用名称
        private String phone;       //用户名
        private String businessType;//司机业务类型
        private String id;          //主键
        private String cd;          //（MD5加密，作用不明）

        public BaseRequestHead init(){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date currDate = new Date(System.currentTimeMillis());

            screeny = ClientUtils.getScreenY(BaseApplication.mContext)+"";
            screenx = ClientUtils.getScreenX(BaseApplication.mContext)+"";
            mos = "android" + ClientUtils.getOsVersion();
            ver = ClientUtils.getAppVersion(BaseApplication.mContext);
            de = formatter.format(currDate);
            aid = ClientUtils.getAppName(BaseApplication.mContext);;
            phone = "18916952130";
            businessType = "0";
            cd = "e34e15c66304f35476f72534a8e23ea9231778ff2709704d";
            return this;
        }
    }

    public RequestBody toRequestBody(){
        String json = new Gson().toJson(this);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        return requestBody;
    }
}
