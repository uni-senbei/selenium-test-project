import com.example.test.SeleniumTestManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private static SeleniumTestManager manager;
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        manager = new SeleniumTestManager();
    }

    @BeforeEach
    void setup() {
        driver = manager.getDriver();
        manager.open("src/test/resources/input.html");
    }

    @AfterEach
    void tearDown() {
        manager.close();
    }

    /** ページタイトルの検証 */
    @Test
    void testPageTitle() {
        assertEquals("テストページ", driver.getTitle());
    }

    /** H1見出しの検証 */
    @Test
    void testHeadingH1() {
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("サンプルページ", h1.getText());
    }

    /** Pタグの検証 */
    @Test
    void testParagraph() {
        WebElement p = driver.findElement(By.tagName("p"));
        assertEquals("これはサンプルの段落です。", p.getText());
    }

    /** テキストボックス入力・検証 */
    @Test
    void testTextboxInput() {
        WebElement textbox = driver.findElement(By.id("textbox"));
        textbox.sendKeys("テスト入力");
        assertEquals("テスト入力", textbox.getAttribute("value"));
    }

    /** ラジオボタン選択・検証 */
    @Test
    void testRadioButton() {
        WebElement radio = driver.findElement(By.id("radio1"));
        radio.click();
        assertTrue(radio.isSelected());
    }
}
