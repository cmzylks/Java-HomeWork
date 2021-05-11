package java6683.edit;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Score6683 {
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleIntegerProperty chinese = new SimpleIntegerProperty();
	private SimpleIntegerProperty math = new SimpleIntegerProperty();
	private SimpleIntegerProperty english = new SimpleIntegerProperty();

	public Score6683(String name, int chinese, int math, int english) {
		this.name.set(name);
		this.chinese.set(chinese);
		this.math.set(math);
		this.english.set(english);
	}

	public Score6683() {

	}

	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public int getChinese() {
		return chinese.get();
	}

	public SimpleIntegerProperty chineseProperty() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese.set(chinese);
	}

	public int getMath() {
		return math.get();
	}

	public SimpleIntegerProperty mathProperty() {
		return math;
	}

	public void setMath(int math) {
		this.math.set(math);
	}

	public int getEnglish() {
		return english.get();
	}

	public SimpleIntegerProperty englishProperty() {
		return english;
	}

	public void setEnglish(int english) {
		this.english.set(english);
	}

	public SimpleStringProperty getNameProperty() {
		return this.name;
	}

	public SimpleIntegerProperty getChineseProperty() {
		return this.chinese;
	}

	public SimpleIntegerProperty getMathProperty() {
		return this.math;
	}

	public SimpleIntegerProperty getEnglishProperty() {
		return this.english;
	}
}
