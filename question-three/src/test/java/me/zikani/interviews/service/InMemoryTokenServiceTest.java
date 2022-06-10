package me.zikani.interviews.service;

import junit.framework.TestCase;
import me.zikani.interviews.models.Token;
import org.eclipse.jetty.http.HttpTokens;
import org.junit.Test;

import java.util.Optional;

public class InMemoryTokenServiceTest extends TestCase {

    @Test
    public void testGenerateToken() {
        InMemoryTokenService service = new InMemoryTokenService();
        Optional<Token> token = service.generateToken();
        assertNotNull(token);
        assertFalse(token.isEmpty());
        String value = token.get().getToken();
        System.out.println("TOKEN = " + value);
        assertTrue(value.length() == 9);
    }

    public void testDisableToken() {
    }
}