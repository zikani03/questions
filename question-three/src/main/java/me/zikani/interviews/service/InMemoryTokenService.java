package me.zikani.interviews.service;

import me.zikani.interviews.models.Token;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Implements a Token Service that uses in-memory hash map and queue to store the parameters.
 * These could eventually be flushed to a store like a database or file system.
 *
 */
public class InMemoryTokenService implements TokenService {
    private final Map<String, Token> db = new ConcurrentHashMap<>();
    private final Queue<String> disabledTokens = new ConcurrentLinkedQueue<>();

    /**
     * There are 14,307,150 possible combinations of the characters based on nCr(30, 9)
     * See formula: https://www.calculatorsoup.com/calculators/discretemathematics/combinations.php?n=30&r=9&action=solve
     */
    private int MAX_TOKENS = 14_307_150;

    private String[] ALPHABET = new String[]{
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    @Override
    public Optional<Token> generateOrGetDisableToken() {
        if (disabledTokens.isEmpty()) {
            return generateToken();
        }
        Token token = db.get(disabledTokens.remove());
        token.setDeleted(false);
        return Optional.of(token);
    }

    @Override
    public Optional<Token> generateToken() {
        List<String> allChars  = Arrays.stream(ALPHABET).collect(Collectors.toList());
        boolean generated = false;
        do {
            Collections.shuffle(allChars);
            String tokenKey = allChars.stream()
                    .limit(9)
                    .collect(Collectors.joining(""));

            Token token = new Token(tokenKey);
            if (db.size() > MAX_TOKENS) {
                // Cannot generate any more UNIQUE tokens, keyspace is exhausted
                return Optional.empty();
            }

            if (db.containsKey(tokenKey)) {
                continue;
            } else {
                generated = true;
                db.put(tokenKey, token);
                return Optional.of(token);
            }

        } while(! generated);

        return Optional.empty();
    }

    @Override
    public void disableToken(String tokenKey) {
        if (db.containsKey(tokenKey)) {
            Token token = db.get(tokenKey);
            token.setDeleted(true);
            db.put(tokenKey, token);
            disabledTokens.add(tokenKey);
        }
    }
}
