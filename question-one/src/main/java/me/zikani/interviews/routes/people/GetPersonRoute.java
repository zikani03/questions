package me.zikani.interviews.routes.people;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class GetPersonRoute extends AbstractRoute {
    public GetPersonRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        long id = Long.parseLong(request.params("id"));

        Optional<PersonEntity> person = personDAO.findById(id);
        if (person.isPresent()) {
            person.get().setComplaintsFiled(complaintsDAO.findAllFiledBy(person.get().getId()));
            return json(response, person);
        }

        return notFound(response);
    }
}
