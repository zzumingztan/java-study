package work1;

import java.util.Scanner;

public class tongJiciShu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入英文文档内容：");
        String input = sc.nextLine();

        // 1. 分词：转小写并按非字母字符切分
        String[] allWords = input.toLowerCase().split("\\W+");

        // 2. 准备数组（模拟动态收集过程）
        String[] uniqueWords = new String[allWords.length];
        int[] counts = new int[allWords.length];
        int uniqueCount = 0;

        // 3. 遍历统计
        for (String word : allWords) {
            if (word.isEmpty()) continue;

            // 调用封装好的查找函数（复用第三题匹配思路）
            int foundIndex = findWordInArray(uniqueWords, uniqueCount, word);

            if (foundIndex != -1) {
                counts[foundIndex]++; // 找到了，次数自增
            } else {
                // 没找到，存入新位置
                uniqueWords[uniqueCount] = word;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // 4. 【关键新增】对统计结果进行排序（从高到低）
        sortArrays(uniqueWords, counts, uniqueCount);

        // 5. 输出结果
        printResults(uniqueWords, counts, uniqueCount);

        sc.close();
    }
    public static int findWordInArray(String[] array, int size, String target) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    public static void sortArrays(String[] words, int[] counts, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                // 如果后一个比前一个大，就交换（实现从高到低）
                if (counts[j] < counts[j + 1]) {
                    // 交换次数
                    int tempCount = counts[j];
                    counts[j] = counts[j + 1];
                    counts[j + 1] = tempCount;

                    // 同步交换单词，保证对应关系不乱
                    String tempWord = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = tempWord;
                }
            }
        }
    }


    public static void printResults(String[] words, int[] counts, int size) {
        System.out.println("\n--- 最终统计结果（按频率由高到低） ---");
        for (int i = 0; i < size; i++) {
            System.out.println(words[i] + " : " + counts[i] + " 次");
        }
    }
}