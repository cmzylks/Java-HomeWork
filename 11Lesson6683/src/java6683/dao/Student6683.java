package java6683.dao;

public class Student6683 {
	private int id;
	private String sno;
	private String name;
	private int lx1;
	private int lx2;

	public Student6683(String sno, String name) {
		this.sno = sno;
		this.name = name;
	}

	public Student6683() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setsName(String name) {
		this.name = name;
	}

	public int getLx1() {
		return lx1;
	}

	public void setLx1(int lx1) {
		this.lx1 = lx1;
	}

	public int getLx2() {
		return lx2;
	}

	public void setLx2(int lx2) {
		this.lx2 = lx2;
	}

	@Override
	public String toString() {
		return id + "," + sno + "," + name + "," + lx1 + "," + lx2;
	}
}
