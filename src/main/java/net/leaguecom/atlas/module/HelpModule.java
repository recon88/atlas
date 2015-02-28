package net.leaguecom.atlas.module;

import java.util.Map;
import java.util.Map.Entry;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class HelpModule implements Module {
	public void execute(String cmd, String txt, GenericMessageEvent<PircBotX> event) {
		Map<String, Module> modMap = ModuleManager.getInstance().getModuleMap();

		for(Entry<String, Module> e : modMap.entrySet()) {
			Module mod = e.getValue();
			cmd = e.getKey();
			event.respond(String.format("!%s\t-\t%s", cmd, mod.help(cmd)));
		}
	}

	public String help(String cmd) {
		return "Prints this help text.";
	}
}
