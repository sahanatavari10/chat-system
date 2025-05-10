package com.practice.chatsystem.user;

public sealed interface UserLoginResult {
    record IsPresent(User user) implements UserLoginResult{
    }

    record NotFound() implements UserLoginResult{
    }

    record InvalidCredentials() implements UserLoginResult{
    }
}
