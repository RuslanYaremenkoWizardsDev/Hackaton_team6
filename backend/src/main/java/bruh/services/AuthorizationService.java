package bruh.services;

import bruh.entity.User;
import bruh.repo.IPostgresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final IPostgresRepo iPostgresRepo;

    @Autowired
    public AuthorizationService(IPostgresRepo iPostgresRepo) {
        this.iPostgresRepo = iPostgresRepo;
    }

    public String authorizeUser(User user) {
        iPostgresRepo.save(user);
        return "";
    }

}
