package edu.tntech.jemma;

import edu.tntech.jemma.models.Member;

public class Tester {

    public static void main(String[] args) {

        String accountID = args[0];
        String publicKey = args[1];
        String privateKey = args[2];

        JEmma jemma = new JEmma(accountID, publicKey, privateKey);

        jemma.members().fetchAll().execute().forEach(member -> {
            System.out.println(member.getEmail());
        });

        jemma.members().fetchByEmail()
                .email("zeejfps@gmail.com")
                .execute()
                .ifPresent(member -> {
            System.out.println(member.getMemberId());
            if (jemma.members().optout()
                    .member(member)
                    .execute()) {
                System.out.println(member.getMemberId());
                System.out.println(member.getEmail() + " has been opted out");
            }

            jemma.members().fetchOptouts()
                .member(member)
                .execute().forEach(optout -> {
                    System.out.println(optout.getTimestamp());
                });
        });

        jemma.members().fetchByID()
                .id(1889629215).showDeleted()
                .execute().ifPresent(member -> {
            System.out.println(member.getStatus());
        });
    }

}
