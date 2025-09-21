package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestConfig {

    private final String baseUrl;
    private final Path screenshotDir;
    private final boolean isCiEnvironment;

    public TestConfig() {
        Properties props = new Properties();

        // CI環境判定 (System.getenv優先, -DisCiEnvironmentで上書き可能)
        String ciEnv = System.getProperty("isCiEnvironment", System.getenv("CI") != null ? "true" : "false");
        this.isCiEnvironment = Boolean.parseBoolean(ciEnv);

        // 設定ファイル読み込み
        try (InputStream in = Files.newInputStream(Paths.get("config.properties"))) {
            props.load(in);
            System.out.println("[INFO] Loaded config.properties");
        } catch (IOException e) {
            System.out.println("[WARN] config.properties not found. Using default values.");
        }

        // baseUrl の取得とチェック
        String rawBaseUrl = props.getProperty("baseUrl", "").trim();
        if (rawBaseUrl.isEmpty()) {
            throw new IllegalStateException(
                    "TestConfig: 'baseUrl' is missing in config.properties or is empty. " +
                            "Please define 'baseUrl' in config.properties or provide via constructor."
            );
        }
        this.baseUrl = normalizeUrl(rawBaseUrl);

        // screenshotDir の設定と作成
        String rawScreenshotDir = props.getProperty("screenshotDir", "screenshots");
        Path path = Paths.get(rawScreenshotDir).toAbsolutePath();
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            String message = "[WARN] Failed to create screenshot directory: " + path;
            if (isCiEnvironment) {
                throw new IllegalStateException("TestConfig: " + message + ". CI environment requires this directory.", e);
            } else {
                System.err.println(message + ". Continuing test execution.");
            }
        }
        this.screenshotDir = path;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Path getScreenshotDir() {
        return screenshotDir;
    }

    public WebDriver createDriver() {
        // 将来的にブラウザ切り替え対応可能
        return new ChromeDriver();
    }

    private String normalizeUrl(String url) {
        if (url == null || url.isEmpty()) {
            return "";
        }
        // 末尾スラッシュを1つに統一、:// は壊さない
        String normalized = url.replaceAll("(?<!:)//+", "/");
        if (!normalized.endsWith("/")) {
            normalized += "/";
        }
        return normalized;
    }
}
