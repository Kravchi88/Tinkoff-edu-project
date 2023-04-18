import ru.tinkoff.edu.java.scrapper.dao.UserDao;
import ru.tinkoff.edu.java.scrapper.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest extends IntegrationEnvironment {
    @Autowired
    private UserDao userDao;
    @Test
    @Transactional
    @Rollback
    void add() {
        userDao.add(new User(0, "Ilya"));
        userDao.findAll()
                .forEach(user -> assertEquals(user.username(), "Ilya"));
    }

    @Test
    @Transactional
    @Rollback
    void remove() {
        userDao.add(new User(0, "Ilya"));
        userDao.remove(0);
        userDao.findAll()
                .forEach(user -> {
                    throw  new RuntimeException("User table is not empty but it actually must be empty");
                });
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
        userDao.add(new User(0, "Ilya"));
        userDao.add(new User(1, "Egor"));
        userDao.add(new User(2, "Timur"));
        int userCount = 0;
        for(User ignored :userDao.findAll()){
            userCount++;
        }
        assertEquals(userCount, 3);
    }
}