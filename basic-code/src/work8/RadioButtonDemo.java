import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonDemo extends JFrame {
    private JRadioButton rbManager;
    private JRadioButton rbEngineer;
    private JRadioButton rbTeacher;
    private JButton btnConfirm;
    private JTextField txtResult;

    public RadioButtonDemo() {
        // 窗口标题和大小，和截图风格一致
        setTitle("关于单选按钮");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // 用绝对定位，和截图完全对齐

        // 1. 提示文字：将来要当：
        JLabel lblTip = new JLabel("将来要当：");
        lblTip.setFont(new Font("Dialog", Font.PLAIN, 28));
        lblTip.setBounds(40, 60, 200, 40);
        add(lblTip);

        // 2. 三个单选按钮（和截图一样水平排列）
        rbManager = new JRadioButton("经理");
        rbEngineer = new JRadioButton("工程师");
        rbTeacher = new JRadioButton("教师");

        // 按钮组（保证只能选一个）
        ButtonGroup group = new ButtonGroup();
        group.add(rbManager);
        group.add(rbEngineer);
        group.add(rbTeacher);

        // 设置字体和位置，和截图一致
        Font rbFont = new Font("Dialog", Font.PLAIN, 28);
        rbManager.setFont(rbFont);
        rbEngineer.setFont(rbFont);
        rbTeacher.setFont(rbFont);

        rbManager.setBounds(260, 60, 150, 40);
        rbEngineer.setBounds(440, 60, 180, 40);
        rbTeacher.setBounds(640, 60, 150, 40);

        add(rbManager);
        add(rbEngineer);
        add(rbTeacher);

        // 3. 确定按钮（新增！）
        btnConfirm = new JButton("确定");
        btnConfirm.setFont(new Font("Dialog", Font.PLAIN, 24));
        btnConfirm.setBounds(300, 130, 120, 40);
        add(btnConfirm);

        // 4. 结果文本框（和截图一样的样式）
        txtResult = new JTextField();
        txtResult.setFont(new Font("Dialog", Font.PLAIN, 28));
        txtResult.setBounds(60, 190, 600, 60);
        txtResult.setEditable(false); // 禁止用户编辑
        add(txtResult);

        // 按钮点击事件：点击确定才显示结果
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = "";
                if (rbManager.isSelected()) {
                    selected = "经理";
                } else if (rbEngineer.isSelected()) {
                    selected = "工程师";
                } else if (rbTeacher.isSelected()) {
                    selected = "教师";
                } else {
                    selected = "未选择";
                }
                txtResult.setText("您选择了将来要当：" + selected);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioButtonDemo().setVisible(true);
        });
    }
}