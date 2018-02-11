package edu.tntech.jemma.services;

import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.models.Optout;
import okhttp3.HttpUrl;

import java.util.List;
import java.util.Optional;

public interface MembersService {
    List<Member> fetchAll(HttpUrl url);
    Optional<Member> fetch(HttpUrl url);
    List<Optout> fetchOptouts(HttpUrl url);
    boolean optout(HttpUrl url);
}
