package app.brio.DTO;

import java.time.LocalDateTime;

public class UserCreationDTO {
    private String login;
    private String mail;
    private String password;
    private String firstName;
    private String lastName;

    public UserCreationDTO(){}

    public UserCreationDTO(String login, String mail, String password, String firstName, String lastName) {
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
