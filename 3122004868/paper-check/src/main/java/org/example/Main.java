package org.example;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.example.utils.Jaccard;
import org.example.utils.Text;

import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 检测传入参数
        if (!validate(args)) {
            return;
        }
        String originTextURL = args[0];
        String originAddTextURL = args[1];
        String targetURL = args[2];

        // 读文件
        String originText;
        String originAddText;
        try {
            originText = FileUtil.readUtf8String(originTextURL).trim();
            originAddText = FileUtil.readUtf8String(originAddTextURL).trim();
        } catch (IORuntimeException e) {
            System.err.println(" 读取文件失败：" + e.getMessage());
            return;
        }

        // 分词
        Set<String> originTextWords = Text.ikSegment(originText);
        Set<String> originAddTextWords = Text.ikSegment(originAddText);

        // 计算相似度
        String similarity = String.format("%.2f", Jaccard.calculate(originTextWords, originAddTextWords));
        System.out.println("相似度：" + similarity);
        try {
            FileUtil.writeUtf8String(similarity, targetURL);
        } catch (IORuntimeException e) {
            System.err.println(" 写入文件失败：" + e.getMessage());
        }
    }
    private static boolean validate(String[] args) {
        // 检查参数数量是否正确
        if (args.length < 3) {
            System.err.println("传入参数错误，请检查你的输入");
            return false;
        }

        String originTextURL = args[0];
        String originAddTextURL = args[1];
        String targetURL = args[2];

        // 校验参数是否为空字符串
        if (isPathString(originTextURL)) {
            System.err.println("错误: 论文原文路径需要是一个合法的字符串。");
            return false;
        }
        if (isPathString(originAddTextURL)) {
            System.err.println("错误: 抄袭版论文路径需要是一个合法的字符串。");
            return false;
        }
        if (isPathString(targetURL)) {
            System.err.println("错误: 输出的答案文件路径需要是一个合法的字符串。");
            return false;
        }
        return true;
    }
    // 检查字符串是否为合法，是否为null或者为空
    private static boolean isPathString(String str) {
        return StringUtils.isBlank(str) || isPath(str);
    }
    private static boolean isPath(String path) {
        File file = new File(path);
        try {
            // 尝试获取绝对路径，检查路径格式是否正确
            file.getCanonicalPath();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}