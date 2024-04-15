package com.example.demoes.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2024年04月09日 17:05:00
 */
public class StdAnalyzer {

    public static final String strCh = "中华人名共和国简称中国，是一个有13亿人口的国家";
    public static final String strEn = "Dogs can not achieve a place, eyes can reach; ";

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = null;
        analyzer = new StandardAnalyzer(); // 标准分词
        System.out.println("标准分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new WhitespaceAnalyzer(); // 空格分词
        System.out.println("空格分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new SimpleAnalyzer(); // 简单分词
        System.out.println("简单分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new CJKAnalyzer(); // 二分法分词
        System.out.println("二分法分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new KeywordAnalyzer(); // 关键字分词
        System.out.println("关键字分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new StopAnalyzer(); // 停用词分词
        System.out.println("停用词分词：" + analyzer.getClass());
        analyzerStr(analyzer);
        analyzer = new SmartChineseAnalyzer(); // 中文智能分词
        System.out.println("中文智能分词：" + analyzer.getClass());
        analyzerStr(analyzer);
    }

    private static void analyzerStr(Analyzer analyzer) throws IOException {
        StringReader sReader = new StringReader(StdAnalyzer.strCh);
        TokenStream tokenStream = analyzer.tokenStream(StdAnalyzer.strCh, sReader);
        tokenStream.reset();
        CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
        System.out.println("分词结果：");
        while (tokenStream.incrementToken()) {
            System.out.print(attribute.toString() + "|");
        }
        System.out.println("\n");
        analyzer.close();
    }

}
