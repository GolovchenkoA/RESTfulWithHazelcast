package ua.golovchenko.artem.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by головченко on 04.08.2017.
 *
 * Basic user
 *
 * @author Golovchenko Artem
 * @see ua.golovchenko.artem.model.User
 */

public class UserBase implements User, Serializable{
    private Long user_id;
    private String email;
    private String name;
    private String nick;
    private List<Result> results = new ArrayList<>();

    public UserBase(){};

    public UserBase(String email, String name, String nick) {
        this.email = email;
        this.name = name;
        this.nick = nick;
    }

    public UserBase(Long id, String email, String name, String nick) {
        this.user_id = id;
        this.email = email;
        this.name = name;
        this.nick = nick;
    }

    @Override
    public Long getUser_id() {return user_id;}

    @Override
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public List<Result> getResults() {
        return results;
    }

    @Override
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBase userBase = (UserBase) o;

        if (!email.equals(userBase.email)) return false;
        if (!name.equals(userBase.name)) return false;
        if (nick != null ? !nick.equals(userBase.nick) : userBase.nick != null) return false;
        if (!results.equals(userBase.results)) return false;
        if (!user_id.equals(userBase.user_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + results.hashCode();
        return result;
    }
}
