package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer extends AbstractServer {
	private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();
	private static Session session;

	private static SessionFactory getSessionFactory(String Password) throws
			HibernateException {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.password",Password);

		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Screening.class);


		ServiceRegistry serviceRegistry = new
				StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		try {
			return configuration.buildSessionFactory(serviceRegistry);
		}
		catch (Exception e) {
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		}
		System.out.println("SessionFactory created");
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public SimpleServer(int port) {
		super(port);
		
	}
	private static List<Movie> getAllMovies() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = session.createQuery(query).getResultList();
		return data;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message) msg;
		String request = message.getMessage();

		try {
			if(message.getId()==0){
				SessionFactory sessionFactory = getSessionFactory("janthecsstudent");
				session = sessionFactory.openSession();
				//session.beginTransaction();
				System.out.println("Mr7aba");
				message.setMovies(getAllMovies());
                message.setMessage("Success, go to main page");
				System.out.println("Success, go to main page");
				client.sendToClient(message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public void sendToAllClients(Message message) {
		try {
			for (SubscribedClient SubscribedClient : SubscribersList) {
				SubscribedClient.getClient().sendToClient(message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
