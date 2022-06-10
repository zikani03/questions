package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;
import java.time.Instant;

public class NewPersonRoute extends AbstractRoute {
    public NewPersonRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        PersonEntity person = gson.fromJson(request.body(), PersonEntity.class);
        super.validate(response, person);
        person.setCreatedAt(Timestamp.from(Instant.now()));
        person.setUpdatedAt(Timestamp.from(Instant.now()));
        personDAO.insert(person);
        return created(response, person);
    }
}
