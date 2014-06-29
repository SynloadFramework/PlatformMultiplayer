package com.socketpanel.card.platforms.elements;

import com.socketpanel.card.platforms.Group;
import com.socketpanel.card.platforms.Multiplayer;
import com.synload.framework.handlers.Response;
import com.synload.framework.ws.WSHandler;

public class Join extends Response {
	public int status = 0;
	public Join(WSHandler user, String groupName){
		if(Multiplayer.groups.containsKey(groupName)){
			Multiplayer.groups.get(groupName).addPlayer(Multiplayer.players.get(user));
		}else{
			Multiplayer.groups.put(groupName, new Group(groupName, Multiplayer.players.get(user)));
		}
		status = Multiplayer.players.get(user).getStatus();
	}
}