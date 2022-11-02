package com.example.demo.object;

/**
 * 验证没有显示继承Object父类的类，在jdk =< 1.6.0时，在编译时进行Object继承（以验证）；
 * jdk>1.7.0时，在运行时jvm生成二进制时进行Object继承（未验证，因为Java文件只有在编辑，编译以及运行时jvm修改）
 *
 * 调用clone()的对象需要继承Cloneable，并且clone方法是半深拷贝半浅拷贝，对象里面的基本类型深拷贝，引用类型浅拷贝
 * @Author mengyuetang
 * @createTime 2020/9/17
 * @Desc
 */
public class MyObjectTest implements Cloneable {
    private Student student;
    private String str;

    public static void main(String[] args) {
        System.out.println("测试没有显示继承Object的类，在何时继承Object，是编译时还是运行时");
        Student student = new Student();
        student.setAge(21);
        student.setName("22222");
        MyObjectTest myObjectTest = new MyObjectTest();
        myObjectTest.setStudent(student);
        myObjectTest.setStr("tmy");
        try {
            MyObjectTest clone = (MyObjectTest)myObjectTest.clone();
            System.out.println("toString"+myObjectTest.toString()+","+clone.toString());
            System.out.println("hashCode"+myObjectTest.hashCode()+","+clone.hashCode());
            System.out.println("getStudent().toString"+myObjectTest.getStudent().toString()+","+clone.getStudent().toString());
            System.out.println("getStudent().hashCode"+myObjectTest.getStudent().hashCode()+","+clone.getStudent().hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    private void setStudent(Student student) {
        this.student = student;
    }

    private Student getStudent() {
        return student;
    }

    private String getStr() {
        return str;
    }

    private void setStr(String str) {
        this.str = str;
    }
}
