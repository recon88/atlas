package net.leaguecom.atlas.module;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.pircbotx.output.OutputUser;

public class OpModule implements Module {
	public void execute(String cmd, String txt, GenericMessageEvent<PircBotX> event) {
		PircBotX bot = event.getBot();
		User u = bot.getUserChannelDao().getUser(txt);
		char modifier = '-';
		switch (cmd) {
		case "op":
			modifier = '+';
		case "deop":
			modifier = '-';
		}

		OutputUser out = bot.getUserChannelDao().getUser("Chanserv").send();
		for(Channel c : bot.getUserBot().getChannelsOpIn()) {
			out.message(String.format("FLAGS %s %s %cO", c.getName(), u.getNick(), modifier));
		}
	}

	public String help(String cmd) {
		switch (cmd) {
		case "op":
			return "Give +O to a user in all channels the bot is operator in.";
		case "deop":
			return "Remove +O from a user in all channels the bot is op in.";
		default:
			return String.format("Unknown command: %s", cmd);
		}
	}
}
