package java6683.more;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 郑逢
 * @date 2021/3/23 8:41 下午
 */
public class Calendar6683Controller {
	public DatePicker dpSelect;
	public Label lblMonth;
	public GridPane gpDayList;

	@FXML
	void onClick(ActionEvent actionEvent) {
		//获取输入的日期
		LocalDate date = dpSelect.getValue();
		//显示输入日期的月历
		showMonth(date);
	}

	/**
	 * 初始化方法
	 */
	@FXML
	void initialize() {
		//7行7列
		int col = gpDayList.getColumnConstraints().size();
		int row = gpDayList.getRowConstraints().size();
		//在GridPane中添加42个Label，第一行是星期，从第二行开始所以为1
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Label label = new Label();
				//为每个格子添加label
				gpDayList.add(label, j, i);
				//设置label元素居中
				GridPane.setHalignment(label, HPos.CENTER);
			}
		}
		//获取当前的日期
		LocalDate dateNow = LocalDate.now();
		//打开窗口时初始化当前日期的月历
		showMonth(dateNow);
	}

	/**
	 * @param date 日期
	 * @description 显示日期的月月历
	 */
	void showMonth(LocalDate date) {
		//月份的数字和中文数字格式
		String[][] strArray = {{"1", "一"}, {"2", "二"}, {"3", "三"}, {"4", "四"}, {"5", "五"}, {"6", "六"}, {"7", "七"}, {"8", "八"}, {"9", "九"}, {"10", "十"}, {"11", "十一"}, {"12", "十二"}};
		//月份
		String monthValue;
		try {
			monthValue = String.valueOf(date.getMonthValue());
		} catch (Exception e) {
			lblMonth.setText("请输入日期！");
			return;
		}
		for (String[] strings : strArray) {
			if (monthValue.equals(strings[0])) {
				monthValue = strings[1];
				break;
			}
		}
		lblMonth.setText(date.getYear() + "年" + monthValue + "月");
		//日期的月份的第一天。
		LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
		//获取当月的天数
		int dayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		//获取第-天的枚举值
		DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
		//将英文星期转成数字
		int value = dayOfWeek.getValue();
		//从一号开始
		int day = 1;
		//第一行的星期占8格
		int firstRow = 8;
		//全部格子50格
		int allGrid = 50;
		//读取GridPane中用于显示日历的42个Label控件
		for (int i = (firstRow + value); i < allGrid; i++) {
			Label label = (Label) gpDayList.getChildren().get(i);
			//为每个label设置日期
			label.setText(String.valueOf(day));
			++day;
			//本月最后一天日期之后的格子中的Label内容清空
			if (day > dayOfMonth + 1) {
				label.setText("");
			}
		}
		//本月第一天之前的格子中的Label内容清空
		for (int i = firstRow; i < firstRow + value; i++) {
			Label label = (Label) gpDayList.getChildren().get(i);
			label.setText("");
		}
	}
}
