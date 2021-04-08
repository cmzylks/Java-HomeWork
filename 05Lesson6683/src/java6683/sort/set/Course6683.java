package java6683.sort.set;

public class Course6683 implements Comparable<Course6683> {
	private String name;
	private String term;
	private String no;
	private int credit;
	private int score;

	public Course6683() {
	}

	public Course6683(int score, String term, int credit, String no, String name) {
		this.name = name;
		this.credit = credit;
		this.score = score;
		this.term = term;
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "成绩：" + score + "，" + term + "，" + credit + "分" + "【" + no + " " + name + "】";
	}

	@Override
	public int compareTo(Course6683 o) {
		////先比较成绩
		int num = o.score - this.score;
		int num2 = num == 0 ? o.getTerm().compareTo(getTerm()) : num;
		int num3 = num2 == 0 ? o.credit - this.credit : num2;
		return num3 == 0 ? o.getNo().compareTo(getNo()) : num3;
	}
}
