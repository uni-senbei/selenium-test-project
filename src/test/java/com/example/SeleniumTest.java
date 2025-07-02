package com.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.MalformedURLException; // ★この行を追加
import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTest {

    // Webページ (input.html) へのパスを定数として定義
    // src/test/resources/input.html に配置することを想定
    private static final String FILE_PATH;

    // ファイルアップロードテストで使用するファイルのパスを定数として定義
    // src/test/resources/gihren.jpg に配置することを想定
    private static final String UPLOAD_FILE_PATH;

    // static初期化ブロックで定数を設定（プログラム起動時に一度だけ実行）
    static {
        String inputHtmlRelativePath = "src/test/resources/input.html"; // input.htmlの相対パス
        String uploadFileRelativePath = "src/test/resources/gihren.jpg"; // gihren.jpgの相対パス（★ご自身のファイル名に合わせる）

        String resolvedFilePath = null;
        String resolvedUploadPath = null;

        try {
            // input.html のパスを file:// 形式のURLに変換
            resolvedFilePath = new File(inputHtmlRelativePath).toURI().toURL().toString();
            // ファイルアップロード用に絶対パスを取得
            resolvedUploadPath = new File(uploadFileRelativePath).getAbsolutePath();
        } catch (MalformedURLException e) {
            // URL変換に失敗した場合の処理
            System.err.println("URL変換エラーが発生しました。ファイルパスを確認してください: " + e.getMessage());
            // テストが実行できないため、実行時例外をスローして処理を中断
            throw new RuntimeException("テストファイルのURL取得に失敗しました。パス設定を確認してください。", e);
        }

        FILE_PATH = resolvedFilePath;
        UPLOAD_FILE_PATH = resolvedUploadPath;
    }

    @Test
    public void testFormInputAndRadioButtons() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(FILE_PATH); // FILE_PATH定数を使用

            // ページのタイトル、H1、Pタグの検証
            String pageTitle = driver.getTitle();
            assertEquals("ページのタイトルが一致しません", "アンケートフォーム入力ページ".trim(), pageTitle.trim());

            WebElement h1Element = driver.findElement(By.tagName("h1"));
            String h1Text = h1Element.getText();
            assertEquals("H1見出しのテキストが一致しません", "アンケートを取ります".trim(), h1Text.trim());

            WebElement pElement = driver.findElement(By.xpath("//p[text()='JSはコンソールで見れるよ']"));
            String pText = pElement.getText();
            assertEquals("Pタグのテキストが一致しません", "JSはコンソールで見れるよ".trim(), pText.trim());

            // first-name テキストボックスに文字入力と検証
            WebElement firstNameInput = driver.findElement(By.id("first-name"));
            String inputName = "ガンダム";
            firstNameInput.sendKeys(inputName);
            String actualFirstName = firstNameInput.getAttribute("value");
            assertEquals("first-name の入力値が一致しません", inputName, actualFirstName);
            System.out.println("first-name に '" + actualFirstName + "' を入力しました。");

            System.out.println("--- ラジオボタン選択テスト開始 ---");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement radioA = wait.until(ExpectedConditions.elementToBeClickable(By.id("radio-a")));
            radioA.click();
            System.out.println("「はい」ラジオボタンをクリックしました。");

            assertTrue("「はい」ラジオボタンが選択されていません", radioA.isSelected());
            System.out.println("「はい」ラジオボタンが選択されていることを確認しました。");

            Thread.sleep(1000); // 各テストメソッド内のsleepは短くしました

        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit(); // driverを閉じる
            }
        }
    }

    @Test
    public void testCheckboxes() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(FILE_PATH); // FILE_PATH定数を使用

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("--- チェックボックス選択テスト開始 ---");

            WebElement checkboxB = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-b")));
            assertTrue("check-b がデフォルトで選択されていません", checkboxB.isSelected());
            System.out.println("「戦略と勢い」チェックボックスがデフォルトで選択されていることを確認しました。");

            WebElement checkboxA = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-a")));
            checkboxA.click();
            assertTrue("check-a が選択されていません", checkboxA.isSelected());
            System.out.println("「政治とタイミング」チェックボックスを選択しました。");

            checkboxB.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.id("check-b"))));
            assertFalse("check-b のチェックが外れていません", checkboxB.isSelected());
            System.out.println("「戦略と勢い」チェックボックスのチェックを外しました。");

            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }


    @Test
    public void testFileUpload() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(FILE_PATH); // FILE_PATH定数を使用

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("--- ファイルアップロードテスト開始 ---");

            // UPLOAD_FILE_PATH 定数を使用
            File file = new File(UPLOAD_FILE_PATH);

            if (!file.exists()) {
                System.err.println("エラー: 指定されたファイルが見つかりません: " + UPLOAD_FILE_PATH);
                org.junit.Assert.fail("アップロードテスト用のファイルが見つかりません。パスを確認してください。");
            }

            // 2. type="file" の要素を見つける
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("image")));

            // 3. ファイルの絶対パスを sendKeys() で入力する
            fileInput.sendKeys(UPLOAD_FILE_PATH); // UPLOAD_FILE_PATH定数を使用
            System.out.println("ファイル '" + file.getName() + "' を選択しました。");

            // 4. (オプション) ファイルが選択されたことを検証する
            String uploadedFileName = fileInput.getAttribute("value");
            String expectedFileName = file.getName();

            assertTrue("ファイルが正しく選択されていません。選択されたファイル名: " + uploadedFileName,
                    uploadedFileName.contains(expectedFileName));
            System.out.println("ファイルが正しく選択されていることを確認しました。");

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail("ファイルアップロードテスト中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testDropdown() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(FILE_PATH); // FILE_PATH定数を使用

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("--- プルダウン選択テスト開始 ---");

            WebElement msDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("MS-pull-down")));
            Select msDropdown = new Select(msDropdownElement);

            msDropdown.selectByValue("アッガイ");
            System.out.println("「アッガイ」をプルダウンから選択しました。");

            assertEquals("選択されたオプションのテキストが一致しません", "アッガイ", msDropdown.getFirstSelectedOption().getText().trim());
            System.out.println("「アッガイ」が選択されていることを確認しました。");

            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail("テスト中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testFormSubmissionAndConfirmation() {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 1. input.html を開く
            driver.get(FILE_PATH);
            System.out.println("--- フォーム送信と確認ページテスト開始 ---");

            // 2. フォームにデータを入力
            WebElement firstNameInput = driver.findElement(By.id("first-name"));
            String testName = "テスト太郎";
            firstNameInput.sendKeys(testName);

            WebElement genderMaleRadio = driver.findElement(By.id("radio-a")); // 男性を選択
            genderMaleRadio.click();

            WebElement interestsPoliticsCheckbox = driver.findElement(By.id("check-a")); // 政治とタイミングを選択
            interestsPoliticsCheckbox.click();
            // デフォルトで選択されている「戦略と勢い」はそのまま残す

            // ファイルアップロード (ここではファイル名だけ確認するため、実際のファイルパスを使う)
            WebElement fileInput = driver.findElement(By.id("image-upload"));
            File uploadTestFile = new File(UPLOAD_FILE_PATH);
            if (!uploadTestFile.exists()) {
                org.junit.Assert.fail("アップロードテスト用のファイルが見つかりません: " + UPLOAD_FILE_PATH);
            }
            fileInput.sendKeys(UPLOAD_FILE_PATH);
            // WebDriverはC:\fakepath\を付加するので、ここでは実際のファイル名が送信されることを期待する

            WebElement msDropdownElement = driver.findElement(By.id("ms-pull-down"));
            Select msDropdown = new Select(msDropdownElement);
            String selectedMs = "シャアザク";
            msDropdown.selectByValue(selectedMs);

            // 3. 送信ボタンをクリック
            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();
            System.out.println("フォームを送信しました。");

            // 4. confirm.html に遷移したことを確認
            wait.until(ExpectedConditions.urlContains("confirm.html"));
            System.out.println("確認ページに遷移しました: " + driver.getCurrentUrl());
            assertEquals("ページのタイトルが一致しません", "入力内容確認ページ", driver.getTitle().trim());

            // 5. confirm.html 上で入力内容が正しく表示されているか検証
            WebElement confirmationDetails = driver.findElement(By.id("confirmation-details"));

            // 名前が正しいか検証
            assertTrue("名前が確認ページに表示されていません", confirmationDetails.getText().contains("名前: " + testName));
            System.out.println("名前 '" + testName + "' の表示を確認。");

            // 性別が正しいか検証
            assertTrue("性別が確認ページに表示されていません", confirmationDetails.getText().contains("性別: 男性"));
            System.out.println("性別 '男性' の表示を確認。");

            // 興味のあることが正しいか検証 (両方選択されていることを確認)
            // 興味のあることが正しいか検証 (両方選択されていることを確認)
            // 修正前: (value属性の文字列で検証していた部分)
            // assertTrue("興味のあること「politics」が表示されていません", confirmationDetails.getText().contains("politics"));
            // assertTrue("興味のあること「strategy」が表示されていません", confirmationDetails.getText().contains("strategy"));

            // 興味のあることが正しいか検証 (両方選択されていることを確認)
            assertTrue("興味のあること「政治とタイミング」が表示されていません", confirmationDetails.getText().contains("政治とタイミング"));
            assertTrue("興味のあること「戦略と勢い」が表示されていません", confirmationDetails.getText().contains("戦略と勢い"));

            // --- 修正: 実際に表示されている「興味のあること」のテキストを取得して表示する ---
            // confirmationDetails のテキスト全体から「興味のあること: 」の行を抽出する
            String fullConfirmationText = confirmationDetails.getText();
            String interestsLine = "";
            String[] lines = fullConfirmationText.split("\n"); // 改行で分割
            for (String line : lines) {
                if (line.contains("興味のあること:")) {
                    interestsLine = line.trim();
                    break;
                }
            }
            System.out.println(interestsLine + " の表示を確認。"); // 例: 興味のあること: 政治とタイミング, 戦略と勢い の表示を確認。

            // ファイル名が正しいか検証（C:\fakepath\ は含まれない）
            String expectedFileName = uploadTestFile.getName(); // 実際のファイル名
            assertTrue("ファイル名が確認ページに表示されていません", confirmationDetails.getText().contains("アップロードファイル: " + expectedFileName));
            System.out.println("ファイル '" + expectedFileName + "' の表示を確認。");

            // 好きなモビルスーツが正しいか検証
            assertTrue("好きなモビルスーツが確認ページに表示されていません", confirmationDetails.getText().contains("好きなモビルスーツ: " + selectedMs));
            System.out.println("好きなモビルスーツ '" + selectedMs + "' の表示を確認。");

            Thread.sleep(6000); // 確認用に少し待機

        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail("フォーム送信と確認ページテスト中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}