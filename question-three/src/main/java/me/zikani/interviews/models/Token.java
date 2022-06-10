package me.zikani.interviews.models;

import java.io.Serializable;

/**
 * Unique 8 Character token
 *
 */
public class Token implements Serializable {
    private String token;
    private boolean deleted;
    private long timestamp;

    public Token(String key) {
        setToken(key);
        this.deleted = false;
        this.timestamp = System.currentTimeMillis();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token.length() > 9) {
            throw new TokenException("Token cannot be more than 9 characters long");
        }
        this.token = token;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJSON() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        return token != null ? token.equalsIgnoreCase(token1.token) : token1.token == null;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    public static class TokenException extends RuntimeException {
        public TokenException(String message) {
            super(message);
        }
    }

}
