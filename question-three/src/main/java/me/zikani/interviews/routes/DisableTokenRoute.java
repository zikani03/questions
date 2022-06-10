package me.zikani.interviews.routes;

import me.zikani.interviews.service.TokenService;
import spark.Request;
import spark.Response;
import spark.Route;

public class DisableTokenRoute implements Route {
    private final TokenService tokenService;

    public DisableTokenRoute(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.body("text/plain");
        String tokenKey = request.params("code");
        if (tokenKey != null) {
            tokenService.disableToken(tokenKey);
        }
        return "Disabled token " + tokenKey;
    }
}
