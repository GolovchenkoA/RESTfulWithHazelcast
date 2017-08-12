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
    private Long id;
    private String result;
    private Long userId;
    private Integer levelId;

    public ResultBase(){}
    public ResultBase(String result, Long userId, Integer levelId) {
        this.result = result;
        this.userId = userId;
        this.levelId = levelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

}
