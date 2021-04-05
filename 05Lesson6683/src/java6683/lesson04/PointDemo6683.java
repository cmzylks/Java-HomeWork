package java6683.lesson04;

import java.util.*;

public class PointDemo6683 {
	static class Point6683 implements Comparable<Point6683> {
		private int x;
		private int y;

		public Point6683() {
		}

		public Point6683(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point6683 point6683 = (Point6683) o;
			return x == point6683.x && y == point6683.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}


		@Override
		public int compareTo(Point6683 o) {
			return x - o.x;
		}
	}

	public static void main(String[] args) {
		demo1();
		demo2();
		demo3();
	}

	public static void demo1() {
		Collection<Point6683> list = new ArrayList<>();
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 31));
		list.add(new Point6683(11, 15));
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 17));
		list.add(new Point6683(13, 15));
		System.out.println(list.contains(new Point6683(11, 15)));

	}

	public static void demo2() {
		Collection<Point6683> list = new HashSet<>();
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 31));
		list.add(new Point6683(11, 15));
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 17));
		list.add(new Point6683(13, 15));
		System.out.println(list);
	}

	public static void demo3() {
		Collection<Point6683> list = new TreeSet<>();
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 31));
		list.add(new Point6683(11, 15));
		list.add(new Point6683(1, 2));
		list.add(new Point6683(21, 17));
		list.add(new Point6683(13, 15));
		System.out.println(list);
	}


}
