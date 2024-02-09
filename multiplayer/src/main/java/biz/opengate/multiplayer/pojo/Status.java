package biz.opengate.multiplayer.pojo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Status {
    private static volatile Status  instance = null;

    private Status () {}
    
    private ConcurrentHashMap<String,Game> data = new ConcurrentHashMap<String,Game>(); 
    
    public static Status getInstance() {
        if (instance == null) {
            synchronized(Status.class) {
                if (instance == null) {
                    instance = new Status();
                }
            }
        }
        return instance;
    }
    
    public Game createNewGame() {
    	Game g = new Game();
    	g.setId(UUID.randomUUID().toString());
    	data.put(g.getId(), g);
    	return g;
    }
    
    public Game getGame(String id) {
    	return data.get(id);
    }
}
