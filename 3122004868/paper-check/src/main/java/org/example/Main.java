package org.example;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 检测传入参数
        if (!validate(args)) {
            return;
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
        if (isString(originTextURL)) {
            System.err.println("错误: 论文原文路径需要是一个合法的字符串。");
            return false;
        }
        if (isString(originAddTextURL)) {
            System.err.println("错误: 抄袭版论文路径需要是一个合法的字符串。");
            return false;
        }
        if (isString(targetURL)) {
            System.err.println("错误: 输出的答案文件路径需要是一个合法的字符串。");
            return false;
        }
        return true;
    }
    // 检查字符串是否为合法，是否为null或者为空
    private static boolean isString(String str) {
        return StringUtils.isBlank(str);
    }

}