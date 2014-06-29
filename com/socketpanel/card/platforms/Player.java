package com.socketpanel.card.platforms;

import java.util.UUID;

import com.synload.framework.ws.WSHandler;

public class Player {
	public WSHandler user = null;
	public int status = 1;
	public String groupName, uuid = "";
	public double x,y,z = 0;
	public Player(WSHandler user){
		this.user = user;
		uuid = UUID.randomUUID().toString();
	}
	public WSHandler getUser() {
		return user;
	}
	public void setUser(WSHandler user) {
		this.user = user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void leaveGroup(){
		if(Multiplayer.groups.containsKey(groupName)){
			Multiplayer.groups.get(groupName).removePlayer(Multiplayer.players.get(user));
		}else{
			Multiplayer.players.remove(user);
		}
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
