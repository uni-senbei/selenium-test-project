package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.Assert.*;

public class SeleniumTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // デフォルトは Chrome
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("[INFO] Chromeブラウザを起動しました。");
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                System.out.println("[INFO] Firefoxブラウザを起動しました。");
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("[INFO] Edgeブラウザを起動しました。");
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // ウィンドウ最大化
        driver.manage().window().maximize();
        // ページ読み込み後に明示的な待機を追加
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testSurveyForm() throws Exception {
        // アンケートフォームにアクセス
        driver.get("file://" + Paths.get("src/test/resources/input.html").toAbsolutePath());
        System.out.println("[INFO] input.html にアクセスしました。");

        // 名前を入力
        WebElement nameField = driver.findElement(By.id("first-name"));
        nameField.sendKeys("テスト太郎");

        // 性別を選択 (ラジオボタン)
        WebElement genderRadio = driver.findElement(By.id("radio-a")); // 男性
        genderRadio.click();

        // 興味のあることを選択 (チェックボックス)
        WebElement interestCheckbox = driver.findElement(By.id("check-strategy")); // 戦略と勢い
        if (!interestCheckbox.isSelected()) {
            interestCheckbox.click();
        }

        // ファイルアップロード
        WebElement fileInput = driver.findElement(By.id("image-upload"));
        File testImage = new File("src/test/resources/gihren.jpg");
        assertTrue("アップロードするテスト画像が存在しません", testImage.exists());
        fileInput.sendKeys(testImage.getAbsolutePath());

        // 好きなモビルスーツを選択
        WebElement msDropdown = driver.findElement(By.id("ms-pull-down"));
        msDropdown.sendKeys("グフ");

        // 送信ボタンをクリック
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();
        System.out.println("[INFO] フォームを送信しました。");

        // confirm.html で入力値を検証
        WebElement confirmationDetails = driver.findElement(By.id("confirmation-details"));
        String confirmationText = confirmationDetails.getText();

        assertTrue(confirmationText.contains("テスト太郎"));
        assertTrue(confirmationText.contains("メンズ"));
        assertTrue(confirmationText.contains("戦略と勢い"));
        assertTrue(confirmationText.contains("グフ"));

        // ファイル名の確認 (Edgeの場合はC:\fakepath\になるか要確認)
        // gihren.jpgを期待するように変更
        assertTrue(confirmationText.matches("(?s).*gihren\\.jpg.*"));
        System.out.println("[INFO] 入力値の検証に成功しました。");

        // スクリーンショットの保存
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), Paths.get("target/screenshot-confirmation.png"));
        System.out.println("[INFO] スクリーンショットを保存しました。");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("[INFO] ブラウザを閉じました。");
        }
    }
}
