package com.example.demoneo4j.domain.movie.service;

import com.example.demoneo4j.domain.movie.dao.MovieRepository;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
import com.example.demoneo4j.domain.movie.model.MovieEntityDTO;
import com.example.demoneo4j.domain.person.model.PersonEntityDTO;
import lombok.var;
import org.neo4j.driver.*;
import org.neo4j.driver.internal.shaded.io.netty.util.internal.StringUtil;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author tangmengyue
 * @ClassName MovieService.java
 * @Description TODO
 * @createTime 2023年01月30日 19:07:00
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final Neo4jClient neo4jClient;
    private final Driver driver;
    private final DatabaseSelectionProvider databaseSelectionProvider;

    public MovieService(MovieRepository movieRepository,
                        Neo4jClient neo4jClient,
                        Driver driver,
                        DatabaseSelectionProvider databaseSelectionProvider) {
        this.movieRepository = movieRepository;
        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
    }

    public int voteInMovieByTitle(String title) {
        return this.neo4jClient
                .query("MATCH (m:Movie {title: $title}) " +
                        "WITH m, coalesce(m.votes, 0) AS currentVotes " +
                        "SET m.votes = currentVotes + 1;")
                .in(database())
                .bindAll(Map.of("title", title))
                .run()
                .counters()
                .propertiesSet();
    }

    public MovieEntityDTO getMovieByTitle(String title) {
        return this.neo4jClient
                .query("" +
                        "MATCH (movie:Movie {title: $title}) " +
                        "OPTIONAL MATCH (person:Person)-[r]->(movie) " +
                        "WITH movie, COLLECT({ name: person.name, job: REPLACE(TOLOWER(TYPE(r)), '_in', ''), role: r.role }) as cast " +
                        "RETURN movie { .title, cast: cast }"
                )
                .in(database())
                .bindAll(Map.of("title", title))
                .fetchAs(MovieEntityDTO.class)
                .mappedBy(this::toMovieEntityDTO)
                .one()
                .orElse(null);
    }

    private MovieEntityDTO toMovieEntityDTO(TypeSystem typeSystem, Record record) {
        var movie = record.get("movie");
        return new MovieEntityDTO(movie.get("title").asString(), movie.get("cast").asList((item) -> {
            PersonEntityDTO personEntityDTO = PersonEntityDTO.builder().name(item.get("name").asString()).job(item.get("job").asString()).build();
            var role = item.get("role");
            if (null != role) {
                personEntityDTO.setRole(role.asString());
            }
            return personEntityDTO;
        }));
    }

    /**
     * 既不是neo4jclient的形式也不是omg映射的形式实现查询
     */
    public Map<String, List<Object>> getMovieGraph() {
        var nodes = new ArrayList<>();
        var links = new ArrayList<>();
        try (Session session = sessionFor(database())) {
            List<Record> records = session.readTransaction(tx -> tx.run(""
                    + " MATCH (m:Movie) <- [r:ACTED_IN] - (p:Person)"
                    + " WITH m, p ORDER BY m.title, p.name"
                    + " RETURN m.title AS movie, collect(p.name) AS actors").list());
            records.forEach(record -> {
                var movie = Map.of("label", "actor", "title", record.get("movie").asString());
                var targetIndex = nodes.size();
                nodes.add(movie);

                record.get("actors").asList(Value::asString).forEach(name -> {
                    var actor = Map.of("label", "actor", "title", name);
                    int sourceIndex;
                    if (nodes.contains(actor)) {
                        sourceIndex = nodes.indexOf(actor);
                    } else {
                        nodes.add(actor);
                        sourceIndex = nodes.size() - 1;
                    }
                    links.add(Map.of("source", sourceIndex, "target", targetIndex));
                });
            });
        }
        return Map.of("nodes", nodes, "links", links);
    }

    public Session sessionFor(String database) {
        if (StringUtil.isNullOrEmpty(database)) {
            return driver.session();
        }
        return driver.session(SessionConfig.forDatabase(database));
    }

    public String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    public MovieEntity findOneByTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }

    public long delAllByTitle(String title) {
        return movieRepository.deleteAllByTitle(title);
    }

    public Long delActRelationEntities() {
        return movieRepository.delActRelationEntities();
    }

    public MovieEntity save(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    public List<MovieEntity> saveAll(List<MovieEntity> movies) {
        return movieRepository.saveAll(movies);
    }


}
