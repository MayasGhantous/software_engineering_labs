package il.cshaifasweng.OCSFMediatorExample.server;

import com.mysql.cj.xdevapi.Client;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;import java.util.List;

public class SimpleServer extends AbstractServer {
	private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();
	private static SessionFactory sessionFactory = getSessionFactory("213461692");



	public static SessionFactory getSessionFactory(String Password) throws
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
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public SimpleServer(int port) {
		super(port);
		
	}
	private static List<Movie> getAllMovies() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}
	private void remove_movie (Movie movie) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(movie);
		session.getTransaction().commit();
		session.close();

	}
	private void insert_movie (Movie movie) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(movie);
		session.getTransaction().commit();
		session.close();

	}
	private void update_movie (Movie movie) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(movie);
		session.getTransaction().commit();
		session.close();

	}
	private List<Movie> get_movies_by_name(String name) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		Root<Movie> root =  query.from(Movie.class);
		Predicate makePredicate = builder.like(root.get("movie_name"), "%"+name+"%");
		query.select(root).where(makePredicate);
		List<Movie> data = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("I got your message");
		Message message = (Message) msg;
		String request = message.getMessage();

		try {
			if(message.getId()==0){
				List<Movie> movies = getAllMovies();
				message.setObject(movies);
				message.setMessage("Success, go to main page");
				client.sendToClient(message);
			}
			else if (message.getMessage().startsWith("#DeleteMovie")){
				Movie movie = (Movie)message.getObject();
				remove_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				client.sendToClient(message);
			}
			else if (message.getMessage().startsWith("#GoToScreenings"))
			{
				Movie movie = (Movie)message.getObject();
				message.setObject(movie.getScreenings());
				message.setMessage("#ScreeningsGot");
				client.sendToClient(message);

			}
			else if (message.getMessage().startsWith("#InsertMovie"))
			{
				Movie movie = (Movie)message.getObject();
				insert_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				client.sendToClient(message);
			}
			else if (message.getMessage().startsWith("#UpdateMovie"))
			{
				Movie movie = (Movie)message.getObject();
				update_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				client.sendToClient(message);
			}
			else if (message.getMessage().startsWith("#SearchMovies"))
			{
				String movieName = (String)message.getObject();
				message.setObject(get_movies_by_name(movieName));
				message.setMessage("#UpdateMovieList");
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
