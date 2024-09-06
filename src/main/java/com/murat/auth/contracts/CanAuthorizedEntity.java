package com.murat.auth.contracts;

public interface CanAuthorizedEntity {

    Long getId();

    String getName();

    String getEmail();

    String getPassword();
}
