package br.tie.progweek;

import br.tie.progweek.model.User;
import br.tie.progweek.repository.UserRepository;
import br.tie.progweek.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void findByNameOk() throws Exception {
        Optional<User> user = userRepository.findByEmail("contato@tiespecialize.com.br");
        assertThat(user.isPresent()).isTrue();
    }
    @Test
    void findByIdOk() throws Exception {
        Optional<User> user = userService.getUser("6439919b3d092161a1d439b9");
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().getEmail()).isEqualTo("contato@tiespecialize.com.br");
    }

}