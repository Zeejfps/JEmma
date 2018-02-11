package edu.tntech.jemma;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.tntech.jemma.models.Member;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.List;

class Members {

    private static final String SCHEME = "https";
    private static final String API_URL = "api.e2ma.net";
    private static final String ENDPOINT = "members";

    private final JEmma jEmma;

    Members(JEmma jEmma) {
        this.jEmma = jEmma;
    }

    private abstract class FetchRequest<T> {

        HttpUrl.Builder urlBuilder;

        private FetchRequest() {
            urlBuilder = new HttpUrl.Builder();
            urlBuilder.scheme(SCHEME);
            urlBuilder.host(API_URL);
            urlBuilder.addPathSegment(jEmma.getAccountID());
            urlBuilder.addPathSegment(ENDPOINT);
        }

        public abstract T execute();

    }


    public class FetchAllRequest extends FetchRequest<List<Member>> {

        private int from, to;

        public FetchAllRequest from(int from) {
            if (from < 0)
                throw new IllegalArgumentException("'from' can not be negative");

            this.from = from;
            urlBuilder.setQueryParameter("start", Integer.toString(from));
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

        @Override
        public List<Member> execute() {
            if (to < from)
                throw new IllegalStateException("'to' can not come before 'from'");

            System.out.println(urlBuilder.build());
            return Collections.emptyList();
        }

    }

    public class FetchByIdRequest extends FetchRequest<Member> {

        private int id = -1;

        public FetchByIdRequest id(int id) {
            this.id = id;
            return this;
        }

        public FetchByIdRequest showDeleted() {
            urlBuilder.setQueryParameter("deleted", "true");
            return this;
        }

        @Override
        public Member execute() {
            urlBuilder.addPathSegment(Integer.toString(id));
            System.out.println(urlBuilder.build());
            return null;
        }

    }

    public class FetchByEmailRequest extends FetchRequest<Member> {

        private String email = "";

        public FetchByEmailRequest email(String email) {
            this.email = email;
            return this;
        }

        public FetchByEmailRequest showDeleted() {
            urlBuilder.setQueryParameter("deleted", "true");
            return this;
        }

        @Override
        public Member execute() {
            urlBuilder.addPathSegment(email);
            System.out.println(urlBuilder.build());
            return null;
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

}