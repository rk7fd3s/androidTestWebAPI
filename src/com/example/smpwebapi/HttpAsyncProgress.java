package com.example.smpwebapi;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * プログレスダイアログ
 * 
 * @author rk7fd3s
 *
 */
public class HttpAsyncProgress implements HttpAsyncProgressDialog {

	// 処理中ダイアログ
	private ProgressDialog progressDialog = null;

	public HttpAsyncProgress(final Context context,
			final CharSequence progressDialogMsg, final boolean modal) {
		init(context, progressDialogMsg, modal);
	}

	@Override
	public void init(final Context context,
			final CharSequence progressDialogMsg, final boolean modal) {
		// バックグラウンドの処理前にUIスレッドでダイアログ表示
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(progressDialogMsg);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(modal); // モーダル
	}

	@Override
	public void show() {
		progressDialog.show();
	}

	@Override
	public void close() {
		progressDialog.dismiss();
	}
}
