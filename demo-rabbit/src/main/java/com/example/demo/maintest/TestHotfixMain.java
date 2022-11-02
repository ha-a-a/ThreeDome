package com.example.timeline.demo.maintest;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/5
 * @Desc
 */
public class TestHotfixMain {
    public static void testIsEmpty() {
        String str = " ";
        // apache和springframe框架的StringUtils
        System.out.println(StringUtils.isEmpty(str));
    }

    public static void testForNull(){
        List<String> array=new ArrayList<>();
        array.add("test");
        array.add("");
        array.add(null);
//        // null会出现nullExcption
        for (String str:array){
            System.out.println(str.length());
        }
        //null会出现nullExcption
        array.forEach(str->System.out.println(str.length()));
    }
    public static void testListToArray(){

        List<String> value = new ArrayList<>();
        value.add("qq");
        value.add("ww");
        value.add("xx");
        //error:[Ljava.lang.Object; cannot be cast to [Ljava.lang.String
//        String[] midArr = (String[]) value.toArray();
        //长度可变
        String[] midArr =  value.toArray(new String[0]);
        System.out.println(midArr.toString());
    }
    public static void main(String[] args){
//        testListToArray();
        //test git start feature
        //test git start feature2
        //edit release 1.1.0
        // git flow hotfix start '1'
    }
}
