package com.example.smpwebapi;

import android.content.Context;

/**
 * プログレスダイアログインターフェイス
 * 
 * @author rk7fd3s
 *
 */
public interface HttpAsyncProgressDialog {

	/**
	 * 初期化
	 * 
	 * @param context
	 * @param progressDialogMsg
	 * @param modal
	 */
	public void init(final Context context,
			final CharSequence progressDialogMsg, final boolean modal);

	/**
	 * プログレスダイアログ表示
	 */
	public void show();

	/**
	 * プログレスダイアログ閉じる
	 */
	public void close();
}
