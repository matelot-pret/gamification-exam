package app.brio.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Préciser la strategie pour éviter que Hibernate n'ai a le deviner même s'il le fait selon la version
    private Long id;//UUID plus lourd que long ou int mais moins predictible et assure l'unicité de l'id

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String hashPassword;

    private String firstName;
    private String lastName;
    private LocalDateTime dateCreation;

    public User(){
        this.dateCreation = LocalDateTime.now();
    }

    public User(Long id, String login, String mail, String hashPassword, String firstName, String lastName) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreation = LocalDateTime.now();
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

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, mail);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
