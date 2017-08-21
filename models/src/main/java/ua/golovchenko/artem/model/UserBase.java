package ua.golovchenko.artem.model;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by головченко on 04.08.2017.
 *
 * Basic user
 *
 * @author Golovchenko Artem
 * @see ua.golovchenko.artem.model.User
 */

@XmlRootElement
public class UserBase implements User, Serializable{

    @NotNull(message = "user_id is blank")
    @Digits(integer = 6, fraction = 1)
    private Long user_id;

    //@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Pattern(regexp="[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    private String email;

    @NotNull(message = "name is blank")
    @Size(min=3, max=25,message="Name must be at least 6 characters long and maximum 25 characters.")
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="Username must be alphanumeric with no spaces. Max 25 characters")
    private String name;

    @NotNull
    @Size(min=3, max=25,
            message="Nick must be at least 6 characters long and maximum 25 characters.")
    private String nick;

    public UserBase(){};

    public UserBase(String email, String name, String nick) {
        this.email = email;
        this.name = name;
        this.nick = nick;
    }

    public Long getUser_id() {return user_id;}

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

}
