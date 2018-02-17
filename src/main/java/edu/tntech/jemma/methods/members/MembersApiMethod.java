package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.methods.ApiMethod;

public abstract class MembersApiMethod<R> extends ApiMethod<R> {
    public MembersApiMethod(JEmma jemma) {
        super(jemma);
        urlBuilder.addPathSegment("members");
    }
}
