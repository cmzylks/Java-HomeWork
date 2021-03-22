package java6683.lesson03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;

/**
 * @author 郑逢
 * @date 2021/3/21 10:04 下午
 */
public class EndDate6683 {
	public static void main(String[] args) {
		System.out.print("请按yyyy-mm-dd的格式输入开班日期：");
		Scanner sc = new Scanner(System.in);
		//获取键盘输入的日期
		String startDate = sc.nextLine();
		System.out.println("培训15天，结业日期：" + calc6683(startDate));
	}

	/**
	 * @param startDate 输入开始培训时间
	 * @return 培训结束后的时间
	 * @description  输入培训时长，计算培训结束后的时间
	 */
	public static String calc6683(String startDate) {
		//去除前后和中间空格
		startDate = startDate.trim().replaceAll(" ", "");
		//培训时间
		int duration = 15;
		//星期的英文和中文格式
		String[][] strArray = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"}, {"THURSDAY", "四"}, {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};
		TemporalAccessor formatter = null;
		LocalDate localDate = null;
		LocalDate endDate = null;
		try {
			//捕获没有按照ISO定制的时间格式输入的情况
			formatter = DateTimeFormatter.ISO_LOCAL_DATE.parse(startDate);
			localDate = LocalDate.from(formatter);
			//加上培训时间
			endDate = localDate.plusDays(duration);
		} catch (Exception e) {
			return null;
		}
		//年
		String year = String.valueOf(endDate.getYear());
		//月
		String month = String.valueOf(endDate.getMonthValue());
		//日
		String day = String.valueOf(endDate.getDayOfMonth());
		//星期
		String week = String.valueOf(endDate.getDayOfWeek());
		//获取行数
		for (int i = 0; i < strArray.length; i++) {
			if (week.equals(strArray[i][0])) {
				week = strArray[i][1];
				break;
			}
		}
		return year + "年" + month + "月" + day + "日" + " 星期" + week;
	}
}
