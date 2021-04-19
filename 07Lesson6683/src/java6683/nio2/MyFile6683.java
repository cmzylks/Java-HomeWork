package java6683.nio2;

public class MyFile6683 {
	private String name;
	private String size;
	private String modifiedTime;
	private String type;

	public MyFile6683() {
	}

	public MyFile6683(String name, String size, String modifiedTime, String type) {
		this.name = name;
		this.size = size;
		this.modifiedTime = modifiedTime;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
