package com.example.smpwebapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

/**
 * HTTP要求を非同期で行います
 *
 * @author rk7fd3s
 *
 */
public class HttpAsyncLoader extends AsyncTask<Void, Void, String> {
	/** コールバック */
	private HttpAsyncCallback callback;
	private HttpAsyncProgressDialog progressDialog;
	private String url = null;

	/**
	 * コンストラクタ
	 * 
	 * @param callback コールバック
	 * @param progressDialog プログレスダイアログ
	 * @param url 要求先URL
	 */
	public HttpAsyncLoader(final HttpAsyncCallback callback,
			final HttpAsyncProgressDialog progressDialog, final String url) {
		this.callback = callback;
		this.url = url;
		this.progressDialog = progressDialog;
	}

	@Override
	protected void onPreExecute() {
		if (progressDialog != null) {
			// バックグラウンドの処理前にUIスレッドでダイアログ表示
			progressDialog.show();
		}
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			String responseBody = httpClient.execute(new HttpGet(this.url),

			// UTF-8に対応した文字列を返すようにhandleResponseをオーバーライドする
					new ResponseHandler<String>() {

						@Override
						public String handleResponse(HttpResponse response)
								throws ClientProtocolException, IOException {

							// レスポンスコードが、HttpStatus.SC_OK（HTTP 200）の場合のみ、結果を返す
							if (HttpStatus.SC_OK == response.getStatusLine()
									.getStatusCode()) {
								return EntityUtils.toString(
										response.getEntity(), "UTF-8");
							}
							return null;
						}
					});

			return responseBody;
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), e.getMessage());
		} finally {
			// 通信終了時は、接続を閉じる
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	@Override
	protected void onPostExecute(final String result) {
		if (progressDialog != null) {
			// 処理中ダイアログをクローズ
			progressDialog.close();
		}
		callback.callback(HttpAsyncCallback.SUCCESS, result);

	}

	@Override
	protected void onCancelled() {
		if (progressDialog != null) {
			progressDialog.close();
		}
	}
}
