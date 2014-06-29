package com.socketpanel.card.platforms;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Group {
	public String name = "";
	public int status = 0;
	public List<Player> players = new ArrayList<Player>();
	public Group(String name, Player firstPlayer){
		this.name = name;
		players.add(firstPlayer);
		firstPlayer.setGroupName(name);
	}
	public void addPlayer(Player p){
		players.add(p);
		p.setGroupName(name);
	}
	public void removePlayer(Player p){
		players.remove(p);
		Multiplayer.players.remove(p.getUser());
		if(players.size()==0){
			Multiplayer.groups.remove(name);
		}
	}
	public void send(Object o, Player from){
		for(Player p: players){
			if(p!=from){
				try {
					p.getUser().send(p.getUser().ow.writeValueAsString(o));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void send(Object o, int status){
		for(Player p: players){
			if(p.getStatus()==status){
				try {
					p.getUser().send(p.getUser().ow.writeValueAsString(o));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void send(Object o){
		for(Player p: players){
			try {
				p.getUser().send(p.getUser().ow.writeValueAsString(o));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
