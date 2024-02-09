package biz.opengate.multiplayer.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private ConcurrentHashMap<String,String> data = new ConcurrentHashMap<String,String>();
    private ConcurrentHashMap<String,String> players = new ConcurrentHashMap<String,String>();
    
    private String id;
    private Date createOn = new Date();
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	// return the password or null if player exist
    public String registerPlayer(String name) {
    	if (players.values().contains(name)) {
    		return null;
    	}
    	String secure = UUID.randomUUID().toString();    	
    	players.put(secure,name);
    	return secure;    	
    }
    
    public String put(String secret, String key, String value) {
    	String userName = players.get(secret);
    	if (userName==null) return null;
    	String userKey = "p." + userName + "." + key;
    	data.put(userKey, value);    	
    	return userKey + ":" + value;
    }
    
    public void put(String key, String value) {
    	data.put(key, value);
    }
    
    public String dump() {
    	StringBuffer b = new StringBuffer();
    	for(Map.Entry<String,String> e:data.entrySet()) {
    		b.append(e.getKey());
    		b.append(':');
    		b.append(e.getValue());
    		b.append('\n');
    	}
    	return b.toString();
    }

}
