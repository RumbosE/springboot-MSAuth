package org.eduorg.msauth.common.utils.result;

public class Result<T> {

    private final T value;
    private final Exception exception;
    private final boolean success;

    private Result(T value, Exception exception, boolean success) {
        if (success && exception != null) {
            throw new IllegalArgumentException("Cannot have an exception if the result is a success");
        }
        if (value != null && exception != null) {
            throw new IllegalArgumentException("Cannot have both a value and an exception");
        }
        if ( !success && exception == null) {
            throw new IllegalArgumentException("Must have an exception if the result is a failure");
        }
        this.value = value;
        this.exception = exception;
        this.success = success;
    }

    public static <T> Result<T> makeResult(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> makeFailure(Exception e) {
        return new Result<>(null, e, false);
    }

    public T getResult() {
        if (value != null) {
            return value;
        } else {
            throw new IllegalStateException("No value present");
        }
    }

    public Exception getFailure() {
        if (exception != null) {
            return exception;
        } else {
            throw new IllegalStateException("No exception present");
        }
    }

    public boolean isSuccess() {
        return success;
    }
}