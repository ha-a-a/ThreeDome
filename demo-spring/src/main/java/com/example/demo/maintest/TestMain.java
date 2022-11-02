package com.example.demo.maintest;

import com.example.demo.maintest.sortmodel.SelectedTags;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/5
 * @Desc
 */
public class TestMain {
    public static void testIsEmpty() {
        String str = " ";
        // apache和springframe框架的StringUtils
        System.out.println(StringUtils.isEmpty(str));
    }

    public static void testForNull() {
        List<String> array = new ArrayList<>();
        array.add("test");
        array.add("");
        array.add(null);
//        // null会出现nullExcption
        for (String str : array) {
            System.out.println(str.length());
        }
        //null会出现nullExcption
        array.forEach(str -> System.out.println(str.length()));
    }

    public static void testListToArray() {

        List<String> value = new ArrayList<>();
        value.add("qq");
        value.add("ww");
        value.add("xx");
        //error:[Ljava.lang.Object; cannot be cast to [Ljava.lang.String
//        String[] midArr = (String[]) value.toArray();
        //长度可变
        String[] midArr = value.toArray(new String[0]);
        System.out.println(midArr.toString());
    }

    public static void testOverrideEquals() {
        SelectedTags selectedTags = new SelectedTags();
        selectedTags.setTagId("11111");
        selectedTags.setTagName("test1");
        selectedTags.setStartTime(1556499700010L);
        selectedTags.setCreateTime(System.currentTimeMillis());
        selectedTags.setCreator("system1");

        SelectedTags selectedTags1 = new SelectedTags();
        selectedTags1.setTagId("11112");
        selectedTags1.setTagName("test2");
        selectedTags1.setStartTime(1556499600010L);
        selectedTags1.setCreateTime(System.currentTimeMillis());
        selectedTags1.setCreator("system2");

        SelectedTags selectedTags2 = new SelectedTags();
        selectedTags2.setTagId("11111");
        selectedTags2.setTagName("test3");
        selectedTags2.setStartTime(1556499600030L);
        selectedTags2.setCreateTime(System.currentTimeMillis());
        selectedTags2.setCreator("system3");

        System.out.println("重写equals的四大原则之自反性 x.equals(x)=>true ,return " + selectedTags.equals(selectedTags));
        System.out.println("重写equals的四大原则之对称性 如果x.equals(y)=>true return " + selectedTags.equals(selectedTags1) + ",那么y.equals(x)=>true return " + selectedTags1.equals(selectedTags));
        System.out.println("重写equals的四大原则之传递性 如果x.equals(y)=>true,y.equals(z)=>true return " + selectedTags.equals(selectedTags1) + "," + selectedTags1.equals(selectedTags2) + ",那么x.equals(z)=>true ,return " + selectedTags.equals(selectedTags2));
        // 反复调用
        for (int i = 0; i < 10; i++) {
            System.out.println("重写equals的四大原则之一致性 调用第" + i + "次" + selectedTags.equals(selectedTags1));
        }
        System.out.println("重写equals的四大原则之非null引用 selectedTags.equals(null)=>false return " + selectedTags.equals(null));
        System.out.println("重写equals时必写hashcode, 如果y.equals(x)=>true return " + selectedTags.equals(selectedTags1) + ",那么y.hashCode()==x.hashCode()=>true ,return " + (selectedTags.hashCode() == selectedTags1.hashCode()));
    }

    private static void stringBufferInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        StringBuilder nextSb = new StringBuilder();
        nextSb.append("nextTest");
        sb.insert(0, nextSb);
        System.out.print("sb:" + sb + ",nextSb:" + nextSb);

    }

    private static void matches() {
        String content = "";
        String newsTitle = "2019-06-169 12:09:03";
        String regex = "^天啦撸！.*竟然抢了.*的封面$";
        System.out.print("result=" + Pattern.matches(regex, newsTitle));
        if (Pattern.matches(regex, newsTitle)) {
            content = newsTitle;
        }
        System.out.print("\n"+"content=" + content);
    }

    public static void main(String[] args) {
//        testListToArray();
        //test git start feature
        //test git start feature2
        //edit release 1.1.0
//        testOverrideEquals();
//        stringBufferInsert();
        matches();
    }
}
