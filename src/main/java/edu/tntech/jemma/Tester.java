package edu.tntech.jemma;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {

        String accountID = args[0];
        String publicKey = args[1];
        String privateKey = args[2];

        JEmma jemma = new JEmma(accountID, publicKey, privateKey);

        int memberCount = jemma.members().count().execute();
        System.out.println("# of members: " + memberCount);

        //System.out.println(jemma.members().listAll().from(1000).execute().size());

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
                .addField("sex", "Male")
        );
        members.add(jemma.members().create("other@lol.wat"));

        long importID = jemma.members().save(members)
                .addToGroup(0).addToGroup(2)
                .setSourceFilename("Lol3.csv").execute();
        System.out.println(importID);

        long memberID = jemma.members().save("myemail@rofl.yey")
                .addField("first_name", "Zee")
                .addField("test", "20")
                .addToGroup(0).fireFieldTriggers().execute();
        System.out.println(memberID);

    }

}
