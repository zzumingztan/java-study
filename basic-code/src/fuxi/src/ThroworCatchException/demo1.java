package jichengtest.ThroworCatchException;

import java.io.File;
import java.io.FileNotFoundException;

public class demo1 {
    public void openThisFile(String fileName) throws java.io.FileNotFoundException {
        System.out.println("正在尝试打开文件: " + fileName);
        
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("找不到文件: " + fileName);
        }
        
        System.out.println("文件打开成功: " + fileName);
    }
    
    public void getCustomerInfo() throws java.io.FileNotFoundException {
        System.out.println("=== 开始获取客户信息 ===");
        this.openThisFile("custome2r.txt");
        System.out.println("=== 客户信息获取成功 ===");
    }
    
    public static void main(String[] args) {
        System.out.println("===== 异常处理演示 =====\n");
        
        demo1 d = new demo1();

        try {
            d.getCustomerInfo();
        } catch (FileNotFoundException e) {
            System.out.println("\n❌ 捕获到异常！");
            System.out.println("异常类型: " + e.getClass().getSimpleName());
            System.out.println("错误信息: " + e.getMessage());
            System.out.println("\n堆栈跟踪:");
            e.printStackTrace();
            
            // 如果需要重新抛出异常，可以取消下面这行的注释
            // throw new RuntimeException(e);
        }
        
        System.out.println("\n===== 程序执行完毕 =====");
    }
}
