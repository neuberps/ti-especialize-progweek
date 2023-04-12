package br.tie.progweek.service;

import br.tie.progweek.model.User;
import br.tie.progweek.repository.UserRepository;
import br.tie.progweek.util.LocalTimeZone;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User saveAndUpdate(User user) {
        if (!user.getId().isEmpty()) {
            return update(user);
        } else {
            return save(user);
        }
    }
    public User save(User user) {
        try {
            user.setId(ObjectId.get().toString());
            user.setCreated(LocalTimeZone.now());
            return userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Optional<User> getUser(String id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public User update(User user) {
        try {
            Optional<User> optUser = getUser(user.getId());
            if (optUser.isPresent()) {
                User userDB = optUser.get();
                userDB.setName(user.getName());
                userDB.setCel(user.getCel());
                userDB.setEmail(user.getEmail());
                userDB.setUpdated(LocalTimeZone.now());
                return userRepository.save(userDB);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Boolean delete(String id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
