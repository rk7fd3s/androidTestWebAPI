package com.example.smpwebapi;

/**
 * HTTP要求処理コールバックインターフェイス
 * 
 * @author rk7fd3s
 *
 */
public interface HttpAsyncCallback {
	/** 成功時のレスポンスコード */
	public static final int SUCCESS = 0;

	/** 失敗時のレスポンスコード */
	public static final int ERROR = -1;

	/**
	 * コールバックメソッド
	 */
	public void callback(final int responseCode, final String result);
}
