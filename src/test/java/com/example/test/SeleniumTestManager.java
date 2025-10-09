package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Objects;

/**
 * SeleniumTestManager
 *
 * テスト全体の WebDriver ライフサイクル管理を担当。
 *
 * - STEP 1: 構成情報の読み込み（TestConfig 経由）
 * - STEP 2: openBaseUrl() によるテストページ起動
 * - STEP 3: close() / shutdown() による終了処理
 */
public class SeleniumTestManager {

    private TestConfig config;
    private WebDriver driver;

    /** 設定情報の注入 */
    public void configure(TestConfig config) {
        this.config = config;
    }

    /** WebDriver の生成 */
    public WebDriver createDriver() {
        if (config == null) {
            throw new IllegalStateException("TestConfig が設定されていません。configure() を先に呼び出してください。");
        }

        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
        this.driver = new ChromeDriver();
        return this.driver;
    }

    /** Base URLを開く */
    public void openBaseUrl(String path) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver が初期化されていません。setUp() を先に呼び出してください。");
        }

        // ローカルファイルの絶対パスに変換（Windows対応）
        String url;
        if (path.startsWith("http")) {
            url = path;
        } else {
            java.io.File file = new java.io.File(path);
            url = "file:///" + file.getAbsolutePath().replace("\\", "/");
        }

        driver.get(url);
    }

    /** STEP 3: 各テスト後の終了処理 */
    public void close() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("[WARN] Failed to quit WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    /** STEP 3: クラス全体終了時の最終クリーンアップ */
    public void shutdown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("[WARN] Failed to quit remaining WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }

        System.out.println("[INFO] SeleniumTestManager shutdown complete.");
    }
}
