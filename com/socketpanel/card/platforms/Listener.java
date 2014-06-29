package com.socketpanel.card.platforms;

import java.util.HashMap;

import com.synload.eventsystem.EventHandler;
import com.synload.eventsystem.events.CloseEvent;
import com.synload.eventsystem.events.ConnectEvent;

public class Listener {
	@EventHandler
	public void disconnect(CloseEvent e){
		Player p = Multiplayer.players.get(e.getSession());
		Group g = Multiplayer.groups.get(p.getGroupName());
		HashMap<String, String> playerData = new HashMap<String, String>();
		playerData.put("id", p.getUuid());
		playerData.put("event", "left");
		g.send(playerData);
		Multiplayer.players.get(e.getSession()).leaveGroup();
		
	}
	@EventHandler
	public void connect(ConnectEvent e){
		Multiplayer.players.put(e.getSession(),new Player(e.getSession()));
	}
}
