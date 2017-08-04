package ua.golovchenko.artem.model;

/**
 * Game level class
 *
 * Created by головченко on 04.08.2017.
 *
 * @author Golovchenko Artem
 */
public interface Level {

    /**
     * @return level id as {@code Long}
     */

    public Integer getId();

    /**
     * Sets the level id
     * @param id Long
     */

    public void setId(Integer id);

    /**
     * @return level description as {@code String}
     */

    public String getDescription();

    /**
     * Sets description of Game level
     * @param description String
     */

    public void setDescription(String description);
}
