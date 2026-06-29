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
     * Maps a User entity to its response representation, omitting sensitive
     * fields such as the hashed password. Centralizes the conversion so every
     * method returning user data produces a consistent DTO.
     */
    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(user.getId(), user.getLogin(), user.getMail(), user.getFirstName(), user.getLastName(), user.getDateCreation());
    }

    /**
     * Retrieves the managed User entity for the given id, or throws if none exists.
     * Centralizes the "fetch or fail" logic reused by read and write operations.
     */
    private User findEntityById(Long userId){
        return repo.findById(userId).orElseThrow(NotFoundException::new);
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

        return toResponseDTO(user);
    }

    /**
     * Finds a user in database by its id
     * @param userId the id of the user to find
     * @return a UserResponseDTO representing the user's information
     * @throws NotFoundException if there is no user with the given id
     */
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long userId){
        User user = findEntityById(userId);

        return toResponseDTO(user);
    }

    /**
     * Updates a user's information
     * @param userId the id of the user to update
     * @param userUpdateDTO the information to update on the user object
     * @return a UserResponseDTO representing the user's new information
     * @throws NotFoundException if there is no user with the given id
     * @throws AlreadyExistsException if the login is Already in use
     */
    @Transactional
    public UserResponseDTO update(Long userId, UserUpdateDTO userUpdateDTO) {

        User user = findEntityById(userId);

        if(userUpdateDTO.getMail() != null && !userUpdateDTO.getMail().equals(user.getMail()) && repo.existsByMail(userUpdateDTO.getMail())){
            throw new AlreadyExistsException();
        }

        if(userUpdateDTO.getMail() != null && !userUpdateDTO.getMail().isEmpty()){
            user.setMail(userUpdateDTO.getMail());
        }

        if(userUpdateDTO.getFirstName() != null && !userUpdateDTO.getFirstName().isEmpty()){
            user.setFirstName(userUpdateDTO.getFirstName());
        }
        if(userUpdateDTO.getLastName() != null && !userUpdateDTO.getLastName().isEmpty()){
            user.setLastName(userUpdateDTO.getLastName());
        }

        repo.save(user);

        return toResponseDTO(user);
    }

    /**
     * Deletes a user from the database. Related data is removed by cascade
     * @param userId the id of the user to delete
     * @throws NotFoundException if there is no user with the given id
     */
    @Transactional
    public void delete(Long userId){
        User user = findEntityById(userId);
        repo.delete(user);
    }
}
