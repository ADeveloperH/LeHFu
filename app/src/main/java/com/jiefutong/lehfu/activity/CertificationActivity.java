package com.jiefutong.lehfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.SimpleResultBean;
import com.jiefutong.lehfu.bean.UploadImageBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.StringUtil;
import com.jiefutong.lehfu.utils.ThreadManager;
import com.jiefutong.lehfu.utils.ToastUtils;
import com.jiefutong.lehfu.widget.SelectPicPopupWindow;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/15 0015 20:46
 * description:
 */


public class CertificationActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_ID)
    EditText etID;
    @BindView(R.id.rl_ID_facade)
    RelativeLayout rlIDFacade;
    @BindView(R.id.rl_ID_identity)
    RelativeLayout rlIDIdentity;
    @BindView(R.id.rl_ID_hand)
    RelativeLayout rlIDHand;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_id_facade)
    ImageView ivIdFacade;
    @BindView(R.id.iv_id_identity)
    ImageView ivIdIdentity;
    @BindView(R.id.iv_id_hand)
    ImageView ivIdHand;
    @BindView(R.id.iv_id_facade_right)
    ImageView ivIdFacadeRight;
    @BindView(R.id.iv_id_identity_right)
    ImageView ivIdIdentityRight;
    @BindView(R.id.iv_id_hand_right)
    ImageView ivIdHandRight;
    private SelectPicPopupWindow menuWindow;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),
            "cache_id_card.jpg");

    String[] imageUrls = new String[3];
    private int curClickIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        setTitle("实名认证");
    }

    @OnClick({R.id.rl_ID_facade, R.id.rl_ID_identity, R.id.rl_ID_hand, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_ID_facade:
                showPicWindow(0);
                break;
            case R.id.rl_ID_identity:
                showPicWindow(1);
                break;
            case R.id.rl_ID_hand:
                showPicWindow(2);
                break;
            case R.id.btn_next:
                commitInfo();
                break;
            case R.id.btn_take_photo:
                takePhotoGet();
                menuWindow.dismiss();
                break;
            case R.id.btn_pick_photo:
                fromPhotoGet();
                menuWindow.dismiss();
                break;
        }
    }


    /**
     * 提交实名认证信息
     */
    private void commitInfo() {
        String idStr = etID.getText().toString().trim();
        String nameStr = etName.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            ToastUtils.showCenterShortToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(idStr)) {
            ToastUtils.showCenterShortToast("请输入身份证");
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (TextUtils.isEmpty(imageUrls[i])) {
                ToastUtils.showCenterShortToast("请先上传照片");
                return;
            }
        }

        RequestParams requestParams = new RequestParams();
        requestParams.put("truename", nameStr);
        requestParams.put("idcard", idStr);
        requestParams.put("card_pic_1", imageUrls[0]);
        requestParams.put("card_pic_2", imageUrls[1]);
        requestParams.put("pic", imageUrls[2]);
        Http.post(Http.CERTIFY_REALNAME, requestParams,
                new MyTextAsyncResponseHandler(act, "正在上传中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                        if (resultBean.getStatus() == 1) {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("提交失败");
                    }
                });
    }


    /**
     * 显示
     *
     * @param index
     */
    private void showPicWindow(int index) {
        curClickIndex = index;
        if (menuWindow == null) {
            menuWindow = new SelectPicPopupWindow(act, this);//实例化SelectPicPopupWindow
        }
        menuWindow.showAtLocation(findViewById(R.id.btn_next), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);//显示窗口
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    doPhoto(Uri.fromFile(tempFile));
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                if (data != null){
                    Uri uri = data.getData();
                    tempFile = new File(parseFilePath(uri));
                    doPhoto(uri);
                }
                break;
        }
    }

    /**
     * 上传照片
     *
     * @param uri
     */
    private void doPhoto(final Uri uri) {
        Bitmap photo = null;
        try {
            photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (curClickIndex == 0) {
            ivIdFacade.setImageBitmap(photo);
            ivIdFacadeRight.setVisibility(View.VISIBLE);
        } else if (curClickIndex == 1) {
            ivIdIdentity.setImageBitmap(photo);
            ivIdIdentityRight.setVisibility(View.VISIBLE);
        } else if (curClickIndex == 2) {
            ivIdHand.setImageBitmap(photo);
            ivIdHandRight.setVisibility(View.VISIBLE);
        }
        ThreadManager.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                    submitImage();
            }
        });

    }


    /**
     * 上传图片
     */
    private void submitImage() {
        String imagePath = tempFile.getAbsolutePath();
        if (TextUtils.isEmpty(imagePath)) {
            return;
        }
        try {
            imagePath = StringUtil.getThumbUploadPath(imagePath, 720, 200);//得到压缩后的图片地址
            final RequestParams requestParams = new RequestParams();
            final String finalImagePath = imagePath;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Http.uploadFileByBase64(Http.UPLOAD_IMAGE, finalImagePath, requestParams,
                            new MyTextAsyncResponseHandler(act, "正在上传中...") {
                                @Override
                                public void onSuccess(String content) {
                                    super.onSuccess(content);
                                    UploadImageBean uploadImageBean = JsonUtil.fromJson(content, UploadImageBean.class);
                                    if (uploadImageBean.getStatus() == 1) {
                                        ToastUtils.showCenterShortToast("上传成功");
                                        imageUrls[curClickIndex] = uploadImageBean.getUrl();
                                    } else {
                                        ToastUtils.showCenterShortToast("上传失败");
                                    }
                                }

                                @Override
                                public void onFailure(Throwable error) {
                                    super.onFailure(error);
                                    ToastUtils.showCenterShortToast("上传失败");
                                }
                            });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showCenterShortToast("上传失败");
        }
    }

    private String parseFilePath(Uri uri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null,
                null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 拍照获取图片
     */
    private void takePhotoGet() {
        // 调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
    }

    /***
     * 从相册中取图片
     */
    private void fromPhotoGet() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }
}
