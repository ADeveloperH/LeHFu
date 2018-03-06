package com.jiefutong.lehfu.http;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.jiefutong.lehfu.http.encrypt.Base64Utils;
import com.jiefutong.lehfu.http.factory.ApiRequestFactory;
import com.jiefutong.lehfu.utils.NetWorkUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

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

    //短信验证码登录
    public static final String LOGIN_SMS = "index.php/User/Index/login";


    //获取短信验证码
    public static final String GET_SMS_CODE = "index.php/User/Index/check_phone1.html";

    //获取短信验证码
    public static final String GET_OTHER_SMS_CODE = "index.php/User/Index/check_phone.html";

    //注册接口
    public static final String REGISTER = "index.php/User/Index/register";

    //找回密码接口
    public static final String FIND_PWD = "index.php/User/Index/forget.html";

    //重置密码接口
    public static final String RESET_PWD = "index.php/User/Account/resetpwd.html";

    //重置交易密码
    public static final String RESET_TRANSACTIONPWD = "index.php/User/Account/TradeModify";

    //获取金融头条数据
    public static final String GET_TOUTIAO = "index.php/User/SyIndex/topJson.html";

    //上传图片接口
    public static final String UPLOAD_IMAGE = "index.php/User/Upload/index";

    //实名认证接口
    public static final String CERTIFY_REALNAME = "index.php/User/User/ToRealName";

    //退出登录
    public static final String LOGOUT = "index.php/User/Index/logout";

    //用户反馈
    public static final String FEEDBACK = "index.php/User/Feedback/index";

    //检测是否设置交易密码
    public static final String CHECK_HAS_TRANSPWD = "index.php/User/Extend/IsHavePassword";




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
            MultipartBody.Part photo = MultipartBody.Part.createFormData("imgFile", file.getName(),
                    photoRequestBody);
            ApiRequestFactory.INSTANCE.getiUploadFileRequest()
                    .uploadFile(url, photo, params).enqueue(responseHandler);
        }
    }


    /**
     * 上传文件。body中进行base64编码
     * @param url
     * @param filePath
     * @param params
     * @param responseHandler
     */
    public static void uploadFileByBase64(final String url,final String filePath,
                                        final RequestParams params, final Callback<ResponseBody> responseHandler) {
        if (!NetWorkUtil.hasAvailableNetWork()) {
            ToastUtils.showCenterLongToast("当前无网络，请检查网络设置或稍后再试");
            return;
        }
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            Log.e("Http", "请求地址和文件路径不能为空");
        } else {
            try {
                String encodeBase64File = encodeBase64File(filePath);
                JSONObject result = new JSONObject();
                result.put("imgFile", encodeBase64File);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                        result.toString());
                ApiRequestFactory.INSTANCE.getiPostRequest()
                        .getDataByPostJson(url, requestBody).enqueue(responseHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 图片文件转Base64字符串
     * @param path 文件所在的绝对路径加文件名　
     * @return
     */
    public static String fileBase64String(String path){
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);//转换成输入流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) >= 0){
                baos.write(buffer, 0, count);//读取输入流并写入输出字节流中
            }
            fis.close();//关闭文件输入流
            String uploadBuffer = new String(Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT));  //进行Base64编码
            return uploadBuffer;
        } catch (Exception e) {
            return null;
        }

    }
    /**
     * encodeBase64File:(将文件转成base64 字符串).
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64Utils.encode(buffer);
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
