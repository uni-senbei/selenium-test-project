package com.example.test;

import org.openqa.selenium.WebDriver;

public class SeleniumTestManager {

    private TestConfig config;
    private WebDriver driver;
    private String baseUrl;

    /**
     * 統一的な設定注入ポイント
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
        this.driver = config.createDriver();
        this.baseUrl = config.getBaseUrl();
    }

    /**
     * WebDriver を返す（SeleniumTest側から利用予定）
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 指定URLを開く（SeleniumTest側から利用予定）
     */
    public void open(String url) {
        // TODO: 実装はSTEP1-2で対応予定
    }

    /**
     * ブラウザを閉じる（SeleniumTest側から利用予定）
     */
    public void close() {
        // TODO: 実装はSTEP1-2で対応予定
    }
}
