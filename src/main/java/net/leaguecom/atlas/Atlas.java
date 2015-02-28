package net.leaguecom.atlas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import net.leaguecom.atlas.command.ModuleManager;
import net.leaguecom.atlas.command.HelpModule;
import net.leaguecom.atlas.listener.CommandListener;

import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

public class Atlas {
	public static void main(String[] args) throws IOException, IrcException {
		Properties config = new Properties();
		config.load(new FileReader("config.ini"));
		
		Builder<PircBotX> builder = new Configuration.Builder<PircBotX>()
				.setName(config.getProperty("nick"))
				.setServer(config.getProperty("host"), Integer.parseInt(config.getProperty("port")))
				.addListener(new CommandListener());
		
		String[] channels = config.getProperty("channels").split(",");
		for(String channel : channels) {
			builder.addAutoJoinChannel(channel);
		}
		
		boolean useSSL = Boolean.parseBoolean(config.getProperty("ssl"));
		
		if(useSSL) {
			builder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());
		}
		
		initCommands();

		PircBotX bot = new PircBotX(builder.buildConfiguration());
		bot.startBot();
	}

	private static void initCommands() {
		ModuleManager cmdMan = ModuleManager.getInstance();
		
		cmdMan.registerCommand("help", new HelpModule());
	}
}
