package com.example;

import com.example.test.SeleniumTestManager;
import com.example.test.TestConfig;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

/**
 * SeleniumTest - テストケース群
 *
 * 実装方針:
 *  - TestConfig を @BeforeClass で生成し manager に注入する
 *  - 各テスト前 ( @Before ) に manager.createDriver() で WebDriver を得て、
 *    manager.openBaseUrl(...) で対象ページを開く
 *  - 各テスト後 ( @After ) に manager.close() を例外安全に呼ぶ
 *  - クラス終了時 ( @AfterClass ) に manager.shutdown() で最終クリーンアップ
 */
public class SeleniumTest {

    private static SeleniumTestManager manager;
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        // TestConfig の生成と注入（TestConfig は別クラスで実装済み）
        TestConfig config = new TestConfig(); // デフォルト読み込み (環境変数 / config.properties / デフォルト)
        manager = new SeleniumTestManager();
        manager.configure(config);
    }

    @Before
    public void setup() {
        // 各テストごとに新しい WebDriver を生成（テストの独立性確保）
        this.driver = manager.createDriver();

        // テスト対象ページを開く（相対パス or クラスパス経由の論理名を manager に解決させる想定）
        // ここでは既存のファイルパス（テストリソース）を渡しています。
        manager.openBaseUrl("src/test/resources/input.html");
    }

    @After
    public void tearDown() {
        // close() の例外を吸収してテストの後片付けが中断されないようにする
        try {
            manager.close();
        } catch (Exception e) {
            // 最低限の警告ログを出して続行（将来的に Logger へ差し替え予定）
            System.err.println("[WARN] Failed to close WebDriver: " + e.getMessage());
        } finally {
            // ローカル参照クリア
            this.driver = null;
        }
    }

    @AfterClass
    public static void tearDownClass() {
        // マネージャ側の最終クリーンアップ（存在すれば呼ぶ）
        try {
            manager.shutdown();
        } catch (NoSuchMethodError | UnsupportedOperationException e) {
            // manager.shutdown() が未実装の場合でも安全に扱えるようにフォールバック
            // （将来的には manager.shutdown() を実装することを推奨）
        } catch (Exception e) {
            System.err.println("[WARN] manager.shutdown() failed: " + e.getMessage());
        } finally {
            manager = null;
        }
    }

    /** ページタイトルの検証 */
    @Test
    public void testPageTitle() {
        assertEquals("テストページ", driver.getTitle());
    }

    /** H1見出しの検証 */
    @Test
    public void testHeadingH1() {
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("サンプルページ", h1.getText());
    }

    /** Pタグの検証 */
    @Test
    public void testParagraph() {
        WebElement p = driver.findElement(By.tagName("p"));
        assertEquals("これはサンプルの段落です。", p.getText());
    }

    /** テキストボックス入力・検証 */
    @Test
    public void testTextboxInput() {
        WebElement textbox = driver.findElement(By.id("textbox"));
        textbox.clear(); // 前回実行の残滓を防ぐ（堅牢性向上）
        textbox.sendKeys("テスト入力");
        assertEquals("テスト入力", textbox.getAttribute("value"));
    }

    /** ラジオボタン選択・検証 */
    @Test
    public void testRadioButton() {
        WebElement radio = driver.findElement(By.id("radio1"));
        radio.click();
        assertTrue(radio.isSelected());
    }
}
