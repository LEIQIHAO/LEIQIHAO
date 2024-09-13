package org.example.utils;

import org.apache.commons.collections4.SetUtils;

import java.util.Set;

public class Jaccard {

    // 计算 Jaccard 相似度
    // 用 Set 来计算
    public static double calculate(Set<String> set1, Set<String> set2) {
        int intersectionSize = SetUtils.intersection(set1, set2).size();
        int unionSize = SetUtils.union(set1, set2).size();
        return (unionSize == 0) ? 0.0 : (double) intersectionSize / unionSize;
    }
}
