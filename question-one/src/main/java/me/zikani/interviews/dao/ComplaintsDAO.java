package me.zikani.interviews.dao;

import me.zikani.interviews.models.ComplaintsEntity;
import me.zikani.interviews.models.PersonEntity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterJoinRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;


@RegisterBeanMapper(ComplaintsEntity.class)
public interface ComplaintsDAO {

    @SqlUpdate("""
    CREATE TABLE IF NOT EXISTS complaints (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        description TEXT not null,
        areas text not null,
        filedById integer not null,
        isResolved boolean not null default false,
        filedOn date not null,
        resolved_on date null,
        createdAt timestamp not null,
        updatedAt timestamp not null
    );
        
    alter table complaints add constraint fk_filedById foreign key(filedById) references people(id);
    """)
    void createTable();

    @SqlQuery("SELECT id, description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt FROM complaints")
    List<ComplaintsEntity> findAll();

    @SqlQuery("SELECT id, description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt FROM complaints OFFSET :offset LIMIT :limit")
    List<ComplaintsEntity> findAllPaginated(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("SELECT id, description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt FROM complaints WHERE id = ?")
    Optional<ComplaintsEntity> findById(@Bind long id);

    @SqlUpdate("INSERT INTO complaints (description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt) VALUES (:e.description,:e.areas,:e.assignedToID,:e.filedBy,:e.isresolved,:e.filedOn,:e.resolvedOn,:e.createdAt,:e.updatedAt) RETURNING id")
    void insert(@BindBean("e") ComplaintsEntity entity);

    @SqlUpdate("UPDATE complaints SET description = :e.description, areas = :e.areas, assigned_to_id = :e.assignedToID, filedById = :e.filedBy, isresolved = :e.isresolved, filedOn = :e.filedOn, resolved_on = :e.resolvedOn, createdAt = :e.createdAt, updatedAt = :e.updatedAt WHERE id = :e.id")
    void update(@BindBean("e") ComplaintsEntity entity);

    @SqlUpdate("DELETE FROM complaints WHERE id = ?")
    void delete(@Bind long id);

    @SqlQuery("SELECT id, description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt FROM complaints WHERE filedById = :filer")
    List<ComplaintsEntity> findAllFiledBy(@Bind("filer") long id);

    @SqlQuery("SELECT id, description, areas, assigned_to_id, filedById, isresolved, filedOn, resolved_on, createdAt, updatedAt FROM complaints WHERE <column> = :query OR <column> LIKE query")
    List<ComplaintsEntity> findUsing(@Define("column") String column, @Bind("query") String value);
}

