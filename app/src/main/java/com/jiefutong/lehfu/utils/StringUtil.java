package com.jiefutong.lehfu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/4/8.
 */
public class StringUtil {


    /**
     * 获取压缩处理后的图片
     * @param oldPath 图片的路径
     * @param bitmapMaxWidth 图片的宽度最大像素，如果大于原始的图片就不进行处理
     * @param maxSize 图片最大的大小，单位为KB
     * @return
     * @throws Exception
     */
    public static String getThumbUploadPath(String oldPath, int bitmapMaxWidth, int maxSize)
            throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(oldPath, options);
        int height = options.outHeight;
        int width = options.outWidth;

        int reqHeight = 0;
        int reqWidth = bitmapMaxWidth;
        reqHeight = (reqWidth * height) / width;

        // 在内存中创建bitmap对象，这个对象按照缩放大小创建的
        options.inSampleSize = calculateInSampleSize(options, bitmapMaxWidth, reqHeight);

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(oldPath, options);

        return compressImage(bitmap, maxSize);
    }


    /**
     * 对图片进行质量压缩
     *
     * @param oldPath 图片的路径
     * @param maxSize 图片最大的大小，单位为KB
     * @return
     * @throws Exception
     */
    public static String compressImage(String oldPath, int maxSize) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(oldPath, options);

        return compressImage(bitmap, maxSize);

    }

    /**
     * 对bitmap进行质量压缩
     *
     * @param imageBitmap 图片
     * @param maxSize     图片最大的大小，单位为KB
     * @return
     * @throws Exception
     */
    public static String compressImage(Bitmap imageBitmap, int maxSize) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > maxSize) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩

            options -= 10;// 每次都减少10
            if (options <= 0) {
                break;
            }
            baos.reset();// 重置baos即清空baos
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = MD5Utils.md5(timeStamp);
        String path = Environment.getExternalStorageDirectory().getPath()
                + File.separator + "test/headImg/";
        File mediaFile = new File(path + File.separator + imageName + ".jpg");
        if (mediaFile.exists()) {
            mediaFile.delete();
        }
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        mediaFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(mediaFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, fos);

        fos.flush();
        fos.close();
        bitmap.recycle();
        bitmap = null;
        System.gc();

        return mediaFile.getPath();
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    /**
     * 去除字符串中的非数字
     *
     * @param str
     * @return
     */
    public static String filterUnNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        // 只允数字
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        //替换与模式匹配的所有字符（即非数字的字符将被""替换）
        return m.replaceAll("").trim();

    }

    /***
     * 获取url 指定name的value;
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        if (!TextUtils.isEmpty(url) && url.contains("?")) {
            int index = url.indexOf("?");
            if (index > -1) {
                String temp = url.substring(index + 1);
                String[] keyValue = temp.split("&");
                for (String str : keyValue) {
                    if (str.contains(name)) {
                        result = str.replace(name + "=", "");
                        break;
                    }
                }
            }

        }
        return result;
    }

    public static CharSequence formatRelativeSize(String content, float relativeSize, int start, int end) {
        if (!TextUtils.isEmpty(content) && content.length() >= start && content.length() >= end) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(content);
            ssb.setSpan(new RelativeSizeSpan(relativeSize), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            return ssb;
        } else {
            return "";
        }
    }


    /**
     * @param text   目标字符串
     * @param length 截取长度
     *               采用的编码方式GBK:中文是占用2个字节的，英文是占用1一个字节
     *               如果采用的编码方式UTF-8:一个中文字符串转换为byte占三个字节
     * @return
     */

    public static String subString(String text, int length) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        try {
            if (text.getBytes("GBK").length <= length) {
                return text;
            } else {
                StringBuilder sb = new StringBuilder();
                int currentLength = 0;
                for (char c : text.toCharArray()) {
                    currentLength += String.valueOf(c).getBytes("GBK").length;
                    if (currentLength <= length) {
                        sb.append(c);
                    } else {
                        break;
                    }
                }
                return sb.append("...").toString();
            }
        } catch (UnsupportedEncodingException e) {
            return text;
        }
    }


    /**
     * 对比判断是否需要更新版本
     *
     * @param localVersion  当前版本
     * @param serverVersion 服务器端版本
     * @return
     */
    public static boolean needUpdate(String localVersion, String serverVersion) {
        try {
            if (!TextUtils.isEmpty(localVersion) && !TextUtils.isEmpty(serverVersion)) {
                String[] splitLocal = localVersion.split("\\.");
                String[] splitServer = serverVersion.split("\\.");
                int minLen = Math.min(splitLocal.length, splitServer.length);
                for (int i = 0; i < minLen; i++) {
                    if (Integer.parseInt(splitServer[i]) > Integer.parseInt(splitLocal[i])) {
                        return true;
                    } else if (Integer.parseInt(splitServer[i]) < Integer.parseInt(splitLocal[i])) {
                        return false;
                    }
                }

                //前面的都相等
                if (splitServer.length > splitLocal.length) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
