package com.imagetotext;

public class Main {
    public static void main(String[] args) {
        String text =  TextFinder.findText("/workspaces/image-to-text/finder/src/test/resources/test.png");
        System.out.println("Text found:" + text);
    }
}