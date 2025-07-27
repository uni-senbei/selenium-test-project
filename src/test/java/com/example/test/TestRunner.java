package com.example.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("TestRunner: テストを開始します。");

        Result result = JUnitCore.runClasses(SeleniumTestManager.class);

        for (Failure failure : result.getFailures()) {
            System.out.println("テスト失敗: " + failure.toString());
        }

        System.out.println("全テスト成功: " + result.wasSuccessful());
    }
}
