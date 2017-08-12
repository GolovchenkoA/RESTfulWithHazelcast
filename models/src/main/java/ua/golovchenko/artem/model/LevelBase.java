package ua.golovchenko.artem.model;

/**
 * Created by головченко on 04.08.2017.
 *
 * Base class implemented Level-interface
 *
 * @author Golovchenko Artem
 * @see ua.golovchenko.artem.model.Level
 */
public class LevelBase implements Level {
    private Integer id;
    private String description;

    public LevelBase(){}
    public LevelBase(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
