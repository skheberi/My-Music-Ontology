package music.daos.urls;

import java.util.List;

public class URLsDAO {
	
	private List<String> urls;
	private String imageUrl;
	private String artistName;
	private String gender;
	
	/**
	 * @return the urls
	 */
	public List<String> getUrls() {
		return urls;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}
	/**
	 * @param artistName the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
}
