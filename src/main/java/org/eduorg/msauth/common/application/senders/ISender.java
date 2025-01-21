package org.eduorg.msauth.common.application.senders;

public interface ISender<T> {
    void send(T Data);
}
