package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

public class SearchPeopleRoute extends AbstractRoute {
    public SearchPeopleRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
