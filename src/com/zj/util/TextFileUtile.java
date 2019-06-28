package com.zj.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileUtile {
    public static List<String> readLines(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(filePath);
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                result.add(line);
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
