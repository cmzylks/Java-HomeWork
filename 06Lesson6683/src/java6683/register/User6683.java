package java6683.register;

/**
 * @author Elxect
 * @date 2021/4/12 7:40 下午
 */
public class User6683 {
	private String userName;
	private String passWord;
	private String phoneNumber;

	public User6683() {
	}

	public User6683(String userName, String passWord, String phoneNumber) {
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return userName + ":" + phoneNumber;
	}
}
