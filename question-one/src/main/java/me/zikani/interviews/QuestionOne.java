package me.zikani.interviews;

import me.zikani.interviews.dao.ComplaintsDAO;
import me.zikani.interviews.dao.PersonDAO;
import me.zikani.interviews.routes.complaints.GetComplaintRoute;
import me.zikani.interviews.routes.complaints.ListComplaintsRoute;
import me.zikani.interviews.routes.complaints.NewComplaintRoute;
import me.zikani.interviews.routes.complaints.SearchComplaintsRoute;
import me.zikani.interviews.routes.people.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.Spark;

public class QuestionOne {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:sqlite:question_one.db");
        jdbi.installPlugin(new SqlObjectPlugin());
        ComplaintsDAO complaintsDAO = jdbi.onDemand(ComplaintsDAO.class);
        PersonDAO peopleDAO = jdbi.onDemand(PersonDAO.class);
        Spark.ipAddress("0.0.0.0");
        Spark.port(3000);

        peopleDAO.createTable();
        complaintsDAO.createTable();

        Spark.get("/complaints", new ListComplaintsRoute(complaintsDAO, peopleDAO));
        Spark.post("/complaints", new NewComplaintRoute(complaintsDAO, peopleDAO));
        Spark.get("/complaints/:id", new GetComplaintRoute(complaintsDAO, peopleDAO));
        Spark.get("/complaints/search", new SearchComplaintsRoute(complaintsDAO, peopleDAO));

        Spark.get("/people", new ListPersonRoute(complaintsDAO, peopleDAO));
        Spark.get("/people/search", new SearchPeopleRoute(complaintsDAO, peopleDAO));
        Spark.post("/people/new", new NewPersonRoute(complaintsDAO, peopleDAO));
        Spark.post("/people/bulk/new", new NewPeopleListRoute(complaintsDAO, peopleDAO));
    }
}
