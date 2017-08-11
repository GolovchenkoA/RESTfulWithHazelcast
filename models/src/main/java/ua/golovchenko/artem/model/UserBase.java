package ua.golovchenko.artem.model;


import java.io.Serializable;

/**
 * Created by головченко on 04.08.2017.
 *
 * Basic user
 *
 * @author Golovchenko Artem
 * @see ua.golovchenko.artem.model.User
 */
public class UserBase implements User, Serializable{
    private Long id;
    private String email;
    private String name;
    private String nick;

    public UserBase(){};

    public UserBase(String email, String name, String nick) {
        this.email = email;
        this.name = name;
        this.nick = nick;
    }

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

}
