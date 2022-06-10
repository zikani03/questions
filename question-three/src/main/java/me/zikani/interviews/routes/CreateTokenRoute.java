package me.zikani.interviews.routes;

import me.zikani.interviews.models.Token;
import me.zikani.interviews.service.TokenService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;

public class CreateTokenRoute implements Route {
    private final TokenService tokenService;

    public CreateTokenRoute(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("text/plain");
        Optional<Token> token = tokenService.generateOrGetDisableToken();
        if (token.isPresent()) {
            return token.get().getToken();
        }
        return "Failed to Generate Token";
    }
}
