package jp.ekidata.sample;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * http://www.ekidata.jp/からの駅情報を扱うためのユーティリティ
 * 
 * @author rk7fd3s
 *
 */
public class EkiDataUtil {
	/**
	 * 山手線駅情報取得URL
	 */
	public static String YAMANOTE_DATA_URL ="http://www.ekidata.jp/api/l/11302.json";
	
	/**
	 * 上記URLからの返却値を正しいJSON形式に補正するための関数
	 * 
	 * @param jsonText 返却文字列
	 * @return JSON文字列
	 */
	public static String replaceJson(String jsonText) {
		jsonText = jsonText.replaceAll("if\\(typeof\\(xml\\)=='undefined'\\) xml = \\{\\};", "");
		jsonText = jsonText.replaceAll("xml.data = ", "");
		jsonText = jsonText.replaceAll("if\\(typeof\\(xml.onload\\)=='function'\\) xml.onload\\(xml.data\\);", "");
		
		return jsonText;
	}
	
	/**
	 * JSON情報をパース格納したオブジェクトから、駅情報クラスに詰め替えます
	 * 
	 * @param jsonObject JSON情報
	 * @return 駅情報クラス
	 * @throws JSONException
	 */
	public static EkiDetail getEkiDetail(JSONObject jsonObject) throws JSONException {
	    EkiDetail ekiData = new EkiDetail();
	    ekiData.setStationCd(jsonObject.getInt("station_cd"));
	    ekiData.setStationGCd(jsonObject.getInt("station_g_cd"));
	    ekiData.setStationName(jsonObject.getString("station_name"));
	    ekiData.setLat(jsonObject.getString("lat"));
	    ekiData.setLon(jsonObject.getString("lon"));
	    
		return ekiData;
	}
}
