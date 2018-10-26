package pi.azure.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonFaceDTO {
	
	@Id
	private String groupId;
	private String personId;
	private String personName;
	private String personFaceId;
	private String personFaceUrl;
	private String personFaceName;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonFaceId() {
		return personFaceId;
	}
	public void setPersonFaceId(String personFaceId) {
		this.personFaceId = personFaceId;
	}
	public String getPersonFaceUrl() {
		return personFaceUrl;
	}
	public void setPersonFaceUrl(String personFaceUrl) {
		this.personFaceUrl = personFaceUrl;
	}
	public String getPersonFaceName() {
		return personFaceName;
	}
	public void setPersonFaceName(String personFaceName) {
		this.personFaceName = personFaceName;
	}
	



}
