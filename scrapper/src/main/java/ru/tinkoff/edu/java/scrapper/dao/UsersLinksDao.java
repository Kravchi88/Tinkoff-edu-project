package ru.tinkoff.edu.java.scrapper.dao;

import ru.tinkoff.edu.java.scrapper.model.UsersLinks;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsersLinksDao {
    public UsersLinksDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<UsersLinks> userLinksRowMapper = new DataClassRowMapper<>(UsersLinks.class);
    public void add(UsersLinks userLinks){
        jdbcTemplate.update("insert into users_and_links(user_chat_id, link_id) values(:userChatId, :linkId)",
                new BeanPropertySqlParameterSource(userLinks));
    }
    public void removeByUserChatId(long userChatId){
        jdbcTemplate.update("delete from users_and_links where user_chat_id=:userChatId",
                Map.of("userChatId", userChatId));
    }

    public void removeByLinkId(long linkId){
        jdbcTemplate.update("delete from users_and_links where link_id=:linkId",
                Map.of("linkId", linkId));
    }
    public Iterable<UsersLinks> findAll(){
        return jdbcTemplate.query("select * from users_and_links", userLinksRowMapper);
    }
}