package me.zikani.interviews;

import me.zikani.interviews.models.Token;
import me.zikani.interviews.routes.CreateTokenRoute;
import me.zikani.interviews.routes.DisableTokenRoute;
import me.zikani.interviews.service.InMemoryTokenService;
import me.zikani.interviews.service.TokenService;
import spark.Spark;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Question One API
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Spark.port(8080);
        Spark.ipAddress("0.0.0.0");

        TokenService service = new InMemoryTokenService();

        Spark.post("/tokens/create", new CreateTokenRoute(service));
        Spark.delete("/tokens/disable/:code", new DisableTokenRoute(service));
    }
}
