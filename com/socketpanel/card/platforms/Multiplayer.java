package com.socketpanel.card.platforms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.synload.eventsystem.Addon;
import com.synload.eventsystem.HandlerRegistry;
import com.synload.framework.SynloadFramework;
import com.synload.framework.ws.WSHandler;
import com.synload.framework.ws.WSRequest;

public class Multiplayer extends Addon {
	public static Hashtable<WSHandler, Player> players = new Hashtable<WSHandler, Player>();
	public static Hashtable<String, Group> groups = new Hashtable<String, Group>();
	public void init(){
		HandlerRegistry.register(Listener.class);
		List<String> flags = new ArrayList<String>();
		SynloadFramework.registerElement(new WSRequest("join","get"), Page.class, "join", flags);
		SynloadFramework.registerElement(new WSRequest("position","get"), Page.class, "position", flags);
		SynloadFramework.registerElement(new WSRequest("dead","get"), Page.class, "dead", flags);
		SynloadFramework.registerElement(new WSRequest("start","get"), Page.class, "start", flags);
	}
}