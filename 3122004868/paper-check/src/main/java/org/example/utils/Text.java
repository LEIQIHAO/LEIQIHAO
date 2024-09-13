package org.example.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

// 分词
public class Text {
    public static Set<String> ikSegment(String text) {
        // 使用 HashSet存储
        Set<String> words = new HashSet<>();
        IKSegmenter ik = new IKSegmenter(new StringReader(text), true);
        try {
            Lexeme lexeme;
            // 判断是否还有词语
            while ((lexeme = ik.next()) != null) {
                words.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            // 分词错误
            System.err.println("分词出错：" + e.getMessage());
        }
        return words;
    }
}
