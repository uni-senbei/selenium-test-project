package com.example.test;

import com.example.SeleniumTest; // 正しいパッケージパスに修正してください

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class SeleniumTestManager {

    private SeleniumTest seleniumTest;

    @Before
    public void setUpManager() {
        seleniumTest = new SeleniumTest();
    }

    @After
    public void tearDownManager() {
        seleniumTest.tearDown();
    }

    @Test
    public void testEdgeDriverInitialization() {
        seleniumTest.setup("edge");  // 各テストごとにブラウザを明示

        WebDriver driver = seleniumTest.getDriver();  // driverに直接アクセスせずgetter経由

        assertNotNull("ドライバが初期化されていません", driver);

        // 追加の検証（例: タイトルが取得できるか）
        driver.get("https://example.com");
        String title = driver.getTitle();
        assertNotNull("タイトルが取得できません", title);
    }

    // 追加のテストメソッドは以下に記述できます
}
