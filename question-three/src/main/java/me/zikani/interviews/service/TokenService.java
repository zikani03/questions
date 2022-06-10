package me.zikani.interviews.service;

import me.zikani.interviews.models.Token;

import java.util.Optional;

public interface TokenService {

    Optional<Token> generateToken();

    void disableToken(String token);

    Optional<Token> generateOrGetDisableToken();
}
