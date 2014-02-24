package jp.ekidata.sample;

/**
 * 駅情報クラス
 * http://www.ekidata.jp/からの駅情報を格納保持するクラス
 * 
 * @author rk7fd3s
 *
 */
public class EkiDetail {
	/** 駅コード */
	private int stationCd = 0;
	
	/** 駅グループコード */
	private int stationGCd = 0;
	
	/** 駅名 */
	private String stationName = null;
	
	/** 経度 */
	private String lon = null;
	
	/** 緯度 */
	private String lat = null;

	
	/**
	 * @return stationCd
	 */
	public int getStationCd() {
		return stationCd;
	}


	/**
	 * @param stationCd 駅コード
	 */
	public void setStationCd(int stationCd) {
		this.stationCd = stationCd;
	}


	/**
	 * @return stationGCd
	 */
	public int getStationGCd() {
		return stationGCd;
	}


	/**
	 * @param stationGCd 駅グループコード
	 */
	public void setStationGCd(int stationGCd) {
		this.stationGCd = stationGCd;
	}


	/**
	 * @return stationName
	 */
	public String getStationName() {
		return stationName;
	}


	/**
	 * @param stationName 駅名
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	/**
	 * @return lon
	 */
	public String getLon() {
		return lon;
	}


	/**
	 * @param lon 経度
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}


	/**
	 * @return lat
	 */
	public String getLat() {
		return lat;
	}


	/**
	 * @param lat 緯度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * ListView用の表示文字列
	 */
	public String toString() {
		return Integer.toString(this.getStationCd()) + ":" + this.getStationName();
	}
}
