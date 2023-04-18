package ru.tinkoff.edu.java.scrapper.dao;

import ru.tinkoff.edu.java.scrapper.model.Link;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LinkDao {
    public LinkDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Link> linkRowMapper = new DataClassRowMapper<>(Link.class);
    public void add(Link link){
        jdbcTemplate.update("insert into links(id, url, last_update) values(:id, :url, :lastUpdate)",
                new BeanPropertySqlParameterSource(link));
    }
    public void remove(long id){
        jdbcTemplate.update("delete from links where id=:id", Map.of("id", id));
    }
    public Iterable<Link> findAll(){
        return jdbcTemplate.query("select * from links", linkRowMapper);
    }
}
