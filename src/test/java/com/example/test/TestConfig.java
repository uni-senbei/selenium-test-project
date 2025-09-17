package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * テスト実行設定クラス
 * - WebDriver生成
 * - baseUrl管理
 * - スクリーンショット保存先管理
 */
public class TestConfig {

    private final String baseUrl;
    private final String screenshotDir;
    private final Properties props = new Properties();

    public TestConfig() {
        // 設定ファイル読み込み
        try (InputStream in = getClass().getResourceAsStream("/config.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                System.out.println("警告: config.properties が見つかりません。デフォルト値を使用します。");
            }
        } catch (IOException e) {
            System.out.println("設定ファイル読み込みエラー: " + e.getMessage());
        }

        // baseUrl とスクリーンショットディレクトリ設定
        String rawBaseUrl = props.getProperty("base.url", "http://localhost:8080");
        this.baseUrl = normalizeUrl(rawBaseUrl);
        this.screenshotDir = props.getProperty("screenshot.dir", "target/screenshots");
    }

    /**
     * URL正規化処理
     * // を1本にするが、http:// の :// は残す
     */
    private String normalizeUrl(String url) {
        return url.replaceAll("(?<!:)//+", "/");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getScreenshotDir() {
        return screenshotDir;
    }

    /**
     * WebDriver生成
     * 将来的にブラウザ切替が可能な拡張ポイント
     */
    public WebDriver createDriver() {
        return new ChromeDriver();
    }
}
