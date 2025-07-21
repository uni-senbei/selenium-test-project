package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                System.out.println("config.propertiesがresourcesフォルダに見つかりません。");
            } else {
                properties.load(is);
                System.out.println("config.propertiesの読み込みに成功しました。");
            }
        } catch (IOException e) {
            System.out.println("config.propertiesの読み込みに失敗しました: " + e.getMessage());
        }
    }

    /**
     * 指定したキーに対応する値を返すユーティリティメソッド
     * @param key 設定ファイル内のキー
     * @return キーに対応する値。見つからない場合はnull。
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
