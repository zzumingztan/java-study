package work12;

import java.sql.*;
import java.util.Scanner;

public class StudentDB {
    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/School?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "";   // root 无密码

    public static void main(String[] args) {
        // 1. 加载 JDBC 驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 驱动未找到，请检查 classpath！");
            e.printStackTrace();
            return;
        }

        // 2. 连接数据库并展示菜单
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("===== 学生信息管理系统 =====");
            System.out.println("成功连接到数据库 School");

            int choice;
            do {
                System.out.println("\n--- 操作菜单 ---");
                System.out.println("1. 新增学生信息 (增)");
                System.out.println("2. 查询学生信息 (查)");
                System.out.println("3. 修改学生信息 (改)");
                System.out.println("4. 删除学生信息 (删)");
                System.out.println("5. 显示所有学生信息");
                System.out.println("0. 退出");
                System.out.print("\n请选择操作: ");

                choice = sc.nextInt();
                sc.nextLine();  // 消耗换行符

                switch (choice) {
                    case 1: insertStudent(conn, sc); break;
                    case 2: queryStudent(conn, sc);  break;
                    case 3: updateStudent(conn, sc);  break;
                    case 4: deleteStudent(conn, sc);  break;
                    case 5: showAllStudents(conn);    break;
                    case 0: System.out.println("程序退出，再见！"); break;
                    default: System.out.println("无效选项，请重新选择。");
                }
            } while (choice != 0);

        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
    }

    // ============ 增：新增学生信息 ============
    private static void insertStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n===== 新增学生信息 =====");

        System.out.print("请输入学号: ");
        String sno = sc.nextLine();
        System.out.print("请输入姓名: ");
        String name = sc.nextLine();
        System.out.print("请输入性别: ");
        String gender = sc.nextLine();
        System.out.print("请输入专业: ");
        String major = sc.nextLine();
        System.out.print("请输入入学年份: ");
        int year = sc.nextInt();
        sc.nextLine();  // 消耗换行符

        String sql = "INSERT INTO studb (学号, 姓名, 性别, 专业, 入学年份) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sno);
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, major);
            pst.setInt(5, year);

            int rows = pst.executeUpdate();
            System.out.println("✅ 成功插入 " + rows + " 条学生记录！");
        }
    }

    // ============ 查：按学号查询学生 ============
    private static void queryStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n===== 查询学生信息 =====");
        System.out.print("请输入要查询的学号: ");
        String sno = sc.nextLine();

        String sql = "SELECT * FROM studb WHERE 学号 = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sno);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n--- 查询结果 ---");
                    printStudent(rs);
                } else {
                    System.out.println("❌ 未找到学号为 " + sno + " 的学生。");
                }
            }
        }
    }

    // ============ 改：按学号修改学生信息 ============
    private static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n===== 修改学生信息 =====");
        System.out.print("请输入要修改的学号: ");
        String sno = sc.nextLine();

        // 先查询是否存在
        String checkSql = "SELECT * FROM studb WHERE 学号 = ?";
        try (PreparedStatement pst = conn.prepareStatement(checkSql)) {
            pst.setString(1, sno);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("❌ 未找到学号为 " + sno + " 的学生。");
                    return;
                }
                System.out.println("当前信息: ");
                printStudent(rs);
            }
        }

        System.out.print("请输入新姓名 (直接回车保持不变): ");
        String name = sc.nextLine();
        System.out.print("请输入新性别 (直接回车保持不变): ");
        String gender = sc.nextLine();
        System.out.print("请输入新专业 (直接回车保持不变): ");
        String major = sc.nextLine();
        System.out.print("请输入新入学年份 (输入 -1 保持不变): ");
        int year = sc.nextInt();
        sc.nextLine();

        // 动态构建 SQL
        StringBuilder sql = new StringBuilder("UPDATE studb SET ");
        boolean hasUpdate = false;

        if (!name.isEmpty()) {
            sql.append("姓名 = ?");
            hasUpdate = true;
        }
        if (!gender.isEmpty()) {
            if (hasUpdate) sql.append(", ");
            sql.append("性别 = ?");
            hasUpdate = true;
        }
        if (!major.isEmpty()) {
            if (hasUpdate) sql.append(", ");
            sql.append("专业 = ?");
            hasUpdate = true;
        }
        if (year != -1) {
            if (hasUpdate) sql.append(", ");
            sql.append("入学年份 = ?");
            hasUpdate = true;
        }
        sql.append(" WHERE 学号 = ?");

        if (!hasUpdate) {
            System.out.println("未修改任何字段。");
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (!name.isEmpty()) pst.setString(paramIndex++, name);
            if (!gender.isEmpty()) pst.setString(paramIndex++, gender);
            if (!major.isEmpty()) pst.setString(paramIndex++, major);
            if (year != -1) pst.setInt(paramIndex++, year);
            pst.setString(paramIndex, sno);

            int rows = pst.executeUpdate();
            System.out.println("✅ 成功修改 " + rows + " 条学生记录！");
        }
    }

    // ============ 删：按学号删除学生 ============
    private static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n===== 删除学生信息 =====");
        System.out.print("请输入要删除的学号: ");
        String sno = sc.nextLine();

        // 先确认
        String checkSql = "SELECT * FROM studb WHERE 学号 = ?";
        try (PreparedStatement pst = conn.prepareStatement(checkSql)) {
            pst.setString(1, sno);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("❌ 未找到学号为 " + sno + " 的学生。");
                    return;
                }
                System.out.println("将要删除以下学生信息:");
                printStudent(rs);
            }
        }

        System.out.print("确认删除？(y/n): ");
        String confirm = sc.nextLine();
        if (!"y".equalsIgnoreCase(confirm)) {
            System.out.println("已取消删除操作。");
            return;
        }

        String sql = "DELETE FROM studb WHERE 学号 = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sno);
            int rows = pst.executeUpdate();
            System.out.println("✅ 成功删除 " + rows + " 条学生记录！");
        }
    }

    // ============ 显示所有学生信息 ============
    private static void showAllStudents(Connection conn) throws SQLException {
        System.out.println("\n===== 所有学生信息 =====");
        String sql = "SELECT * FROM studb";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("--------------------------------------------------------------");
            System.out.printf("| %-12s | %-10s | %-4s | %-14s | %-6s |\n",
                    "学号", "姓名", "性别", "专业", "入学年份");
            System.out.println("--------------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                System.out.printf("| %-12s | %-10s | %-4s | %-14s | %-6d |\n",
                        rs.getString("学号"),
                        rs.getString("姓名"),
                        rs.getString("性别"),
                        rs.getString("专业"),
                        rs.getInt("入学年份"));
                count++;
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println("共 " + count + " 条记录");
        }
    }

    // ============ 打印单条学生记录 ============
    private static void printStudent(ResultSet rs) throws SQLException {
        System.out.println("学号: " + rs.getString("学号"));
        System.out.println("姓名: " + rs.getString("姓名"));
        System.out.println("性别: " + rs.getString("性别"));
        System.out.println("专业: " + rs.getString("专业"));
        System.out.println("入学年份: " + rs.getInt("入学年份"));
    }
}
