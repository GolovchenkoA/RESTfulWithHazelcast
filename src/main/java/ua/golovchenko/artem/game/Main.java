package ua.golovchenko.artem.game;

/**
 * Created by Artem on 07.08.2017.
 */
public class Main {
    //public static final String CACHE_SERVER_PORT;


    public static void main(String[] args) throws Exception {

        Context.init("game.xml");

        if(Context.getCacheServer() != null){
            //Context.getCacheServer().start();
        }

        if(Context.getWebApiServer() != null){
            Context.getWebApiServer().start();
        }



        //Context.getDataManager().initCacheServer();
        System.out.println(Context.getConfig());

        /*Context.getServerManager().start();*/
    }
}
