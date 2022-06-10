package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.NewPeopleList;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;
import java.time.Instant;

import static java.util.Collections.singletonMap;

public class NewPeopleListRoute extends AbstractRoute {
    public NewPeopleListRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        NewPeopleList people = gson.fromJson(request.body(), NewPeopleList.class);
        super.validate(response, people);
        people.getPeople().forEach(person -> {
            person.setCreatedAt(Timestamp.from(Instant.now()));
            person.setUpdatedAt(Timestamp.from(Instant.now()));
            personDAO.insert(person);
        });

        return created(response, singletonMap("message", "People uploaded"));
    }
}
