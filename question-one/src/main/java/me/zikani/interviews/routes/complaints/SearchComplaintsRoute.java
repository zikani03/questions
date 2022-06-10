package me.zikani.interviews.routes.complaints;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.ComplaintsEntity;
import me.zikani.interviews.routes.AbstractRoute;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class SearchComplaintsRoute extends AbstractRoute {

    public SearchComplaintsRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String column = request.queryParams("field");
        String value = request.queryParams("s");
        try {
            List<ComplaintsEntity> complaints = complaintsDAO.findUsing(column, value);
            complaints.forEach(complaint -> complaint.setFiledBy(personDAO.findById(complaint.getFiledById()).get()));
            return json(response, complaints);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("Failed to execute query", e);
        }

        return notFound(response);
    }
}
