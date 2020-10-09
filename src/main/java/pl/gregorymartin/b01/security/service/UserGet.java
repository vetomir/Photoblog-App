package pl.gregorymartin.b01.security.service;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.security.repository.sql.SqlUserRepository;

@Service
class UserGet {
    private SqlUserRepository sqlUserRepository;

    public UserGet(final SqlUserRepository sqlUserRepository) {
        this.sqlUserRepository = sqlUserRepository;
    }

}
