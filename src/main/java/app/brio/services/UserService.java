package app.brio.services;

import app.brio.DTO.*;
import app.brio.exceptions.*;
import app.brio.models.*;
import app.brio.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final PasswordEncoder cryptor;
    private final UserRepository repo;

    public UserService(PasswordEncoder pass, UserRepository repo){
        cryptor = pass;
        this.repo = repo;
    }

    /**
     * Creates a new user account from the registration data.
     * The password is hashed before persistence
     * @param userCreationDTO the registration data(login, mail, password, first name and last name)
     * @return a UserResponseDTO representing the created account (without the password)
     * @throws AlreadyExistsException if the login or the mail is Already in use
     */
    @Transactional
    public UserResponseDTO create(UserCreationDTO userCreationDTO) {

        if(repo.existsByLogin(userCreationDTO.getLogin()) || repo.existsByMail(userCreationDTO.getMail())){
            throw new AlreadyExistsException();
        }

        String hashedPassword = cryptor.encode(userCreationDTO.getPassword());
        User user = new User(userCreationDTO.getLogin(), userCreationDTO.getMail(), hashedPassword, userCreationDTO.getFirstName(), userCreationDTO.getLastName());

        repo.save(user);

        return new UserResponseDTO(user.getId(), user.getLogin(), user.getMail(), user.getFirstName(), user.getLastName(), user.getDateCreation());
    }
}
