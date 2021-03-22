package java6683.lesson03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

/**
 * @author 郑逢
 * @date 2021/3/22 8:07 上午
 */
public class LastDay6683 {
	public static void main(String[] args) {
		System.out.println("计算某月的最后一天");
		System.out.print("请按yyyy-mm的格式输入:");
		Scanner sc = new Scanner(System.in);
		String date = sc.nextLine();
		System.out.println("本月的最后一天是" + getLastDay6683(date) + "日");
	}

	/**
	 * @param date 输入的日期，yyyy-mm
	 * @return 返回月份的最后一天
	 * @description 计算输入日期的月份的最后一天
	 */
	public static String getLastDay6683(String date) {
		//去除前后和中间空格
		date = date.trim().replaceAll(" ", "");
		StringBuilder sb = new StringBuilder(date);
		try {
			//输入两位数月份的情况
			if (sb.charAt(5) == '0' || sb.length() == 7) {
				sb.append("-01");
				date = sb.toString();
			} else {
				//输入一位输入月份的情况
				sb.insert(5, "0");
				sb.append("-01");
				date = sb.toString();
			}
		} catch (Exception e) {
			return null;
		}

		TemporalAccessor dateTimeFormatter = null;
		LocalDate localDate = null;
		//捕获输入日期不合法的情况
		try {
			//格式化日期
			dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE.parse(date);
			localDate = LocalDate.from(dateTimeFormatter);
		} catch (Exception e) {
			return null;
		}
		//获取计算某个月最后一天的接口
		TemporalAdjuster temporalAdjuster = TemporalAdjusters.lastDayOfMonth();
		//获取某个月的最后一天
		LocalDate lastDayOfThisMonth = localDate.with(temporalAdjuster);
		return String.valueOf(lastDayOfThisMonth.getDayOfMonth());
	}
}
