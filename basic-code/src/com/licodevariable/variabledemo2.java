package com.licodevariable;

public class variabledemo2 {
    public static void main(String[] args) {
//        练习三
//                街头霸王
//        我方:叉子
//        攻击:220
//        防御:85
//        血量:10I2.5
//        技能加成:1.2
//        对方:长手
//        攻击:210
//        防御:80
//        血量:1223.3
//        技能加成:1.3
//        技能造成伤害的公式:攻击力“技能加成一对方防御力
//                普攻造成伤害的公式
//        攻击力一对方防御力
        //记录我方攻击力
        int myAttack = 220;
        //记录我方防御力
        int myDefense = 85;
        //记录我方技能加成
        double mySkill = 1.2;
        //记录我方血量
        double myHp = 1012.5;
        //记录对方攻击力
        int enemyAttack = 210;
        //记录对方防御力
        int enemyDefense = 80;
        //记录对方技能加成
        double enemySkill = 1.3;
        //记录对方血量
        double enemyHp = 1223.3;
        //我方第一次攻击力
        int myAttack1 = myAttack-enemyDefense;
        //我方第一次技能加成
        double mySkill1 = mySkill*enemySkill;
        //提防剩余血量
        enemyHp = enemyHp-myAttack1*mySkill1;
        System.out.println("剩余血量"+enemyHp);
    }
}
