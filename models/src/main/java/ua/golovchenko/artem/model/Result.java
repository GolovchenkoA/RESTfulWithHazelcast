package ua.golovchenko.artem.model;

/**
 *
 * Class implemented Result-interface stores the result of the user and the level at which the result was obtained
 * Created by головченко on 04.08.2017.
 */
public interface Result {

    /**
     * @return result  result id as {@code Long}
     */

    public Long getId();

    /**
     * Sets the result id
     * @param id Long
     */

    public void setId(Long id);

    /**
     * @return String user result on the level {@code String}
     */

    public String getResult() ;

    /**
     * Sets user result on the level
     * @param result String
     */

    public void setResult(String result);

    /**
     * @return user id as {@code Long}
     */

    public Long getUserId();

    /**
     * Sets the user id
     * @param userId Long
     */

    public void setUserId(Long userId);

    /**
     * @return level id as {@code Long}
     */

    public Integer getLevelId();

    /**
     * Sets the level id
     * @param levelId Long
     */

    public void setLevelId(Integer levelId);

}
