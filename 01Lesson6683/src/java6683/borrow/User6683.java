package java6683.borrow;

/**
 * @author Elxect
 * @date 2021/3/10 9:49 上午
 * @description 用户类
 */
public class User6683 {
	private String userName;
	private String passWord;
	public User6683() {
	}

	public User6683(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
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

}
