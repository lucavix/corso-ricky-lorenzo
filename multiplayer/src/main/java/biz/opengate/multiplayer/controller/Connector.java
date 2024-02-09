package biz.opengate.multiplayer.controller;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.opengate.multiplayer.pojo.Game;
import biz.opengate.multiplayer.pojo.Status;

@RestController
@RequestMapping("/server")
public class Connector {
	
	@GetMapping(path="/game")
	public ResponseEntity<String> createGame() {
		Status s = Status.getInstance();
		Game game = s.createNewGame();
		return new ResponseEntity<String>(game.getId(),HttpStatus.OK);
	}
	
	@GetMapping(path="/register/{gameId}/{playerName}")
	public ResponseEntity<String> registerPlayer(
			@PathVariable String gameId,
			@PathVariable String playerName
			) {
		Status s = Status.getInstance();
		Game game = s.getGame(gameId);
		if (game==null) {
			return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
		}		
		
		
		String secret = game.registerPlayer(playerName);
		if (secret==null) {
			return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
		}		
		return new ResponseEntity<String>(secret,HttpStatus.OK);
	}

	
	
	@PostMapping(path = "/exchange/{game}/{player}")
	public ResponseEntity<byte[]> exchange(
			@PathVariable String game,
			@PathVariable String player,
			@RequestBody String input) throws ParseException {
		
		
		Status s = Status.getInstance();
		
		Game g = s.getGame(game);
		if (game==null) {
			return new ResponseEntity<byte[]>("".getBytes(),HttpStatus.NOT_FOUND);
		}		
		
		String[] lines = input.split("\n");
		for(String line:lines) {
			String[] kv = line.split(":");
			String res = g.put(player,kv[0], kv[1]);
			if (res==null) {
				return new ResponseEntity<byte[]>("".getBytes(),HttpStatus.NOT_FOUND);
			}
			
		}
		
		return new ResponseEntity<byte[]>(g.dump().getBytes(), HttpStatus.OK);
		
	}
}
