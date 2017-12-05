package music.daos.event;

public class EventDAO {
	
	private String eventName;
	private String arena;
	private String eventDate;
	private String performedBy;
	private String location;
	
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the arena
	 */
	public String getArena() {
		return arena;
	}
	/**
	 * @param arena the arena to set
	 */
	public void setArena(String arena) {
		this.arena = arena;
	}
	/**
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return the performedBy
	 */
	public String getPerformedBy() {
		return performedBy;
	}
	/**
	 * @param performedBy the performedBy to set
	 */
	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}
