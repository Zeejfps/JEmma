package edu.tntech.jemma;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {

        String accountID = args[0];
        String publicKey = args[1];
        String privateKey = args[2];

        JEmma jemma = new JEmma(accountID, publicKey, privateKey);

        /*jemma.members().listAll().from(1).includeDeleted()
                .execute().forEach(member -> {
            System.out.println(member.getMemberId());
        });*/

        jemma.members().list(10).execute().forEach(member -> {
            System.out.println(member.getEmail());
        });

        jemma.members().get(1889979423).includeDeleted()
                .execute().ifPresent(member -> {
            System.out.println(member.getStatus());
        });

        jemma.members().get("zeejfps@gmail.com").includeDeleted()
                .execute().ifPresent(member -> {
            System.out.println(member.getStatus());
        });

        List<Members.Factory> members = new ArrayList<>();
        members.add(jemma.members().create("zeejfps@gmail.com")
                .addField("first_name", "Zee")
                .addField("last_name", "Jenkins")
                .addField("Sex", "Male")
        );
        members.add(jemma.members().create("other@lol.wat"));

        int importID = jemma.members().save(members)
                .setSourceFilename("somecsv.csv")
                .automateFieldChanges().execute();
        System.out.println(importID);

    }

}
