package com.example.smpwebapi;

import jp.ekidata.sample.EkiDataUtil;
import jp.ekidata.sample.EkiDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements HttpAsyncCallback {

	// 一覧表示用ListView
	private ListView listView = null;
	private ArrayAdapter<EkiDetail> arrayAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// リスト
		listView = (ListView) findViewById(R.id.dataList);
		arrayAdapter = new ArrayAdapter<EkiDetail>(this,
				android.R.layout.simple_list_item_1);

		// アダプタを設定
		listView.setAdapter(arrayAdapter);

		// ボタン押下イベント
		findViewById(R.id.btnGetData).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						getData();
					}
				});
	}

	// データ取得処理
	protected void getData() {
		// プログレスダイアログ
		HttpAsyncProgress progressDialog = new HttpAsyncProgress(
				MainActivity.this, this.getText(R.string.progress_loading_in),
				false);

		// 非同期でデータ取得
		HttpAsyncLoader loader = new HttpAsyncLoader(this, progressDialog,
				EkiDataUtil.YAMANOTE_DATA_URL);
		loader.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 非同期でデータ取得後のコールバック処理
	 */
	@Override
	public void callback(int responseCode, String result) {
		// 返却文字列をJSONに補正
		result = EkiDataUtil.replaceJson(result);

		try {
			// リストをクリア
			arrayAdapter.clear();

			// JSON値から駅情報を取得し、リストに追加していく
			JSONObject rootObject = new JSONObject(result);
			JSONArray eventArray = rootObject.getJSONArray("station_l");

			for (int i = 0; i < eventArray.length(); i++) {
				JSONObject jsonObject = eventArray.getJSONObject(i);

				arrayAdapter.add(EkiDataUtil.getEkiDetail(jsonObject));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
