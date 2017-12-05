package music.daos.genre;

public class GenreDAO {
	
	private String genreName;
	private String playCount;
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	/**
	 * @return the playCount
	 */
	public String getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}
}
