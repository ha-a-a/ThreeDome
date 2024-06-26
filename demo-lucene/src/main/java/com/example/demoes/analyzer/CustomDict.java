package com.example.demoes.analyzer;

import com.example.demoes.analyzer.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2024年04月09日 18:08:00
 */
public class CustomDict  {
    private static String str = "厉害了我的哥！中国环保部门即将发布治理北京\n雾霾的方法！";
    public static void main(String[] args)throws IOException {
        Analyzer analyzer = new IKAnalyzer6x(true);
        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str, reader);
        toStream.reset();
        CharTermAttribute teAttribute= toStream.getAttribute(
        CharTermAttribute.class);
        System.out.println("分词结果：");
        while(toStream.incrementToken()){
            System.out.print(teAttribute.toString()+ "|");
        }
        System.out.println("\n");
        analyzer.close();
    }
}
