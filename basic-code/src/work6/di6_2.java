package work6;

import java.io.*;

public class di6_2 {
    public static void main(String[] args) {
        // 1. 检查命令行参数是否足够
        if (args.length < 2) {
            System.out.println("请提供源文件和目标文件路径！");
            System.out.println("示例: java work6.di6_2 C:\\Hello.txt C:\\Hello_copy.txt");
            return;
        }

        String sourcePath = args[0]; // 第一个参数：源文件
        String destPath = args[1];   // 第二个参数：目标文件

        // 2. 使用字节流进行读写复制
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destPath)) {

            byte[] buffer = new byte[1024]; // 缓冲区，提高效率
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制任务已完成！");
            System.out.println("源文件: " + sourcePath);
            System.out.println("目标文件: " + destPath);

        } catch (FileNotFoundException e) {
            System.err.println("找不到文件: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("读写出错: " + e.getMessage());
        }
    }
}
