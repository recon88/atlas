package net.leaguecom.atlas.listener;

import net.leaguecom.atlas.command.ModuleManager;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class CommandListener extends ListenerAdapter<PircBotX> {
	@Override
	public void onGenericMessage(GenericMessageEvent<PircBotX> event)
			throws Exception {
		String msg = event.getMessage();

		if (msg.startsWith("!")) {
			String[] split = msg.substring(1).split(" ", 2);
			ModuleManager.getInstance().execute(split[0],
					split.length == 2 ? split[1] : null, event);
		}
	}
}
