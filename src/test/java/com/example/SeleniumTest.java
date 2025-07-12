package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver; // ★追加: FirefoxDriverのためにインポート
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.time.Duration; // Durationクラスのためにインポート
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTest {

    private static final String FILE_PATH;
    private static final String UPLOAD_FILE_PATH;
    private static final String INTERESTS_CSV_PATH = "src/test/resources/interests.csv";
    private static final String GENDER_CSV_PATH = "src/test/resources/gender.csv";
    private static final String MS_CSV_PATH = "src/test/resources/favorite_ms.csv";

    private WebDriver driver;
    private WebDriverWait wait; // WebDriverWaitインスタンスをフィールドとして保持

    // ★追加：ブラウザごとのデフォルト待機時間を設定するための変数
    private Duration defaultWaitDuration;

    // static初期化ブロックで定数を設定（プログラム起動時に一度だけ実行）
    static {
        String inputHtmlRelativePath = "src/test/resources/input.html";
        String uploadFileRelativePath = "src/test/resources/gihren.jpg";

        String resolvedFilePath = null;
        String resolvedUploadPath = null;

        try {
            resolvedFilePath = new File(inputHtmlRelativePath).toURI().toURL().toString();
            resolvedUploadPath = new File(uploadFileRelativePath).getAbsolutePath();
        } catch (MalformedURLException e) {
            System.out.println("URL変換エラーが発生しました。ファイルパスを確認してください: " + e.getMessage()); // System.err.printlnから変更
            throw new RuntimeException("テストファイルのURL取得に失敗しました。パス設定を確認してください。", e);
        }

        FILE_PATH = resolvedFilePath;
        UPLOAD_FILE_PATH = resolvedUploadPath;
    }

    /**
     * CSVの各行を保持するための内部クラス
     */
    private static class InterestOption {
        String value;
        String label;
        boolean defaultSelected;

        public InterestOption(String value, String label, boolean defaultSelected) {
            this.value = value;
            this.label = label;
            this.defaultSelected = defaultSelected;
        }

        // ゲッターメソッド
        public String getValue() { return value; }
        public String getLabel() { return label; }
        public boolean isDefaultSelected() { return defaultSelected; }
    }

    /**
     * CSVの各行を保持するための内部クラス (gender.csv)
     */
    private static class GenderOption {
        String value;
        String label;
        String id;

        public GenderOption(String value, String label, String id) {
            this.value = value;
            this.label = label;
            this.id = id;
        }
        public String getValue() { return value; }
        public String getLabel() { return label; }
        public String getId() { return id; }
    }

    /**
     * CSVの各行を保持するための内部クラス (favorite_ms.csv)
     */
    private static class MsOption {
        String value;
        String label;

        public MsOption(String value, String label) {
            this.value = value;
            this.label = label;
        }
        public String getValue() { return value; }
        public String getLabel() { return label; }
    }


    /**
     * interests.csvを読み込んでInterestOptionのリストを返すメソッド
     * @return 読み込んだInterestOptionのリスト
     */
    private List<InterestOption> readInterestsFromCsv() {
        List<InterestOption> options = new ArrayList<>();
        File csvFile = new File(INTERESTS_CSV_PATH);
        if (!csvFile.exists()) {
            org.junit.Assert.fail("interests.csvファイルが見つかりません: " + INTERESTS_CSV_PATH);
        }

        try (Reader in = new FileReader(csvFile)) {
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build());

            for (CSVRecord record : parser) {
                String value = record.get("value");
                String label = record.get("label");
                boolean defaultSelected = Boolean.parseBoolean(record.get("default_selected"));
                options.add(new InterestOption(value, label, defaultSelected));
            }
        } catch (IOException e) {
            org.junit.Assert.fail("interests.csvの読み込み中にエラーが発生しました: " + e.getMessage());
        }
        return options;
    }

    /**
     * gender.csvを読み込んでGenderOptionのリストを返すメソッド
     * @return 読み込んだGenderOptionのリスト
     */
    private List<GenderOption> readGenderFromCsv() {
        List<GenderOption> options = new ArrayList<>();
        File csvFile = new File(GENDER_CSV_PATH);
        if (!csvFile.exists()) {
            org.junit.Assert.fail("gender.csvファイルが見つかりません: " + GENDER_CSV_PATH);
        }

        try (Reader in = new FileReader(csvFile)) {
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build());

            for (CSVRecord record : parser) {
                String value = record.get("value");
                String label = record.get("label");
                String id = record.get("id");
                options.add(new GenderOption(value, label, id));
            }
        } catch (IOException e) {
            org.junit.Assert.fail("gender.csvの読み込み中にエラーが発生しました: " + e.getMessage());
        }
        return options;
    }

    /**
     * favorite_ms.csvを読み込んでMsOptionのリストを返すメソッド
     * @return 読み込んだMsOptionのリスト
     */
    private List<MsOption> readMsFromCsv() {
        List<MsOption> options = new ArrayList<>();
        File csvFile = new File(MS_CSV_PATH);
        if (!csvFile.exists()) {
            org.junit.Assert.fail("favorite_ms.csvファイルが見つかりません: " + MS_CSV_PATH);
        }

        try (Reader in = new FileReader(csvFile)) {
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build());

            for (CSVRecord record : parser) {
                String value = record.get("value");
                String label = record.get("label");
                options.add(new MsOption(value, label));
            }
        } catch (IOException e) {
            org.junit.Assert.fail("favorite_ms.csvの読み込み中にエラーが発生しました: " + e.getMessage());
        }
        return options;
    }

    /**
     * テスト失敗時にスクリーンショットを撮るヘルパーメソッド
     * @param driver WebDriverインスタンス
     * @param testName スクリーンショットファイル名に含めるテスト名
     */
    private void takeScreenshot(WebDriver driver, String testName) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File screenshotDir = new File("target/screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdirs();
                }
                String fileName = testName + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().substring(0, 8) + ".png";
                File destFile = new File(screenshotDir, fileName);
                FileUtils.copyFile(screenshot, destFile);
                System.out.println("スクリーンショットを保存しました: " + destFile.getAbsolutePath()); // System.err.printlnから変更
            } catch (IOException e) {
                System.out.println("スクリーンショットの保存に失敗しました: " + e.getMessage()); // System.err.printlnから変更
            }
        }
    }

    // 各テストメソッド実行前にWebDriverを初期化するメソッド ★修正
    @Before
    public void setup() {
        String browserName = System.getProperty("browser", "chrome").toLowerCase(); // デフォルトはChrome

        switch (browserName) {
            case "firefox":
                // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver"); // Selenium Managerが自動的にドライバーを解決できない場合に必要
                driver = new FirefoxDriver();
                // ★追加：Firefoxのデフォルト待機時間を設定
                defaultWaitDuration = Duration.ofSeconds(25); // Chromeより少し長めに設定
                System.out.println("Firefoxでテストを開始します。");
                break;
            // case "edge": // 将来Edgeを追加する際にコメント解除
            //     // System.setProperty("webdriver.edge.driver", "path/to/msedgedriver.exe"); // Selenium Managerが自動的にドライバーを解決できない場合に必要
            //     // driver = new EdgeDriver();
            //     // ★追加：Edgeのデフォルト待機時間を設定
            //     // defaultWaitDuration = Duration.ofSeconds(30); // 例として更に長めに設定
            //     // System.out.println("Edgeでテストを開始します。");
            //     // break;
            case "chrome":
            default:
                // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Selenium Managerが自動的にドライバーを解決できない場合に必要
                driver = new ChromeDriver();
                // ★追加：Chromeのデフォルト待機時間を設定
                defaultWaitDuration = Duration.ofSeconds(20);
                System.out.println("Chromeでテストを開始します。");
                break;
        }
        // 共通のWebDriver設定
        driver.manage().window().maximize();

        // ★修正：WebDriverWaitのインスタンスを共通化し、動的な待機時間を使用
        wait = new WebDriverWait(driver, defaultWaitDuration);
    }

    // 各テストメソッド実行後にWebDriverを終了するメソッド
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFormInputAndRadioButtons() {
        try {
            driver.get(FILE_PATH);

            // ページのタイトル、H1、Pタグの検証
            String pageTitle = driver.getTitle();
            assertEquals("ページのタイトルが一致しません", "アンケートフォーム入力ページ".trim(), pageTitle.trim());

            WebElement h1Element = driver.findElement(By.tagName("h1"));
            String h1Text = h1Element.getText();
            assertEquals("H1見出しのテキストが一致しません", "アンケート".trim(), h1Text.trim());

            WebElement pElement = driver.findElement(By.xpath("//p[text()='Pタグ復活']"));
            String pText = pElement.getText();
            assertEquals("Pタグのテキストが一致しません", "Pタグ復活".trim(), pText.trim());

            // first-name テキストボックスに文字入力と検証
            WebElement firstNameInput = driver.findElement(By.id("first-name"));
            String inputName = "ガンダム";
            firstNameInput.sendKeys(inputName);
            String actualFirstName = firstNameInput.getAttribute("value");
            assertEquals("first-name の入力値が一致しません", inputName, actualFirstName);
            System.out.println("first-name に '" + actualFirstName + "' を入力しました。");

            System.out.println("--- ラジオボタン選択テスト開始 ---");

            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ★削除
            WebElement radioA = wait.until(ExpectedConditions.elementToBeClickable(By.id("radio-a")));
            radioA.click();
            System.out.println("「はい」ラジオボタンをクリックしました。");

            assertTrue("「はい」ラジオボタンが選択されていません", radioA.isSelected());
            System.out.println("「はい」ラジオボタンが選択されていることを確認しました。");

            // Thread.sleep(1000); // ★削除

        } catch (Throwable t) {
            takeScreenshot(driver, "testFormInputAndRadioButtons");
            t.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + t.getMessage());
        }
    }

    @Test
    public void testCheckboxes() {
        try {
            driver.get(FILE_PATH);

            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // ★削除

            System.out.println("--- チェックボックス選択テスト開始 ---");

            // CSVから読み込んだデータに基づいてチェックボックスを操作
            List<InterestOption> interests = readInterestsFromCsv();

            // 「戦略と勢い」(strategy)のチェックボックスを確認
            WebElement strategyCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("check-strategy")));
            wait.until(ExpectedConditions.elementToBeClickable(strategyCheckbox));
            assertTrue("check-strategy がデフォルトで選択されていません", strategyCheckbox.isSelected());
            System.out.println("「戦略と勢い」チェックボックスがデフォルトで選択されていることを確認しました。");

            // 「政治とタイミング」(politics)のチェックボックスを選択
            WebElement politicsCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("check-politics")));
            wait.until(ExpectedConditions.elementToBeClickable(politicsCheckbox));
            politicsCheckbox.click();
            assertTrue("check-politics が選択されていません", politicsCheckbox.isSelected());
            System.out.println("「政治とタイミング」チェックボックスを選択しました。");

            // 「戦略と勢い」のチェックを外す
            wait.until(ExpectedConditions.elementToBeClickable(strategyCheckbox));
            strategyCheckbox.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.id("check-strategy"))));
            assertFalse("check-strategy のチェックが外れていません", strategyCheckbox.isSelected());
            System.out.println("「戦略と勢い」チェックボックスのチェックを外しました。");

            // Thread.sleep(1000); // ★削除

        } catch (Throwable t) {
            takeScreenshot(driver, "testCheckboxes");
            t.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + t.getMessage());
        }
    }


    @Test
    public void testFileUpload() {
        try {
            driver.get(FILE_PATH);

            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ★削除

            System.out.println("--- ファイルアップロードテスト開始 ---");

            // UPLOAD_FILE_PATH 定数を使用
            File file = new File(UPLOAD_FILE_PATH);

            if (!file.exists()) {
                System.out.println("エラー: 指定されたファイルが見つかりません: " + UPLOAD_FILE_PATH); // System.err.printlnから変更
                org.junit.Assert.fail("アップロードテスト用のファイルが見つかりません。パスを確認してください。");
            }

            // 2. type="file" の要素を見つける
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("image")));

            // 3. ファイルの絶対パスを sendKeys() で入力する
            fileInput.sendKeys(UPLOAD_FILE_PATH);
            System.out.println("ファイル '" + file.getName() + "' を選択しました。");

            // 4. (オプション) ファイルが選択されたことを検証する
            String uploadedFileName = fileInput.getAttribute("value");
            String expectedFileName = file.getName();

            assertTrue("ファイルが正しく選択されていません。選択されたファイル名: " + uploadedFileName,
                    uploadedFileName.contains(expectedFileName));
            System.out.println("ファイルが正しく選択されていることを確認しました。");

            // Thread.sleep(3000); // ★削除

        } catch (Throwable t) {
            takeScreenshot(driver, "testFileUpload");
            t.printStackTrace();
            org.junit.Assert.fail("ファイルアップロードテスト中にエラーが発生しました: " + t.getMessage());
        }
    }

    @Test
    public void testDropdown() {
        try {
            driver.get(FILE_PATH);

            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // ★削除

            System.out.println("--- プルダウン選択テスト開始 ---");

            // まず要素の存在を確認
            WebElement msDropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ms-pull-down")));
            // その後、クリック可能になるまで待機
            wait.until(ExpectedConditions.elementToBeClickable(msDropdownElement));
            Select msDropdown = new Select(msDropdownElement);

            msDropdown.selectByValue("アッガイ");
            System.out.println("「アッガイ」をプルダウンから選択しました。");

            assertEquals("選択されたオプションのテキストが一致しません", "アッガイ", msDropdown.getFirstSelectedOption().getText().trim());
            System.out.println("「アッガイ」が選択されていることを確認しました。");

            // Thread.sleep(1000); // ★削除

        } catch (Throwable t) {
            takeScreenshot(driver, "testDropdown");
            t.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + t.getMessage());
        }
    }


    @Test
    public void testFormSubmissionAndConfirmation() {
        try {
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ★削除

            // CSVからデータを読み込む
            List<InterestOption> interestsOptions = readInterestsFromCsv();
            List<GenderOption> genderOptions = readGenderFromCsv();
            List<MsOption> msOptions = readMsFromCsv();

            // 1. input.html を開く
            driver.get(FILE_PATH);
            System.out.println("--- フォーム送信と確認ページテスト開始 ---");

            // 2. フォームにデータを入力
            WebElement firstNameInput = driver.findElement(By.id("first-name"));
            String testName = "テスト太郎";
            firstNameInput.sendKeys(testName);

            // 性別をCSVから読み込んだデータに基づいて操作
            String selectedGenderLabel = "";
            for (GenderOption option : genderOptions) {
                if ("no_answer".equals(option.getValue())) {
                    WebElement genderRadio = driver.findElement(By.id(option.getId()));
                    genderRadio.click();
                    selectedGenderLabel = option.getLabel();
                    break;
                }
            }

            // 興味のあることのチェックボックスをCSVから読み込んだデータに基づいて操作
            List<String> expectedSelectedInterestsLabels = new ArrayList<>();
            List<String> expectedSelectedInterestsValues = new ArrayList<>();

            for (InterestOption option : interestsOptions) {
                if ("politics".equals(option.getValue())) {
                    WebElement checkbox = driver.findElement(By.id("check-" + option.getValue()));
                    checkbox.click();
                    expectedSelectedInterestsLabels.add(option.getLabel());
                    expectedSelectedInterestsValues.add(option.getValue());
                }
                if ("strategy".equals(option.getValue()) && option.isDefaultSelected()) {
                    expectedSelectedInterestsLabels.add(option.getLabel());
                    expectedSelectedInterestsValues.add(option.getValue());
                }
            }

            // ファイルアップロード (ここではファイル名だけ確認するため、実際のファイルパスを使う)
            WebElement fileInput = driver.findElement(By.id("image-upload"));
            File uploadTestFile = new File(UPLOAD_FILE_PATH);
            if (!uploadTestFile.exists()) {
                org.junit.Assert.fail("アップロードテスト用のファイルが見つかりません: " + UPLOAD_FILE_PATH);
            }
            fileInput.sendKeys(UPLOAD_FILE_PATH);

            // 好きなモビルスーツをCSVから読み込んだデータに基づいて操作
            String selectedMsLabel = "";
            for (MsOption option : msOptions) {
                if ("シャアザク".equals(option.getValue())) {
                    WebElement msDropdownElement = driver.findElement(By.id("ms-pull-down"));
                    Select msDropdown = new Select(msDropdownElement);
                    msDropdown.selectByValue(option.getValue());
                    selectedMsLabel = option.getLabel();
                    break;
                }
            }

            // 3. 送信ボタンをクリック
            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();
            System.out.println("フォームを送信しました。");

            // 4. confirm.html に遷移したことを確認
            wait.until(ExpectedConditions.urlContains("confirm.html"));
            System.out.println("確認ページに遷移しました: " + driver.getCurrentUrl());
            assertEquals("ページのタイトルが一致しません", "入力内容確認ページ", driver.getTitle().trim());

            // 5. confirm.html 上で入力内容が正しく表示されているか検証
            WebElement confirmationDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmation-details")));
            String actualText = confirmationDetails.getText();
            System.out.println("確認ページの実際の内容:\n" + actualText);

            // 名前が正しいか検証
            assertTrue("名前が確認ページに表示されていません", actualText.contains("名前: " + testName));
            System.out.println("名前 '" + testName + "' の表示を確認。");

            //テスト失敗時のスクリーンショット取得確認のため、名前が正しくない検証を残す
            // assertTrue("名前が確認ページに表示されていません", actualText.contains("名前： " + testName)); // 「：」を全角に変更
            // System.out.println("名前 '" + testName + "' の表示を確認。");


            // 性別が正しいか検証
            assertTrue("性別が確認ページに表示されていません", actualText.contains("性別: " + selectedGenderLabel));
            System.out.println("性別 '" + selectedGenderLabel + "' の表示を確認。");

            // 興味のあることが正しいか検証 (CSVから取得したラベルで検証)
            String actualInterestsText = "";
            for (String label : expectedSelectedInterestsLabels) {
                assertTrue("興味のあること「" + label + "」が表示されていません", actualText.contains(label));
                if (!actualInterestsText.isEmpty()) {
                    actualInterestsText += ", ";
                }
                actualInterestsText += label;
            }
            System.out.println("興味のあること: " + actualInterestsText + " の表示を確認。");

            // ファイル名が正しいか検証（C:\fakepath\ は含まれない）
            String expectedFileName = uploadTestFile.getName();
            assertTrue("ファイル名が確認ページに表示されていません", actualText.contains("アップロードファイル: " + expectedFileName));
            System.out.println("ファイル '" + expectedFileName + "' の表示を確認。");

            // 好きなモビルスーツが正しいか検証
            assertTrue("好きなモビルスーツが確認ページに表示されていません", actualText.contains("好きなモビルスーツ: " + selectedMsLabel));
            System.out.println("好きなモビルスーツ '" + selectedMsLabel + "' の表示を確認。");

            // Thread.sleep(6000); // ★削除

        } catch (Throwable t) {
            takeScreenshot(driver, "testFormSubmissionAndConfirmation");
            t.printStackTrace();
            org.junit.Assert.fail("フォーム送信と確認ページテスト中にエラーが発生しました: " + t.getMessage());
        }
    }
}