package me.zikani.interviews.routes;

import com.google.gson.Gson;
import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.ErrorResponse;
import me.zikani.interviews.models.PersonEntity;
import spark.Response;
import spark.Route;

public abstract class AbstractRoute implements Route {
    protected Gson gson = new Gson();
    protected final ComplaintsDAO complaintsDAO;
    protected final PersonDAO personDAO;

    public AbstractRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        this.complaintsDAO = complaintsDAO;
        this.personDAO = personDAO;
    }



    protected String json(Response response, Object data) {
        response.type("application/json");
        return gson.toJson(data);
    }

    protected String created(Response response, Object data) {
        response.type("application/json");
        response.status(201);
        return gson.toJson(data);
    }

    protected String notFound(Response response) {
        response.type("application/json");
        response.status(404);
        return gson.toJson(new ErrorResponse("Entity", "Entity Not Found"));
    }

    protected void validate(Response response, Object data) {
        // TODO:
    }
}
