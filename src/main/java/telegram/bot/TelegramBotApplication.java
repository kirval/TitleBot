package telegram.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TelegramBotApplication {
	@Autowired
	private TelegramBot telegramBot;

	static {
		ApiContextInitializer.init();
	}

	@PostConstruct
	public void init(){
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(telegramBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

	}
}
