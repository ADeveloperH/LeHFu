package com.jiefutong.lehfu.http;

import android.text.TextUtils;
import android.util.Log;

import com.jiefutong.lehfu.http.factory.ApiRequestFactory;
import com.jiefutong.lehfu.utils.NetWorkUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;

public class Http {

    public static final String SUCCESS = "0";//接口请求成功的标志


    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static final String BASE_URL;


    //获取图片验证码
    public static final String GET_CODE = "index.php/User/Index/getVerify.html";

    //密码登录
    public static final String LOGIN_PWD = "index.php/User/Index/passlogin";


    public static final String GET_SMS_CODE = "index.php/User/Index/check_phone1.html";

    //注册接口
    public static final String REGISTER = "index.php/User/Index/register";


    static {
        BASE_URL = "http://cslhf.jiefutong.net/";//主机域名
    }

    /**
     * 相对地址GET异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(final String url, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiGetRequest()
                    .getDataByGet(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 相对地址GET异步请求(可设置超时时间)
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void get(final String url, final RequestParams params,
                           final Callback<ResponseBody> responseHandler,
                           final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiGetRequestTimeout(readTimeout, connTimeout)
                    .getDataByGet(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 绝对地址GET异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void getTemp(final String url, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiGetRequest()
                    .getDataByGetTem(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 绝对地址GET异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void getTemp(final String url, final RequestParams params,
                               final Callback<ResponseBody> responseHandler,
                               final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiGetRequestTimeout(readTimeout, connTimeout)
                    .getDataByGetTem(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 相对地址POST异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(final String url, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (!NetWorkUtil.hasAvailableNetWork()) {
            ToastUtils.showCenterLongToast("当前无网络，请检查网络设置或稍后再试");
            return;
        }

        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiPostRequest()
                    .getDataByPost(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 相对地址POST异步请求
     *
     * @param url
     * @param json
     * @param responseHandler
     */
    public static void postJson(String url, String json, Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                    json);
            ApiRequestFactory.INSTANCE.getiPostRequest().
                    getDataByPostJson(url, body).enqueue(responseHandler);
        }
    }


    /**
     * 相对地址POST异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void post(final String url, final RequestParams params,
                            final Callback<ResponseBody> responseHandler,
                            final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiPostRequestTimeout(readTimeout, connTimeout)
                    .getDataByPost(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 绝对地址POST异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void postTemp(final String url, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiPostRequest()
                    .getDataByPostTem(url, params).enqueue(responseHandler);
        }
    }

    /**
     * 绝对地址POST异步请求
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void postTemp(final String url, final RequestParams params,
                                final Callback<ResponseBody> responseHandler,
                                final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url)) {
            Log.e("Http", "请求地址不能为空");
        } else {
            //带参数
            ApiRequestFactory.INSTANCE.getiPostRequestTimeout(readTimeout, connTimeout)
                    .getDataByPostTem(url, params).enqueue(responseHandler);
        }
    }


    /**
     * POST上传单个文件
     *
     * @param url             相对地址
     * @param filePath        文件绝对路径
     * @param responseHandler
     */
    public static void uploadFileImage(final String url,
                                       final String filePath,
                                       String token,
                                       String imgtype,
                                       final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            //带参数
            File file = new File(filePath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequest()
                    .uploadFileWithHeader(url, token,imgtype,photo).enqueue(responseHandler);
        }
    }

    /**
     * POST上传单个文件
     *
     * @param url             相对地址
     * @param filePath        文件绝对路径
     * @param params          参数Map
     * @param responseHandler
     */
    public static void uploadFile(final String url, final String filePath, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            //带参数
            File file = new File(filePath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequest()
                    .uploadFile(url, photo, params).enqueue(responseHandler);
        }
    }

    /**
     * POST上传单个文件
     *
     * @param url             相对地址
     * @param filePath        文件绝对路径
     * @param params          参数Map
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void uploadFile(final String url, final String filePath, final RequestParams params,
                                  final Callback<ResponseBody> responseHandler,
                                  final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            //带参数
            File file = new File(filePath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequestTimeout(readTimeout, connTimeout)
                    .uploadFile(url, photo, params).enqueue(responseHandler);
        }
    }

    /**
     * POST上传单个文件
     *
     * @param url             绝对地址
     * @param filePath        文件绝对路径
     * @param params          参数Map
     * @param responseHandler
     */
    public static void uploadFileTemp(final String url, final String filePath, final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            //带参数
            File file = new File(filePath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequest()
                    .uploadFileTemp(url, photo, params).enqueue(responseHandler);
        }
    }

    /**
     * POST上传单个文件
     *
     * @param url             绝对地址
     * @param filePath        文件绝对路径
     * @param params          参数Map
     * @param responseHandler
     * @param readTimeout     单位为秒
     * @param connTimeout     单位为秒
     */
    public static void uploadFileTemp(final String url, final String filePath, final RequestParams params,
                                      final Callback<ResponseBody> responseHandler,
                                      final int readTimeout, final int connTimeout) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            //带参数
            File file = new File(filePath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequestTimeout(readTimeout, connTimeout)
                    .uploadFileTemp(url, photo, params).enqueue(responseHandler);
        }
    }

}
