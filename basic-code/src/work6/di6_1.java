package work6;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class di6_1 {
    public static void main(String[] args) {
        File file = new File("C:\\Hello.txt");


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;
            System.out.println("从文件中读取到的内容：");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("读取失败：" + e.getMessage());
        }
    }
}