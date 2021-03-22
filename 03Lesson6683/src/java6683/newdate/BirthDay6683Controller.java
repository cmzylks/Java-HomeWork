package java6683.newdate;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author 郑逢
 * @date 2021/3/22 6:47 下午
 */
public class BirthDay6683Controller {
	public DatePicker dpBirthDay;
	public Label lblResult;

	public void change(ActionEvent actionEvent) {
		//获取输入的日期
		String birthday = String.valueOf(dpBirthDay.getValue());
		TemporalAccessor formatter = null;
		//捕获没有输入日期的情况
		try {
			formatter = DateTimeFormatter.ISO_LOCAL_DATE.parse(birthday);
		} catch (Exception e) {
			lblResult.setText("请输入出生日期！");
			return;
		}
		//格式化日期
		LocalDate birthdayDate = LocalDate.from(formatter);
		//获取当前的时间
		LocalDate nowDate = LocalDate.now();
		//今年的生日日期
		LocalDate birthdayThisYear = LocalDate.of(nowDate.getYear(), birthdayDate.getMonthValue(), birthdayDate.getDayOfMonth());
		//岁数
		int age = nowDate.getYear() - birthdayDate.getYear();
		//距离今年生日的天数
		long day = birthdayThisYear.toEpochDay() - nowDate.toEpochDay();
		//输入的日期超前
		if (age < 0 || age == 0 && day > 0) {
			lblResult.setText("你还没出生嘞！");
			//今天生日
		} else if (day == 0) {
			lblResult.setText(age + "岁生日,生日快乐！");
			//生日已过
		} else if (day < 0) {
			lblResult.setText("你的生日已经过啦！");
		} else {
			lblResult.setText("还有" + day + "天," + age + "岁生日,有惊喜！");
		}
	}
}
