package java6683.group;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Elxect
 */
public abstract class TaskGroups6683 {
	/**
	 * 循环次数
	 */
	static int count = 1;
	/**
	 * 随机数对象
	 */
	private static final Random RANDOM = new Random();

	/**
	 * 通过学生人数来生成分组方案
	 *
	 * @param size 人数
	 * @return studentNum 分组方案集合
	 */
	public static ArrayList<Integer> getGroupingScheme(int size) {
		//创建空集合
		ArrayList<Integer> studentNum = new ArrayList<>();
		while (true) {
			//随机生成[5,7]的整数
			int i = RANDOM.nextInt(7 - 5 + 1) + 5;
			//将每个数添加到集合中
			studentNum.add(i);
			//如果集合中的数的和等于人数，就表示分组完毕，退出循环
			if (arraySum(studentNum) == size) {
				break;
			}
			//如果大于人数则表示，该分组方案不适合
			if (arraySum(studentNum) > size) {
				//清空集合重新分组
				studentNum.clear();
				//增加循环次数
				++count;
			}
		}
		return studentNum;
	}

	/**
	 * 根据分组方案来为每位学生随机分配小组
	 *
	 * @param list         分组方案
	 * @param student6683s 学生清单
	 * @return group 每组人员集合
	 */
	public static ArrayList<Student6683[]> getTeamMembers(ArrayList<Integer> list, ArrayList<Student6683> student6683s) {
		//创建小组集合
		ArrayList<Student6683[]> group = new ArrayList<>();
		for (Integer length : list) {
			//跟据分组方案分组
			Student6683[] groupList = new Student6683[length];
			//将分配好的小组添加到集合中
			group.add(groupList);
		}
		for (Student6683[] s : group) {
			if (student6683s.size() > 0) {
				for (int i = 0; i < s.length; i++) {
					//以产生的随机数为序号
					int index = RANDOM.nextInt(student6683s.size());
					//从班级名单中提取并删除学生，把提取的学生添加到分组名单中
					s[i] = student6683s.get(index);
					student6683s.remove(index);
				}
			}
		}
		return group;
	}

	/**
	 * 统计集合内数的和
	 *
	 * @param arrayList 集合对象
	 * @return sum 和
	 */
	private static int arraySum(ArrayList<Integer> arrayList) {
		int sum = 0;
		for (Integer integer : arrayList) {
			sum += integer;
		}
		return sum;
	}
}
