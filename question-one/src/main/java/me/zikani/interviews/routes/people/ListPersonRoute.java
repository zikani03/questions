package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.util.List;

public class ListPersonRoute extends AbstractRoute {

    public ListPersonRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        List<PersonEntity> people = personDAO.findAll();
        return json(response, people);
    }
}
