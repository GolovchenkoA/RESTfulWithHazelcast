package ua.golovchenko.artem.model;

import java.io.Serializable;

/**
 * Created by головченко on 04.08.2017.
 *
 * Base class implemented Result-interface
 *
 * @author Golovchenko Artem
 * @see Info
 */
public class InfoBase implements Info, Serializable {
    private Long user_id;
    private Integer level_id;
    private Integer result;

    public InfoBase(){}

    public InfoBase(Long user_id, Integer level_id, Integer result) {
        this.user_id = user_id;
        this.level_id = level_id;
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoBase infoBase = (InfoBase) o;

        if (!level_id.equals(infoBase.level_id)) return false;
        if (!result.equals(infoBase.result)) return false;
        if (!user_id.equals(infoBase.user_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = user_id.hashCode();
        result1 = 31 * result1 + level_id.hashCode();
        result1 = 31 * result1 + result.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return "InfoBase{" +
                "user_id=" + user_id +
                ", level_id=" + level_id +
                ", result=" + result +
                '}';
    }
}
