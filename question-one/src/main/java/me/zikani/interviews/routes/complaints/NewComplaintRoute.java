package me.zikani.interviews.routes.complaints;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.ComplaintsEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;
import java.time.Instant;

public class NewComplaintRoute extends AbstractRoute {

    public NewComplaintRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        ComplaintsEntity complaint = gson.fromJson(request.body(), ComplaintsEntity.class);
        complaint.setCreatedAt(Timestamp.from(Instant.now()));
        complaint.setUpdatedAt(Timestamp.from(Instant.now()));

        complaintsDAO.insert(complaint);

        return created(response, complaint);
    }
}
