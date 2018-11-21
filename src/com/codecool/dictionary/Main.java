package com.codecool.dictionary;

public class Main {
    public static void main(String[] args) {
        Dictionary<String, Integer> map = new Dictionary<>();
        map.put("test", 1);
        System.out.println(map.get("test"));
        map.put("test", 7);
        System.out.println(map.get("test"));
        System.out.println(map.get("not in here"));
        map.remove("test");
        System.out.println(map.get("test"));
    }
}
