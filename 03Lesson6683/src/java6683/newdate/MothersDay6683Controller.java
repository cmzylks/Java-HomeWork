package java6683.newdate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * @author 郑逢
 * 控制类
 */
public class MothersDay6683Controller {

	@FXML
	private Spinner<?> sYear;

	@FXML
	private Label lblMothersDay;

	@FXML
	void buttonClick(MouseEvent event) {
		//获取输入的年份
		String year = String.valueOf(sYear.getValue());
		//得到输入年份的5月份的第二个星期的星期日
		LocalDate date = LocalDate.of(Integer.parseInt(year), 5, 1).plusWeeks(1).with(DayOfWeek.SUNDAY);
		//使用默认的日期格式
		String motherDay = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
		//将日期输出出来
		lblMothersDay.setText(motherDay);
	}

}
