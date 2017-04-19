# A Simple Frame

## RxJava+Retrofit+EventBus

### 接口使用

```java

  /**
     * 定义接口api
     */
    public interface Api {
        @Headers("Cache-Control: public,max-age = 60")
        @POST("你的接口地址")
        Observable<LoginEntity> Login(@Body RequestBody requestBody);
    }

    private Api api;

    /**
     * 声明请求
     */
    public LoginApi(){
        api = RequestManager.getRequest(Api.class,"你的域名");
    }


    public void getLogin(LoginRequestBody loginRequestBody, RxJavaSubscribeHelp subscribeAction) {
        api.Login(new BaseRequestBody<>(loginRequestBody).toRequestBody())
                .compose(RxJavaSchedulesHelp.<LoginEntity>getRxScheduleHelper())
                .compose(RxJavaSchedulesHelp.<LoginEntity>getResponseHelper())
                .subscribe(subscribeAction);
    }
}

```
