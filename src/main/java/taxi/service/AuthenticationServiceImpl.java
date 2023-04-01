package taxi.service;

import taxi.dao.DriverDao;
import taxi.exception.AuthenticationException;
import taxi.lib.Injector;
import taxi.lib.Service;
import taxi.model.Driver;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector =
            Injector.getInstance("taxi");
    private final DriverDao driverDao = (DriverDao) injector
            .getInstance(DriverDao.class);

    @Override
    public Driver login(String login, String password)
            throws AuthenticationException {
        Optional<Driver> driver = driverDao.findByLogin(login);
        if (driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Login or password was incorrect");
    }
}
