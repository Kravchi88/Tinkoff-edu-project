import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.model.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LinkDaoTest extends IntegrationEnvironment {
    @Autowired
    private LinkDao linkDao;

    @Test
    @Transactional
    @Rollback
    void add() {
        linkDao.add(new Link(0, "https://github.com/Kravchi88/Tinkoff-edu-project"));
        linkDao.findAll()
                .forEach(link ->{
                    assertEquals(link.url(), "https://github.com/Kravchi88/Tinkoff-edu-project");
                    assertEquals(link.id(), 0);
                });
    }

    @Test
    @Transactional
    @Rollback
    void remove() {
        linkDao.add(new Link(0, "https://github.com/Kravchi88/Tinkoff-edu-project"));
        linkDao.remove(0);
        linkDao.findAll()
                .forEach(link ->{
                    throw new RuntimeException("Link table is not empty but it actually must be empty");
                });
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
        linkDao.add(new Link(0, "https://github.com/Kravchi88/Tinkoff-edu-project"));
        linkDao.add(new Link(1, "https://github.com/Kravchi88/Java-Labs"));
        linkDao.add(new Link(2, "https://stackoverflow.com/questions/245062/whats-the-difference-between-javascript-and-java"));
        int countLinks = 0;
        for(Link ignored :linkDao.findAll()){
            countLinks++;
        }
        assertEquals(countLinks, 3);
    }
}