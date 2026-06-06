package work7;

import java.io.File;

// 任务1：列出指定目录中所有扩展名为 .java 的文件
public class ListJavaFiles {
    public static void main(String[] args) {
        // 目录路径
        File dir = new File("F:\\IDE\\study\\basic-code\\src\\work2");

        // 检查目录是否存在
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("目录不存在或不是有效目录！");
            return;
        }

        File[] files = dir.listFiles();

        // 空指针判断
        if (files == null) {
            System.out.println("无法访问该目录！");
            return;
        }

        System.out.println("=== 目录中的 .java 文件 ===");
        for (File file : files) {
            // 只筛选文件，并且后缀是 .java
            if (file.isFile() && file.getName().endsWith(".java")) {
                System.out.println(file.getName());
            }
        }
    }
}