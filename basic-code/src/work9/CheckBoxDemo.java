package work9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxDemo extends JFrame implements ActionListener {
    // 组件声明
    private JCheckBox cbMusic, cbSport, cbInternet;
    private JTextField tfResult;
    private JLabel label;

    public CheckBoxDemo() {
        // 1. 窗口基础设置
        setTitle("关于复选框");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中

        // 2. 创建面板和组件
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        label = new JLabel("爱好选择：");
        cbMusic = new JCheckBox("音乐");
        cbSport = new JCheckBox("运动");
        cbInternet = new JCheckBox("上网");
        tfResult = new JTextField(20);
        tfResult.setEditable(false); // 只读文本框

        //3.添加动作
        cbMusic.addActionListener(this);
        cbSport.addActionListener(this);
        cbInternet.addActionListener(this);

        // 4. 组件添加到面板
        topPanel.add(label);
        topPanel.add(cbMusic);
        topPanel.add(cbSport);
        topPanel.add(cbInternet);

        bottomPanel.add(new JLabel("您选择了："));
        bottomPanel.add(tfResult);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        // 5. 添加面板到窗口
        add(panel);
    }

    // 事件处理方法：复选框被点击时触发
    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();

        if (cbMusic.isSelected()) {
            sb.append("音乐 ");
        }
        if (cbSport.isSelected()) {
            sb.append("运动 ");
        }
        if (cbInternet.isSelected()) {
            sb.append("上网 ");
        }

        // 移除末尾多余空格，更新文本框内容
        tfResult.setText(sb.toString().trim());
    }

    public static void main(String[] args) {
        // 保证Swing程序在事件调度线程中运行
        SwingUtilities.invokeLater(() -> {
            CheckBoxDemo demo = new CheckBoxDemo();
            demo.setVisible(true);
        });
    }
}
