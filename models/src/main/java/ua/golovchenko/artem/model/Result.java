package ua.golovchenko.artem.model;

/**
 *
 * Created by головченко on 04.08.2017.
 *
 * Class implemented Result-interface stores the result of the user and the level at which the result was obtained
 * @author Golovchenko Artem
 */
public interface Result extends Comparable {

    /**
     * @return String user result on the level {@code String}
     */

    public Integer getResult() ;

    /**
     * Sets user result on the level
     * @param result String
     */

    public void setResult(Integer result);

    /**
     * @return user id as {@code Long}
     */

    public Long getUser_id();

    /**
     * Sets the user id
     * @param userId Long
     */

    public void setUser_id(Long userId);

    /**
     * @return level id as {@code Long}
     */

    public Integer getLevel_id();

    /**
     * Sets the level id
     * @param levelId Long
     */

    public void setLevel_id(Integer levelId);

}
