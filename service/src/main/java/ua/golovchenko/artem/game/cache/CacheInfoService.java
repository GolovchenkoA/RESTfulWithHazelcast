package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.dao.CacheInfoDAO;
import ua.golovchenko.artem.game.dao.InfoDAO;
import ua.golovchenko.artem.game.service.InfoService;
import ua.golovchenko.artem.model.Info;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheInfoService implements InfoService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    InfoDAO infoDAO = new CacheInfoDAO();


    @Override
    public void add(Info item) throws Exception{

        try{
            infoDAO.add(item);
        } catch (Exception e) {
            logger.info("Error add new Info item: {}", e);
            throw new Exception("Error add new Info item");

        }
    }
}
