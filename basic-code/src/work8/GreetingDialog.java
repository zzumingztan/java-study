package work8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingDialog extends JFrame {
    private JTextField nameField;
    private JButton okBtn;

    public GreetingDialog() {
        // 1. 窗口基本设置
        setTitle("自定义的JFrame窗体");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中

        // 2. 布局与控件
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));

        add(new JLabel("请输入您的姓名："));
        nameField = new JTextField(15);
        add(nameField);

        okBtn = new JButton("确定");
        add(okBtn);

        // 3. 按钮事件：弹出对话框
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入姓名！");
                    return;
                }
                // 自定义JDialog
                JDialog dialog = new JDialog(GreetingDialog.this, "JDialog对话框", true);
                dialog.setSize(250, 150);
                dialog.setLocationRelativeTo(null);
                dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
                dialog.add(new JLabel(name + "，你好！"));
                dialog.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GreetingDialog().setVisible(true);
        });
    }
}
