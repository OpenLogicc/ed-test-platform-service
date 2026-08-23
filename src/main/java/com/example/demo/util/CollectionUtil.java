package com.example.demo.util;

import java.util.List;

public class CollectionUtil {

    public static <R> boolean isNullOrEmpty(List<R> list) {
        return list == null || list.isEmpty();
    }
}
