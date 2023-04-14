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

    public List<User> list() throws Exception {
        return userRepository.findAll();
    }

    public void saveAndUpdate(User user) throws Exception {
        if (!user.getId().isEmpty()) {
            update(user);
        } else {
            save(user);
        }
    }
    public void save(User user) throws Exception {
        try {
            user.setId(ObjectId.get().toString());
            user.setCreated(LocalTimeZone.now());
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

    public Optional<User> getUser(String id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

    public Optional<User> findByName(String name) throws Exception {
        try {
            return userRepository.findByName(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

    public Optional<User> findByEmail(User user) throws Exception {
        try {
            return userRepository.findByEmail(user.getEmail());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

    public void update(User user) throws Exception {
        try {
            Optional<User> optUser = getUser(user.getId());
            if (optUser.isPresent()) {
                User userDB = optUser.get();
                userDB.setName(user.getName());
                userDB.setCel(user.getCel());
                userDB.setEmail(user.getEmail());
                userDB.setUpdated(LocalTimeZone.now());
                userRepository.save(userDB);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

    public void delete(String id) throws Exception {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw(e);
        }
    }

}
