package edu.tntech.jemma;

import edu.tntech.jemma.models.Member;

import java.util.List;

public class Tester {

    public static void main(String[] args) {

        String accountID = args[0];
        String publicKey = args[1];
        String privateKey = args[2];

        JEmma jemma = new JEmma(accountID, publicKey, privateKey);

        List<Member> members = jemma.members().fetchAll()
                .from(0).to(2)
                .showDeleted()
                .execute();

        jemma.members().fetchAll()
                .from(20).to(13).execute();

        Member idMember = jemma.members().fetchByID()
                .id(2).showDeleted()
                .execute();

        jemma.members().fetchByID().execute();

        Member emailMember = jemma.members().fetchByEmail()
                .email("lol@lol.lol").showDeleted()
                .execute();

        jemma.members().fetchByEmail().execute();

    }

}
