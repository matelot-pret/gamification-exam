package app.brio.DTO;

import java.time.LocalDateTime;

public class UserResponseDTO {
    private Long id;
    private String login;
    private String mail;
    private String firstName;
    private String lastname;
    private LocalDateTime dateCreation;

    public UserResponseDTO(){}

    public UserResponseDTO(Long id, String login, String mail, String firstName, String lastname, LocalDateTime dateCreation) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.firstName = firstName;
        this.lastname = lastname;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
