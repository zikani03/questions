package me.zikani.interviews.routes.complaints;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.models.ComplaintsEntity;
import me.zikani.interviews.models.PersonEntity;
import me.zikani.interviews.routes.AbstractRoute;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class GetComplaintRoute extends AbstractRoute {
    public GetComplaintRoute(ComplaintsDAO complaintsDAO, PersonDAO personDAO) {
        super(complaintsDAO, personDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        long id = Long.parseLong(request.params("id"));

        Optional<ComplaintsEntity> entity = complaintsDAO.findById(id);
        if (entity.isPresent()) {
            personDAO.findById(entity.get().getFiledById())
                    .ifPresent(person -> entity.get().setFiledBy(person));

            return json(response, entity);
        }

        return notFound(response);
    }
}
