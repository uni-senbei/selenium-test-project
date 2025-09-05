package com.example;

import com.example.test.SeleniumTestManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class SeleniumTest {

    private static SeleniumTestManager manager;
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        manager = new SeleniumTestManager();
    }

    @Before
    public void setup() {
        driver = manager.getDriver();
        manager.open("src/test/resources/input.html");
    }

    @After
    public void tearDown() {
        manager.close();
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
