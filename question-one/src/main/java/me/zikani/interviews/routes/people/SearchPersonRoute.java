package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class SearchPersonRoute extends AbstractRoute {
    public SearchPersonRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String column = request.queryParams("field");
        String value = request.queryParams("s");
        try {
            List<PersonEntity> people = personDAO.findUsing(column, value);
            people.forEach(person -> person.setComplaintsFiled(complaintsDAO.findAllFiledBy(person.getId())));
            return json(response, people);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("Failed to execute query", e);
        }

        return notFound(response);
    }
}
