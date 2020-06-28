package com.location.voicetest.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class CommonDialog {
	/** 默认提示弹出框的监听*/
	public  void showDialog(Activity activity, String title, String titleDesc){
		AlertDialog dialog = new AlertDialog.Builder(activity)
				.setTitle(title)
				.setMessage(titleDesc)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {
							if (m_OnDialogClickListener!=null){
								m_OnDialogClickListener.onSureClick();
							}
						dialog.dismiss();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (m_OnDialogClickListener!=null){
							m_OnDialogClickListener.onCancleClick();
						}
						dialog.dismiss();
					}
				})
				.create();
		dialog.show();
	}



   private OnDialogClickListener m_OnDialogClickListener;
	public void setOnDialogClickListener(OnDialogClickListener Listener) {
		this.m_OnDialogClickListener = Listener;
	}
	/** 确定按钮监听*/
	public abstract interface  OnDialogClickListener {
		public abstract void onSureClick();
		public abstract void onCancleClick();
	}
}
