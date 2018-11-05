package pi.entity;

import java.io.File;
import java.util.ArrayList;

public class Azure {

	private String personId;
	private ArrayList<File> photos;

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
