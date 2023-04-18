import org.junit.Test;

import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dao.UserDao;
import ru.tinkoff.edu.java.scrapper.dao.UsersLinksDao;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.User;
import ru.tinkoff.edu.java.scrapper.model.UsersLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatabaseScrapperTest extends IntegrationEnvironment {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LinkDao linkDao;
    @Autowired
    private UsersLinksDao userLinksDao;

    @Test
    @Transactional
    @Rollback
    void addLinkForUser(){

        userDao.add(new User(0, "Maks"));

        linkDao.add(new Link(0, "https://github.com/Kravchi88/Tinkoff-edu-project"));

        userLinksDao.add(new UsersLinks(0, 0));

        userDao.findAll()
                .forEach(user -> assertEquals(user.username(), "Ilya"));
        linkDao.findAll()
                .forEach(link -> assertEquals(link.url(),
                        "https://github.com/Kravchi88/Tinkoff-edu-project"));
        userLinksDao.findAll().
                forEach(userLinks ->{
                    assertEquals(userLinks.linkId(), 0);
                    assertEquals(userLinks.userChatId(), 0);
                });
    }

}