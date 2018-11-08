package pi.entity;

import java.io.File;
import java.util.ArrayList;

public class Azure {

	private String personId;
	private ArrayList<File> photos;
	private String faceId;
	private String[] candidates;

	public String[] getCandidates() {
		return candidates;
	}

	public void setCandidates(String[] candidates) {
		this.candidates = candidates;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public Azure() {

	}

	public Azure(String personId) {
		this.personId = personId;
	}

	
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public ArrayList<File> getPhotos() {
		return photos;
	}

	public void setPhotos(ArrayList<File> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Azure [personId=" + personId + ", photos=" + photos + "]";
	}

}
