package work7;

import java.io.File;

// 任务2：列出指定目录中的所有文件与子目录（分开显示）
public class ListDirAndFiles {
    public static void main(String[] args) {
        // 目录路径
        File dir = new File("F:\\IDE\\study\\basic-code\\src");

        // 检查目录是否存在
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("目录不存在或不是有效目录！");
            return;
        }

        File[] items = dir.listFiles();

        if (items == null) {
            System.out.println("无法访问该目录！");
            return;
        }

        System.out.println("=== 子目录 ===");
        for (File item : items) {
            if (item.isDirectory()) {
                System.out.println("[目录] " + item.getName());
            }
        }

        System.out.println("\n=== 文件 ===");
        for (File item : items) {
            if (item.isFile()) {
                System.out.println("[文件] " + item.getName());
            }
        }
    }
}