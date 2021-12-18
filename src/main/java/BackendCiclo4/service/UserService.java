package BackendCiclo4.service;

import BackendCiclo4.model.User;
import BackendCiclo4.repository.UserRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class User Service
 */
@Service
public class UserService {
     @Autowired
    private UserRepositorio userRepository;

    /**
     * Get = List of All Users
     * @return
     */
    public List<User> getAll() {
        return userRepository.getAll();
    }

    /**
     * Get = User by its id
     * @param id
     * @return
     */
    public Optional<User> getUser(int id) { return userRepository.getUser(id); }

    /**
     * This method saves a new user
     * @param user
     * @return
     */
    public User create(User user) {
        if (user.getId() == null) {
            return user;            
        }else {
            Optional<User> e = userRepository.getUser(user.getId());
            if (e.isEmpty()) {
                if (emailExists(user.getEmail())==false){
                    return userRepository.create(user);
                }else{
                    return user;
                }
            }else{
                return user;
            }           
        }
    }

    /**
     * This method updates a user
     * @param user
     * @return
     */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = userRepository.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }

                if (user.getBirthtDay() != null){
                    userDb.get().setBirthtDay(user.getBirthtDay());
                }

                if (user.getMonthBirthtDay() != null){
                    userDb.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }

                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }

                if (user.getType() != null) {
                    userDb.get().setType(user.getType());
                }
                
                userRepository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * This method deletes a User
     * @param userId
     * @return
     */
    public boolean delete(int userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     * This method checks if an email exists
     * @param email
     * @return
     */
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    /**
     * This method verifies if a user is registered by its email and password
     * @param email
     * @param password
     * @return
     */
    public User authenticateUser(String email, String password) {
        Optional<User> usuario = userRepository.authenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }
    }

    public List<User> getByMonthBirthDay(String month){
        return userRepository.getByMonthBirthDay(month);
    }
}
