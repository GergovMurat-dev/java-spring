package com.murat.shared.utils;

public class ServiceResult<T> {
    private final boolean error;
    private final T value;
    private final String errorMessage;

    private ServiceResult(boolean error, T value, String errorMessage) {
        this.error = error;
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public static <T> ServiceResult<T> success(T value) {
        return new ServiceResult<>(false, value, null);
    }

    public static <T> ServiceResult<T> failure(String errorMessage) {
        return new ServiceResult<>(true, null, errorMessage);
    }

    public boolean isError() {
        return error;
    }

    public T getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
