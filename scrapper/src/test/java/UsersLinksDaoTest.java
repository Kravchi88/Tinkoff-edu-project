import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dao.UserDao;
import ru.tinkoff.edu.java.scrapper.dao.UsersLinksDao;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.User;
import ru.tinkoff.edu.java.scrapper.model.UsersLinks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UsersLinksDaoTest extends IntegrationEnvironment {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LinkDao linkDao;
    @Autowired
    private UsersLinksDao usersLinksDao;
    @Test
    @Transactional
    @Rollback
    void add() {
        userDao.add(new User(0, "Maks"));
        linkDao.add(new Link(0, "https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));
        usersLinksDao.add(new UsersLinks(0, 0));
        usersLinksDao.findAll()
                .forEach(userLinks -> {
                    assertEquals(userLinks.linkId(), 0);
                    assertEquals(userLinks.userChatId(), 0);
                });
    }

    @Test
    @Transactional
    @Rollback
    void removeByUserChatId() {
        userDao.add(new User(0, "Maks"));
        linkDao.add(new Link(0, "https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));
        usersLinksDao.add(new UsersLinks(0, 0));
        usersLinksDao.findAll()
                .forEach(userLinks -> {
                    assertEquals(userLinks.linkId(), 0);
                    assertEquals(userLinks.userChatId(), 0);
                });
        usersLinksDao.removeByUserChatId(0);
        usersLinksDao.findAll()
                .forEach(
                        userLinks ->{
                            throw new RuntimeException("Link table is not empty but it actually must be empty");
                        }
                );
    }

    @Test
    @Transactional
    @Rollback
    void removeByLinkId() {
        userDao.add(new User(0, "Ilya"));
        linkDao.add(new Link(0, "https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));
        usersLinksDao.add(new UsersLinks(0, 0));
        usersLinksDao.findAll()
                .forEach(userLinks -> {
                    assertEquals(userLinks.linkId(), 0);
                    assertEquals(userLinks.userChatId(), 0);
                });
        usersLinksDao.removeByLinkId(0);
        usersLinksDao.findAll()
                .forEach(
                        userLinks -> {
                            throw new RuntimeException("Link table is not empty but it actually must be empty");
                        }
                );
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
        userDao.add(new User(0, "Ilya"));
        userDao.add(new User(1, "Egor"));
        userDao.add(new User(2, "Timur"));
        linkDao.add(new Link(0, "https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));
        linkDao.add(new Link(1, "https://github.com/Kravchi88/Tinkoff-edu-project"));
        linkDao.add(new Link(2, "https://github.com/Kravchi88/Java-Labs"));
        usersLinksDao.add(new UsersLinks(0, 0));
        usersLinksDao.add(new UsersLinks(0, 1));
        usersLinksDao.add(new UsersLinks(0, 2));
        usersLinksDao.add(new UsersLinks(1, 2));
        usersLinksDao.add(new UsersLinks(2, 0));
        usersLinksDao.findAll()
                .forEach(userLinks -> {
                    switch ((int) userLinks.userChatId()){
                        case 0 -> assertTrue(Set.of(0L, 1L, 2L).contains(userLinks.linkId()));
                        case 1 -> assertEquals(2, userLinks.linkId());
                        case 2 -> assertEquals(0, userLinks.linkId());
                        default -> throw new RuntimeException("Unknown userChatId");
                    }
                });
        usersLinksDao.removeByUserChatId(0);
        usersLinksDao.removeByUserChatId(1);
        usersLinksDao.removeByUserChatId(2);
        usersLinksDao.findAll()
                .forEach(
                        userLinks -> {
                            throw new RuntimeException("UserLinks table is not empty but it actually must be empty");
                        }
                );
    }
}
