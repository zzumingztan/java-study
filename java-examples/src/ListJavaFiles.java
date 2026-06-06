import java.io.File;

public class ListJavaFiles {
    public static void main(String[] args) {
        File dir = new File("."); // 当前目录
        listJavaFiles(dir, "");
    }

    private static void listJavaFiles(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(indent + "Directory: " + file.getName());
                    listJavaFiles(file, indent + "  ");
                } else if (file.getName().endsWith(".java")) {
                    System.out.println(indent + "File: " + file.getName());
                }
            }
        }
    }
}
```
</write_to_file>

<write_to_file>
<path>java-examples/src/ListSubDirectoriesAndFiles.java</path>
<content>
```java
import java.io.File;

public class ListSubDirectoriesAndFiles {
    public static void main(String[] args) {
        File dir = new File("."); // 当前目录
        listSubDirectoriesAndFiles(dir, "");
    }

    private static void listSubDirectoriesAndFiles(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(indent + "Directory: " + file.getName());
                    listSubDirectoriesAndFiles(file, indent + "  ");
                } else {
                    System.out.println(indent + "File: " + file.getName());
                }
            }
        }
    }
}
</write_to_file>

<task_progress>
- [x] 分析需求
- [x] 设置必要的文件
- [ ] 实现主要功能
- [ ] 处理边缘情况
- [ ] 测试实现
- [ ] 验证结果
</task_progress>