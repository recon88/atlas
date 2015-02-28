package net.leaguecom.atlas.module;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericMessageEvent;

public interface Module {
	public void execute(String cmd, String txt, GenericMessageEvent<PircBotX> event);
	public String help(String cmd);
}
