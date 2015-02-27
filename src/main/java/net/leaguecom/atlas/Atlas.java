package net.leaguecom.atlas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
				.setServer(config.getProperty("host"), Integer.parseInt(config.getProperty("port")));
		
		String[] channels = config.getProperty("channels").split(",");
		for(int i = 0; i < channels.length; i++) {
			builder.addAutoJoinChannel(channels[i]);
		}
		
		boolean useSSL = Boolean.parseBoolean(config.getProperty("ssl"));
		
		if(useSSL) {
			builder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());
		}

		PircBotX bot = new PircBotX(builder.buildConfiguration());
		bot.startBot();
	}
}
