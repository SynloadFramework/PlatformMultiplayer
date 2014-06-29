package com.socketpanel.card.platforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socketpanel.card.platforms.elements.Join;
import com.synload.framework.handlers.Request;
import com.synload.framework.ws.WSHandler;

public class Page {
	public void join(WSHandler user, Request request) throws JsonProcessingException{
		user.send(user.ow.writeValueAsString(new Join( user, request.getData().get("groupname") )));
	}
	public void position(WSHandler user, Request request) throws JsonProcessingException{
		Player p = Multiplayer.players.get(user);
		p.setX(Double.valueOf(request.getData().get("x")));
		p.setY(Double.valueOf(request.getData().get("y")));
		p.setZ(Double.valueOf(request.getData().get("z")));
		if(Multiplayer.groups.containsKey(p.getGroupName())){
			Group g = Multiplayer.groups.get(p.getGroupName());
			HashMap<String, String> playerData = new HashMap<String, String>();
			playerData.put("id", p.getUuid());
			playerData.put("pos", p.getX() + "," + p.getY() + "," + p.getZ());
			g.send(playerData, p);
		}
	}
	public void dead(WSHandler user, Request request) throws JsonProcessingException{
		Player p = Multiplayer.players.get(user);
		p.setStatus(2);
		if(Multiplayer.groups.containsKey(p.getGroupName())){
			Group g = Multiplayer.groups.get(p.getGroupName());
			HashMap<String, String> playerData = new HashMap<String, String>();
			playerData.put("id", p.getUuid());
			playerData.put("status", String.valueOf(p.getStatus()));
			g.send(playerData, p);
			boolean all = true;
			List<Player> stillAlive = new ArrayList<Player>(); 
			for(Player play : g.getPlayers()){
				if(play.getStatus()==1){
					all = false;
					stillAlive.add(play);
				}
			}
			if(stillAlive.size()==1){
				playerData = new HashMap<String, String>();
				playerData.put("event", "winner");
				playerData.put("name", stillAlive.get(0).getUuid());
				g.send(playerData);
			}
			if(all){
				playerData = new HashMap<String, String>();
				playerData.put("event", "countdown");
				g.send(playerData, 2);
				for(Player play : g.getPlayers()){
					play.setStatus(3);
				}
			}
		}
	}
	public void start(WSHandler user, Request request) throws JsonProcessingException{
		Player p = Multiplayer.players.get(user);
		if(p.getStatus()!=1){
			p.setStatus(1);
			if(Multiplayer.groups.containsKey(p.getGroupName())){
				Group g = Multiplayer.groups.get(p.getGroupName());
				HashMap<String, String> playerData = new HashMap<String, String>();
				playerData.put("id", p.getUuid());
				playerData.put("status", String.valueOf(p.getStatus()));
				g.send(playerData, p);
				boolean all = true;
				for(Player play : g.getPlayers()){
					if(play.getStatus()!=1 && play.getStatus()!=0){
						all = false;
					}
				}
				if(all){
					playerData = new HashMap<String, String>();
					playerData.put("event", "start");
					g.send(playerData);
					
				}
			}
		}
	}
}
