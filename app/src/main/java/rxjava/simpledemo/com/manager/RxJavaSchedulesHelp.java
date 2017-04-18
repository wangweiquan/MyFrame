package rxjava.simpledemo.com.manager;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Rxjava baseh
 * Created by wangwq.2017/4/17
 */

public class RxJavaSchedulesHelp {

    public static <T> Observable.Transformer<T, T> getRxScheduleHelper() {
        Observable.Transformer<T, T> observable = new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
        return observable;
    }

    public static <T> Observable.Transformer<T, T> getResponseHelper() {

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T response) {
                        return createDate(response);
                    }
                });
            }
        };
    }

    public static  <T>Observable<T> createDate(final T t){

        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
