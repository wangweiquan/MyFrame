package rxjava.simpledemo.com.manager;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * calssName
 * Created by wangwq.2017/4/18
 */

public abstract class RxJavaSubscribeHelp extends Subscriber {

    private WeakReference<Context> context;
    private boolean isShowDialog;

    public RxJavaSubscribeHelp(Context context,boolean isShowDialog){
        this.context = new WeakReference<>(context);
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog && context != null) {
            Log.v("onStart-->","insert yourself dialog");
        }
    }

    @Override
    public void onCompleted() {
        if(isShowDialog){
            Log.v("onCompleted-->","dismiss dialog");
        }
    }

    @Override
    public void onError(Throwable e) {
       e.printStackTrace();
    }

    /**
     * 请求结束后回调
     * @param o
     */
    @Override
    public void onNext(Object o) {
        _onNext(o);
    }

    public abstract void _onNext(Object t);
}
