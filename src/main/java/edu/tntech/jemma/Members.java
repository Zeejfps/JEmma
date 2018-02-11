package edu.tntech.jemma;

import com.sun.istack.internal.NotNull;
import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.models.Optout;
import edu.tntech.jemma.services.MembersService;
import okhttp3.HttpUrl;

import java.util.List;
import java.util.Optional;

public class Members {

    private static final String SCHEME = "https";
    private static final String API_URL = "api.e2ma.net";
    private static final String ENDPOINT = "members";

    private final String accountID;
    private final MembersService service;

    Members(String accountID, MembersService service) {
        this.accountID = accountID;
        this.service = service;
    }

    private abstract class Request {

        HttpUrl.Builder urlBuilder;

        private Request() {
            urlBuilder = new HttpUrl.Builder();
            urlBuilder.scheme(SCHEME);
            urlBuilder.host(API_URL);
            urlBuilder.addPathSegment(accountID);
            urlBuilder.addPathSegment(ENDPOINT);
        }

    }

    public class FetchAllRequest extends Request {

        private int from, to;

        public FetchAllRequest from(int from) {
            if (from < 0)
                throw new IllegalArgumentException("'from' can not be negative");

            this.from = from;
            if (this.to < from) {
                to(from);
            }
            return this;
        }

        public FetchAllRequest to(int to) {
            if (to < 0)
                throw new IllegalArgumentException("'to' can not be negative");

            this.to = to;
            urlBuilder.setQueryParameter("end", Integer.toString(to));
            return this;
        }

        public FetchAllRequest showDeleted() {
            urlBuilder.setQueryParameter("deleted", "true");
            return this;
        }

        public List<Member> execute() {
            if (to < from)
                throw new IllegalStateException("'to' can not come before 'from'");
            return service.fetchAll(urlBuilder.build());
        }

    }

    public class FetchByIdRequest extends Request {

        private long id = -1;

        public FetchByIdRequest id(long id) {
            if (id < 0)
                throw new IllegalArgumentException("'id' can not be negative");
            this.id = id;
            return this;
        }

        public FetchByIdRequest showDeleted() {
            urlBuilder.setQueryParameter("deleted", "true");
            return this;
        }

        public Optional<Member> execute() {
            urlBuilder.addPathSegment(Long.toString(id));
            return service.fetch(urlBuilder.build());
        }

    }

    public class FetchByEmailRequest extends Request {

        private String email = "";

        public FetchByEmailRequest() {
            urlBuilder.addPathSegment("email");
        }

        public FetchByEmailRequest email(@NotNull String email) {
            if (email == null)
                throw new IllegalArgumentException("'email' can not be null");
            this.email = email;
            return this;
        }

        public FetchByEmailRequest showDeleted() {
            urlBuilder.setQueryParameter("deleted", "true");
            return this;
        }

        public Optional<Member> execute() {
            if (email.isEmpty())
                throw new IllegalStateException("'email' can not be empty");
            urlBuilder.addPathSegment(email);
            return service.fetch(urlBuilder.build());
        }
    }

    public class FetchOptoutsRequest extends Request {

        private Member member;

        public FetchOptoutsRequest member(@NotNull Member member) {
            if (member == null)
                throw new IllegalArgumentException("'member' can not be null");
            this.member = member;
            return this;
        }

        public List<Optout> execute() {
            if (member == null)
                throw new IllegalStateException("'member' can not be null");
            urlBuilder.addPathSegment(Long.toString(member.getMemberId()));
            urlBuilder.addPathSegment("optout");
            return service.fetchOptouts(urlBuilder.build());
        }
    }

    public class DoOptoutRequest extends Request {

        private Member member;

        public DoOptoutRequest member(@NotNull Member member) {
            if (member == null)
                throw new IllegalArgumentException("'member' can not be null");
            this.member = member;
            return this;
        }

        public boolean execute() {
            if (member == null)
                throw new IllegalStateException("'member' can not be null");
            urlBuilder.addPathSegment("email");
            urlBuilder.addPathSegment("optout");
            urlBuilder.addPathSegment(member.getEmail());
            return service.optout(urlBuilder.build());
        }
    }

    public FetchAllRequest fetchAll() {
        return new FetchAllRequest();
    }

    public FetchByIdRequest fetchByID() {
        return new FetchByIdRequest();
    }

    public FetchByEmailRequest fetchByEmail() {
        return new FetchByEmailRequest();
    }

    public FetchOptoutsRequest fetchOptouts() {
        return new FetchOptoutsRequest();
    }

    public DoOptoutRequest optout() {
        return new DoOptoutRequest();
    }

}