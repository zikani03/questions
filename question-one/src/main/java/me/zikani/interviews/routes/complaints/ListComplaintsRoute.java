package me.zikani.interviews.routes.complaints;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.ComplaintsEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ListComplaintsRoute extends AbstractRoute {

    public ListComplaintsRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        List<ComplaintsEntity> complaints = complaintsDAO.findAll();
        return json(response, complaints);
    }
}
