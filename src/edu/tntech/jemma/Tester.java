package edu.tntech.jemma;

import edu.tntech.jemma.models.Member;

public class Tester {

    public static void main(String[] args) {

        String accountID = args[0];
        String publicKey = args[1];
        String privateKey = args[2];

        JEmma jemma = new JEmma(accountID, publicKey, privateKey);
        Member member = new Member("asdf@");
    }

}
