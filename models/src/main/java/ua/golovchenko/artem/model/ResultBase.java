package ua.golovchenko.artem.model;

/**
 * Created by головченко on 04.08.2017.
 *
 * Base class implemented Result-interface
 *
 * @author Golovchenko Artem
 * @see ua.golovchenko.artem.model.Result
 */
public class ResultBase implements Result {
    private Long user_id;
    private Integer level_id;
    private Long result;

    public ResultBase(){}

    public ResultBase(Long result, Long user_id, Integer level_id) {
        this.level_id = level_id;
        this.user_id = user_id;
        this.result = result;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }
}
