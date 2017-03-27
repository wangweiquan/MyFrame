package rxjava.simpledemo.com.eventbus;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rxjava.simpledemo.com.base.BaseActivity;
import rxjava.simpledemo.com.entity.MessageEventEntity;

/**
 * 事件总线
 *
 * Created by wangwq.2017/3/27
 */

public class EventBusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new MessageEventEntity("this is a eventbus post"));
    }

    /**
     * 订阅
     * •POSTING（默认）：
     * 如果使用事件处理函数指定了线程模型为POSTING，那么该事件在哪个线程发布出来的，事件处理函数就会在这个线程中运行，
     * 也就是说发布事件和接收事件在同一个线程。在线程模型为POSTING的事件处理函数中尽量避免执行耗时操作，因为它会阻塞事件的传递，
     * 甚至有可能会引起ANR。
     * •MAIN:
     * 事件的处理会在UI线程中执行。事件处理时间不能太长，长了会ANR的。
     * •BACKGROUND：
     * 如果事件是在UI线程中发布出来的，那么该事件处理函数就会在新的线程中运行，如果事件本来就是子线程中发布出来的，
     * 那么该事件处理函数直接在发布事件的线程中执行。在此事件处理函数中禁止进行UI更新操作。
     * •ASYNC：
     * 无论事件在哪个线程发布，该事件处理函数都会在新建的子线程中执行，同样，此事件处理函数中禁止进行UI更新操作。
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 100)
    public void MyEventBus(MessageEventEntity event){
        Log.i("---eventbus---",event.msg);
    }
}
