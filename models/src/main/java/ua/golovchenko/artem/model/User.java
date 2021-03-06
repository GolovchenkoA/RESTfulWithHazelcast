package ua.golovchenko.artem.model;

import java.util.List;

/**
 * Created by головченко on 04.08.2017.
 *
 * @author Golovchenko Artem
 *
 */

public interface User {

    /**
     * @return user id as {@code Long}
     */

    public Long getUser_id();

    /**
     * Sets the user id
     * @param id Long
     */

    public void setUser_id(Long id);

    /**
     * @return user email as {@code String}
     */
    public String getEmail();

    /**
     * Sets the user email
     * @param email String  example: user@domain.com
     */
    public void setEmail(String email);

    /**
     * @return user name {@code String}
     */
    public String getName();

    /**
     * Sets the user name
     * @param name String  Alphabetical without spaces
     */
    public void setName(String name);

    /**
     * @return user nick {@code String}
     */
    public String getNick();

    /**
     * Sets the user id
     * @param nick String  Numbers and letters are allowed without spaces
     */
    public void setNick(String nick);

    public List<Result> getResults();

    public void setResults(List<Result> results);

}
