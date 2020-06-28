package com.location.voicetest.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.location.voicetest.R;
import com.location.voicetest.activity.IdeaFankuiActivity;
import com.location.voicetest.activity.LoginActivity;
import com.location.voicetest.activity.StartActivty;
import com.location.voicetest.activity.TextRecordActivity;
import com.location.voicetest.activity.UpdataPassword;
import com.location.voicetest.activity.UpdataPickName;
import com.location.voicetest.base.BaseFragment;
import com.location.voicetest.utils.AppUtils;
import com.location.voicetest.utils.CircleImageView;
import com.location.voicetest.utils.Constent;
import com.location.voicetest.utils.SXSDialog;
import com.location.voicetest.utils.SharedPreferencesUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFragement extends BaseFragment implements View.OnClickListener {

    TextView nickname;

    private Bitmap picBitmap;// 头像Bitmap

    private CircleImageView headicon;

    private static String path = "/sdcard/Voicetext/";// sd路径

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylayout_frg, null, false);
        inintView(view);
        return view;
    }

    private void inintView(View view) {
        CircleImageView icon = (CircleImageView) view.findViewById(R.id.icon);
        nickname = (TextView) view.findViewById(R.id.nickname);
        nickname.setText("昵称:" + SharedPreferencesUtils.get(getActivity(), Constent.USERNAME, ""));
        TextView phonenum = (TextView) view.findViewById(R.id.phonenum);
        phonenum.setText("手机号：" + SharedPreferencesUtils.get(getActivity(), Constent.PHONE, ""));
        TextView textrecord = (TextView) view.findViewById(R.id.textrecord);
        textrecord.setOnClickListener(this);
        TextView ideafankui = (TextView) view.findViewById(R.id.ideafankui);
        ideafankui.setOnClickListener(this);
        TextView chagenumber = (TextView) view.findViewById(R.id.chagenumber);
        chagenumber.setOnClickListener(this);
        TextView quitinfo = (TextView) view.findViewById(R.id.quitinfo);
        quitinfo.setOnClickListener(this);
        TextView updatapaw = (TextView) view.findViewById(R.id.updatapaw);
        updatapaw.setOnClickListener(this);
        TextView chagepickname = (TextView) view.findViewById(R.id.chagepickname);
        chagepickname.setOnClickListener(this);
        headicon = (CircleImageView) view.findViewById(R.id.headicon);
        headicon.setOnClickListener(this);
        Bitmap bitmap = getLoacalBitmap(path + SharedPreferencesUtils.get(getActivity(), Constent.PHONE, "") + ".jpg"); //从本地取图片(在cdcard中获取)  //
        if (bitmap != null)
            headicon.setImageBitmap(bitmap);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textrecord://测试记录
                Intent intent = new Intent(getActivity(), TextRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.ideafankui://意见反馈
                Intent intent1 = new Intent(getActivity(), IdeaFankuiActivity.class);
                startActivity(intent1);
                break;
            case R.id.chagenumber://切换账号
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.quitinfo://退出登录
                SharedPreferencesUtils.shareremvo(getActivity(), Constent.ISLOGIN);
                Intent intent3 = new Intent(getActivity(), StartActivty.class);
                startActivity(intent3);
                getActivity().finish();
                break;
            case R.id.updatapaw://修改登录密码
                Intent intent4 = new Intent(getActivity(), UpdataPassword.class);
                startActivity(intent4);
                break;
            case R.id.chagepickname://修改昵称
                Intent intent5 = new Intent(getActivity(), UpdataPickName.class);
                startActivity(intent5);
                break;
            case R.id.headicon://更换头像
                showDialog();
                break;
            default:
                break;
        }
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 提示对话框方法
    private void showDialog() {
        final SXSDialog sxsDialog = new SXSDialog(getActivity(), R.layout.my_setting_popwin);
        sxsDialog.getWindow().setWindowAnimations(R.style.AnimBottom);
        sxsDialog.setWidthHeight(AppUtils.getScreenDispaly(getActivity())[0], 0);
        sxsDialog.getWindow().setGravity(Gravity.BOTTOM);
        sxsDialog.setOnClick(R.id.btn_take_photo, new View.OnClickListener() {//拍照
            @Override
            public void onClick(View v) {
                openCamera();
                sxsDialog.dismiss();
            }
        });
        Button button = (Button) sxsDialog.findViewById(R.id.btn_pick_photo);//从相册中选择
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromAlbum();
                sxsDialog.dismiss();
            }
        });
        sxsDialog.setOnClick(R.id.btn_cancel, new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                sxsDialog.dismiss();
            }
        });
        sxsDialog.show();
    }

    private void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 4);
    }

    //拍照
    private void openCamera() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "pic.jpg")));
        startActivityForResult(intent2, 2);// 采用ForResult打开
    }

    @Override
    public void onResume() {
        super.onResume();
        nickname.setText("昵称:" + SharedPreferencesUtils.get(getActivity(), Constent.USERNAME, ""));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {//拍照回调
            if (resultCode == getActivity().RESULT_OK) {
                File temp = new File(Environment.getExternalStorageDirectory() + "/pic.jpg");
                cropPhoto(Uri.fromFile(temp));// 裁剪图片
            }
        } else if (requestCode == 4) {
            cropPhoto(data.getData());//直接裁剪图片
        } else if (requestCode == 3) {//裁剪的回调
            if (data != null) {
                Bundle extras = data.getExtras();
                picBitmap = extras.getParcelable("data");
                if (picBitmap != null) {
                    setPicToView(picBitmap);// 保存在SD卡中
                    headicon.setImageBitmap(picBitmap);// 用ImageButton显示出来
                }
            }
        }
    }

    //保存图片至SD卡
    private File setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return null;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + SharedPreferencesUtils.get(getActivity(), Constent.PHONE, "") + ".jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //裁剪图片
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
