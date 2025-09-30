package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumTestManager {

    private TestConfig config;
    private WebDriver driver;
    private String baseUrl;

    /**
     * TestConfigの設定
     */
    public void configure(TestConfig config) {
        this.config = config;
    }

    /**
     * 各テスト前に呼び出すセットアップ処理
     */
    public void setUp() {
        if (config == null) {
            throw new IllegalStateException("TestConfigが設定されていません。先にconfigure()を呼び出してください。");
        }
        this.driver = createDriver();
        this.baseUrl = config.getBaseUrl();
    }

    /**
     * WebDriverインスタンスを生成する最小実装
     */
    public WebDriver createDriver() {
        // TestConfig に実装されている createDriver() を呼ぶだけ
        return config.createDriver();
    }


    /**
     * baseUrl を開く（Step 2で実装予定）
     */
    public void openBaseUrl() {
        // TODO: Step 2 で baseUrl を開く処理を実装
    }

    /**
     * 各テスト後に呼び出すクローズ処理（Step 3で実装予定）
     */
    public void close() {
        // TODO: Step 3 でリソース解放処理を実装
    }

    /**
     * 全テスト完了後に呼び出すシャットダウン処理（Step 3で実装予定）
     */
    public void shutdown() {
        // TODO: Step 3 で例外安全なシャットダウン処理を実装
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
