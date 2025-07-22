package com.example.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigUtilTest {

    /**
     * 有効なキーで値を取得できるかを検証するテスト
     */
    @Test
    public void testGetProperty_existingKey() {
        String edgePath = ConfigUtil.getProperty("edge.driver.path");
        assertNotNull("edge.driver.path が null です", edgePath);
        System.out.println("edge.driver.path: " + edgePath);
    }

    /**
     * 存在しないキーで null が返るかを検証するテスト
     */
    @Test
    public void testGetProperty_nonExistingKey() {
        String nonExisting = ConfigUtil.getProperty("non.existing.key");
        assertNull("存在しないキーに対して null が返りません", nonExisting);
    }

    /**
     * config.properties が存在しない場合の挙動を確認するテスト
     * 注意: このテストは自動化されていません。
     * config.properties を一時的にリネーム/削除して実行してください。
     */
//    @Test
//    public void testConfigFileMissing() {
//        System.out.println("【注意】このテストはConfigUtilのエラーハンドリング確認用です。");
//        System.out.println("       実際に src/main/resources/config.properties を削除またはリネームして確認してください。");
//        String edgePath = ConfigUtil.getProperty("edge.driver.path");
//        assertNull("ファイル未検出時にnullが返らない場合があります", edgePath);
//    }
}
