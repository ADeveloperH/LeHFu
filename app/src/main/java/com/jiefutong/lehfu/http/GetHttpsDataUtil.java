package com.jiefutong.lehfu.http;

import android.content.Context;

public class GetHttpsDataUtil {

	private Context context;//上下文

    private IHttpsResponseListener listener = null;
	private static final int TIMEOUT_READ_DEFAULT = 30;
	private static final int TIMEOUT_CONNECTION_DEFAULT = 30;

    public void setOnGetDataListener(IHttpsResponseListener listener) {
        this.listener = listener;
    }

    /**
     * @param context
     */
	public GetHttpsDataUtil(Context context) {
		super();
		this.context = context;
	}

	private void getData(final String url, RequestParams requestParamsMap,
						 String dialogMsg) {

		Http.postTemp(getAbsoultUrl(url),requestParamsMap,
				new MyTextAsyncResponseHandler(context,dialogMsg){
			@Override
			public void onStart() {
				super.onStart();
				if (listener != null) {
					listener.onGetDataStart();
				}
			}

			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				if (listener != null) {
					listener.onGetDataSuccess(url,content);
				}
			}


			@Override
			public void onFailure(Throwable error) {
				super.onFailure(error);
				if (listener != null) {
					listener.onGetDataFailure(error);
				}
			}
		},TIMEOUT_READ_DEFAULT,TIMEOUT_CONNECTION_DEFAULT);
	}


	private String getAbsoultUrl(String url) {
		return HttpsUtils.HOST + url;
	}


	/**
	 * 监听https访问的接口
	 * @author huangjian
	 *
	 */
	public interface IHttpsResponseListener {
		/**
		 * 开始访问数据
		 */
		void onGetDataStart();
		/**
		 * 成功访问网络后
		 * @param url	访问的url地址
		 * @param result 成功访问后返回的字符串
		 */
		void onGetDataSuccess(String url, String result);

		/**
		 * 访问网络失败后
		 * @param error
		 */
		void onGetDataFailure(Throwable error);
	}

}
