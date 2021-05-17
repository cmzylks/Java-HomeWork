package java6683.data;

import java.io.*;

class Student6683 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Student6683() {
    }

    public Student6683(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("name=%s,age=%d", name, age);
    }
}

public class StudyObjectData6683 {
    public static void main(String[] args) {
        writeStudent6683();
        readStudent6683();
    }

    public static void writeStudent6683() {
        File file = new File("write" + File.separator + "student6683.dat");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(new Student6683("郑逢", 22));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readStudent6683() {
        File file = new File("write" + File.separator + "student6683.dat");
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            System.out.println(o);
            Student6683 student6683 = (Student6683) o;
            System.out.println("姓名=" + student6683.getName() + ",年龄=" + student6683.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
