package java6683.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Elxect
 */
public class TaskGroupsDemo6683 {
	public static void main(String[] args) throws FileNotFoundException {
		//文本地址
		String url = "data/StudentList.txt";
		//根据文本地址获取学生名单
		ArrayList<Student6683> studentList = getStudentList(url);
		//获取存储方案
		ArrayList<Integer> group = TaskGroups6683.getGroupingScheme(studentList.size());
		System.out.println("班级人数:" + studentList.size());
		System.out.println("经过" + TaskGroups6683.count + "次循环,得到分组方案:分为" + group.size() + "组" + Arrays.toString(group.toArray(new Integer[0])));
		//获取存储分组名单
		ArrayList<Student6683[]> teamMembers = TaskGroups6683.getTeamMembers(group, studentList);
		for (int i = 0; i < teamMembers.size(); i++) {
			System.out.println("第" + (i + 1) + "组" + Arrays.toString(teamMembers.get(i)) + teamMembers.get(i).length + "人");
		}

	}

	/**
	 * 根据所给的文件地址，返回文件中的数据集合
	 *
	 * @param url 文件地址
	 * @return list 学生对象集合
	 * @throws FileNotFoundException 文件异常
	 */
	public static ArrayList<Student6683> getStudentList(String url) throws FileNotFoundException {
		//从文本读取学生名单
		Scanner sc = new Scanner(new File(url));
		//创建学生空集合对象
		ArrayList<Student6683> list = new ArrayList<>();
		//将每个学生对象添加到集合中
		while (sc.hasNext()) {
			String line = sc.nextLine();
			list.add(new Student6683(line.split(" ")[1]));
		}
		return list;
	}
}
