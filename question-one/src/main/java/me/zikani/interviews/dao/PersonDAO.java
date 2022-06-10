package me.zikani.interviews.dao;

import me.zikani.interviews.models.PersonEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(PersonEntity.class)
public interface PersonDAO {

    @SqlUpdate("""
    CREATE TABLE IF NOT EXISTS people (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        firstName varchar(50) not null,
        lastName varchar(50) not null,
        dateOfBirth date null,
        phone varchar(20),
        createdAt timestamp not null,
        updatedAt timestamp not null
    );
    """)
    void createTable();

    @SqlQuery("SELECT id, firstName, lastName, dateOfBirth, phone, createdAt, updatedAt FROM people")
    List<PersonEntity> findAll();

    @SqlQuery("SELECT id, firstName, lastName, dateOfBirth, phone, createdAt, updatedAt FROM people OFFSET :offset LIMIT :limit")
    List<PersonEntity> findAllPaginated(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT id, firstName, lastName, dateOfBirth, phone, createdAt, updatedAt FROM people WHERE id = ?")
    Optional<PersonEntity> findById(@Bind long id);

    @SqlUpdate("INSERT INTO people (firstName, lastName, dateOfBirth, phone, createdAt, updatedAt) VALUES (:e.firstName,:e.lastName,:e.dateOfBirth,:e.phone,:e.createdAt,:e.updatedAt) RETURNING id")
    void insert(@BindBean("e") PersonEntity entity);

    @SqlUpdate("UPDATE people SET firstName = :e.firstName, lastName = :e.lastName, dateOfBirth = :e.dateOfBirth, phone = :e.phone, createdAt = :e.createdAt, updatedAt = :e.updatedAt WHERE id = :e.id")
    void update(@BindBean("e") PersonEntity entity);

    @SqlQuery("SELECT id, firstName, lastName, dateOfBirth, phone, createdAt, updatedAt FROM people WHERE <column> = :query OR <column> LIKE :query")
    List<PersonEntity> findUsing(@Define("column") String column, @Bind("query") String value);

    @SqlUpdate("DELETE FROM people WHERE id = ?")
    void delete(@Bind long id);
}
