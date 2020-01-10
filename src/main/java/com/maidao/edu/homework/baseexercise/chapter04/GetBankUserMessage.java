package com.maidao.edu.homework.baseexercise.chapter04;

import java.util.Collection;
import java.util.HashMap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 15:42
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:GetBankUserMessage
 * 类描述:编写一个程序，创建一个HashMap对象，用于存储银行储户的信息(其中储户的主要信息
 * 有储户的ID、姓名和余额)。另外，根据储户ID获取其余额信息。
 **/
public class GetBankUserMessage {
    public static void main(String[] args) {

        HashMap<String, BankUser> m = new HashMap<>();
        m.put("第一个用户", new BankUser("1", 0));
        m.put("第二个用户", new BankUser("2", 10000));
        m.put("第三个用户", new BankUser("3", 100000000));

        GetBankUserMessage g = new GetBankUserMessage();
        g.getBankUserMessage(m, "2");
    }

    public void getBankUserMessage(HashMap m, String s) {

        Collection<BankUser> doglist = m.values();
        for (BankUser d : doglist) {
            if (d.getId() == s)
                System.out.println(d.getBalance());
        }

    }
}
