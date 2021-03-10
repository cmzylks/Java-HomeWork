package java6683.borrow;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elxect
 * @date 2021/3/10 8:40 上午
 * @description 图书类
 * 表格专用的SimpleStringProperty，只有该类型的数据才会自动填充到表格单元中
 */
public class Book6683 {
	/**
	 * 图书编号
	 */
	private SimpleIntegerProperty id;
	/**
	 * 图书名称
	 */
	private SimpleStringProperty bookName;
	/**
	 * 图书作者
	 */
	private SimpleStringProperty bookAuthor;

	public Book6683() {
	}

	public Book6683(int id, String bookName, String bookAuthor) {
		this.id = new SimpleIntegerProperty(id);
		this.bookName = new SimpleStringProperty(bookName);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
	}

	public int getId() {
		return id.get();
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getBookName() {
		return bookName.get();
	}

	public SimpleStringProperty bookNameProperty() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}

	public String getBookAuthor() {
		return bookAuthor.get();
	}

	public SimpleStringProperty bookAuthorProperty() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor.set(bookAuthor);
	}
}
