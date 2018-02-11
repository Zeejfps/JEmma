package edu.tntech.jemma;

import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.models.Optout;
import edu.tntech.jemma.services.MembersService;
import okhttp3.HttpUrl;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MembersTest {

    private Members members;

    public MembersTest() {
        String accountID = "";
        String publicKey = "";
        String privateKey = "";
        members = new Members("", new MockMembersService());
    }

    @Test
    public void testFetchAll() throws Exception {
        members.fetchAll()
                .from(-1).execute();
    }


    public class MockMembersService implements MembersService {

        @Override
        public List<Member> fetchAll(HttpUrl url) {
            return null;
        }

        @Override
        public Optional<Member> fetch(HttpUrl url) {
            return Optional.empty();
        }

        @Override
        public List<Optout> fetchOptouts(HttpUrl url) {
            return Collections.emptyList();
        }

        @Override
        public boolean optout(HttpUrl url) {
            return false;
        }
    }

}
