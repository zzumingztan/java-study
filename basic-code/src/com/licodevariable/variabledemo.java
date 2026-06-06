package com.licodevariable;

public class variabledemo {
    public static void main(String[] args) {
//        微信余额:0元
//        支付宝余额:10元
//        银行卡余额:20元问题一:请问现在一共有多少钱?
//                问题二:微信收了10元红包，又发了2元红包，余额多少?
        int weixin = 0;
        int zhifubao = 10;
        int bank = 20;
        System.out.println(weixin+zhifubao+bank);
        System.out.println(weixin+=10-2);
    }
}
