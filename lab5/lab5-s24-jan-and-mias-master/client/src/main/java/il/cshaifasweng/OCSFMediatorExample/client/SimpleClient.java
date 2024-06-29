package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.io.IOException;


public class SimpleClient extends AbstractClient {
	public static Message Current_Message;
	private static SimpleClient client = null;

	public SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("I got your message");
		Message message = (Message) msg;
		if(message.getMessage().equals("Success, go to main page")){
			System.out.println("here");
			Current_Message = message;

			Platform.runLater(() -> {
				SimpleChatClient.setWindowTitle("editing_details");
				try {
					SimpleChatClient.setRoot("Movie_editing_details");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
        }
		else if (message.getMessage().equals("#UpdateMovieList")){
			if(SimpleChatClient.getWindowTitle().equals("editing_details"))
			{
				Current_Message = message;
				Platform.runLater(() -> {
					SimpleChatClient.setWindowTitle("editing_details");
					try {
						SimpleChatClient.setRoot("Movie_editing_details");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			}

		}
		else if (message.getMessage().equals("#ScreeningsGot")){
			Current_Message = message;
			Platform.runLater(() -> {
				SimpleChatClient.setWindowTitle("edit_screenings");
				try {
					SimpleChatClient.setRoot("EditScreening");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else {
			EventBus.getDefault().post(new MessageEvent(message));
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
