package java6683.score;

public class Score6683 {
	private String name;
	private int chinese;
	private int math;
	private int english;
	private int sum;

	public Score6683() {
	}

	public Score6683(String name, int chinese, int math, int english) {
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.sum = getSum();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getSum() {
		return chinese + math + english;
	}
}
