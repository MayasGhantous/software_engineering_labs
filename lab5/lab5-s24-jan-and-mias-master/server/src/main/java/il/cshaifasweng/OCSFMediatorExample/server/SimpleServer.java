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
	private void add_new_screening(Screening screening) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(screening);
		session.getTransaction().commit();
		session.close();
	}
	private List<Screening> get_screening_for_movie(Movie movie)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Screening> query = builder.createQuery(Screening.class);
		Root<Screening> root =  query.from(Screening.class);
		query.select(root).where(builder.equal(root.get("movie"), movie));
		List<Screening> data = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}
	private Screening get_screening(int screening_id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Screening> query = builder.createQuery(Screening.class);
		Root<Screening> root =  query.from(Screening.class);
		query.select(root).where(builder.equal(root.get("auto_number_screening"), screening_id));
		Screening data = session.createQuery(query).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return data;


	}
	private void remove_screening(Screening screening) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(screening);
		session.getTransaction().commit();
		session.close();
	}
	private List<Screening> search_sreening_branch_and_movie(String branch,Movie movie) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Screening> query = builder.createQuery(Screening.class);
		Root<Screening> root =  query.from(Screening.class);
		Predicate predicate_branch =  builder.like(root.get("branch"), "%"+branch+"%");
		Predicate predicate_movie =  builder.equal(root.get("movie"), movie);
		query.select(root).where(builder.and(predicate_branch, predicate_movie));

		List<Screening> data = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}
	private void update_screening(Screening screening)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(screening);
		session.getTransaction().commit();
		session.close();


	}
	private void update_all_prices(int new_price) throws Exception
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Movie> movies = getAllMovies();
		for (Movie movie : movies)
		{
			movie.setPrice(new_price);
			session.update(movie);

		}
		session.getTransaction().commit();
		session.close();

	}
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message) msg;
		String request = message.getMessage();

		try {
			if(message.getId()==0){
				SubscribedClient connection = new SubscribedClient(client);
				SubscribersList.add(connection);
				List<Movie> movies = getAllMovies();
				message.setObject(movies);
				message.setMessage("Success, go to main page");
				client.sendToClient(message);
			}
			else if (message.getMessage().equals("#DeleteMovie")){
				Movie movie = (Movie)message.getObject();
				remove_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				sendToAllClients(message);
			}
			else if (message.getMessage().equals("#GoToScreenings"))
			{
				Movie movie = (Movie)message.getObject();
				System.out.println("screening number");
				System.out.println(movie.getScreenings().size());
				message.setObject(movie.getScreenings());
				message.setMessage("#ScreeningsGot");
				client.sendToClient(message);

			}
			else if (message.getMessage().equals("#InsertMovie"))
			{
				Movie movie = (Movie)message.getObject();
				insert_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				sendToAllClients(message);
				Message message1 = new Message(10,"#ChangeMovieIdBox");
				message1.setObject(movie);
				client.sendToClient(message1);
			}
			else if (message.getMessage().equals("#UpdateMovie"))
			{
				Movie movie = (Movie)message.getObject();
				update_movie(movie);
				message.setObject(getAllMovies());
				message.setMessage("#UpdateMovieList");
				sendToAllClients(message);
			}
			else if (message.getMessage().equals("#SearchMovies"))
			{
				String movieName = (String)message.getObject();
				message.setObject(get_movies_by_name(movieName));
				message.setMessage("#UpdateMovieList_Eatch");
				client.sendToClient(message);
			}
			else if(message.getMessage().equals("#AddNewScreening"))
			{
				Screening screening = (Screening)message.getObject();

				add_new_screening(screening);
				message.setMessage("#UpdateScreeningForMovie");
				message.setObject2(screening.getMovie());
				message.setObject(get_screening_for_movie(screening.getMovie()));

				sendToAllClients(message);
				Message message1 = new Message(20,"#UpdateBoxesInScreening");
				message1.setObject(screening);

				client.sendToClient(message1);
			}
			else if (message.getMessage().equals("#get_screening_from_id"))
			{
				int screening_id = (Integer)message.getObject();
				message.setObject(get_screening(screening_id));
				message.setMessage("#UpdateBoxesInScreening");
				client.sendToClient(message);
			}
			else if (message.getMessage().equals("#RemoveScreening")) {
				Movie movie = ((Screening)message.getObject()).getMovie();
				Screening screening =  (Screening)message.getObject();
				remove_screening(screening);
				message.setObject(get_screening_for_movie(movie));
				message.setObject2(movie);
				message.setMessage("#UpdateScreeningForMovie");
				sendToAllClients(message);
			}
			else if (message.getMessage().equals("#SearchBranchForScreening"))
			{
				Movie movie = (Movie) message.getObject();
				String Branch = (String)message.getObject2();
				List<Screening> screenings = search_sreening_branch_and_movie(Branch, movie);
				message.setObject(screenings);
				message.setObject2(movie);
				message.setMessage("#UpdateScreeningForMovie_each");
				client.sendToClient(message);
			}
			else if (message.getMessage().equals("#UpdateScreening"))
			{
				Movie movie = ((Screening) message.getObject()).getMovie();
				Screening screening =  (Screening)message.getObject();
				update_screening(screening);

				message.setObject(get_screening_for_movie(movie));
				message.setObject2(movie);
				message.setMessage("#UpdateScreeningForMovie");
				sendToAllClients(message);

			}
			else if (message.getMessage().equals("#ChangeAllPrices"))
			{
				int new_price = (int)message.getObject();
				update_all_prices(new_price);
				message.setMessage("#UpdateMovieList");
				message.setObject(getAllMovies());
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
