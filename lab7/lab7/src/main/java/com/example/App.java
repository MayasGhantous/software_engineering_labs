package com.example;
import javax.persistence.criteria.*;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App
{

    private static Session session;

    private static SessionFactory getSessionFactory(String UserName ,String Password) throws
            HibernateException {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.username",UserName);
        configuration.setProperty("hibernate.connection.password",Password);

        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Human.class);
        configuration.addAnnotatedClass(Carage.class);
        configuration.addAnnotatedClass(Picture.class);


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

    private static void generateCars() throws Exception {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Picture picture = new Picture("picture"+i);
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 +
                    random.nextInt(19));
            session.save(car);
            session.save(picture);
            /*
             * The call to session.flush() updates the DB immediately
without ending the transaction.
             * Recommended to do after an arbitrary unit of work.
             * MANDATORY to do if you are saving a large amount of data -
otherwise you may get cache errors.
            */
            session.flush();
        }
    }
    private static void generateHumans() throws Exception {
        Random random = new Random();
        int how_many_people = 8;
        for (int i = 0; i < how_many_people; i++) {
            Human human = new Human(""+(char)(65+i),""+(char)(65+how_many_people+i),"1"+(char)(65+i)+"2"+(char)(65+how_many_people+i),""+(char)(65+i)+""+(char)(65+how_many_people+i)+"gmail.com");
            session.save(human);
            /*
             * The call to session.flush() updates the DB immediately
without ending the transaction.
             * Recommended to do after an arbitrary unit of work.
             * MANDATORY to do if you are saving a large amount of data -
otherwise you may get cache errors.
            */
            session.flush();
        }
    }
    private static void generateGarages() throws Exception {
        Carage garage = new Carage("Sakhnin Maryousef 40","053-9822777");
        session.save(garage);
        session.flush();
        garage = new Carage("Haifa derchalnby 71","053-8800777");
        session.save(garage);
        session.flush();
    }

    private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);

        query.from(Car.class);
        List<Car> data = session.createQuery(query).getResultList();
        return data;
    }
    private static List<Human> getAllHumans() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Human> query = builder.createQuery(Human.class);
        query.from(Human.class);
        List<Human> data = session.createQuery(query).getResultList();
        return data;
    }
    private static List<Picture> getAllPictures() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Picture> query = builder.createQuery(Picture.class);
        query.from(Picture.class);
        List<Picture> data = session.createQuery(query).getResultList();
        return data;
    }
    private static List<Carage> getAllGarages() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Carage> query = builder.createQuery(Carage.class);
        query.from(Carage.class);
        List<Carage> data = session.createQuery(query).getResultList();
        return data;

    }
    private static void create_relations() throws Exception {
        Random random = new Random();
        List<Car> cars = getAllCars();
        List<Human> humans = getAllHumans();
        List<Picture> pictures = getAllPictures();
        List<Carage> garages = getAllGarages();
        Collections.shuffle(cars);
        Collections.shuffle(humans);
        Collections.shuffle(pictures);
        Collections.shuffle(garages);
        int i = 0;
        Transaction transaction = null;
        for (Car car : cars) {

            car.setHuman(humans.get(random.nextInt(humans.size())));
            car.setPicture(pictures.get(i));
            for(int j =0 ; j<garages.size();j++) {

                Boolean random_bool = random.nextBoolean();
                if (random_bool) {
                    List<Carage> current_Garages = car.getGarages();
                    current_Garages.add(garages.get(j));
                    car.setGarages(current_Garages);
                    List<Car> Current_cars = garages.get(j).getCars();
                    Current_cars.add(car);
                    garages.get(j).setCars(Current_cars);
                    //session.update(garages.get(j));
                }
            }
            session.update(car);
            session.flush();
            i++;
        }
        for (Human human : humans) {
            for (Carage garage : garages) {
                Boolean random_bool = random.nextBoolean();
                if (random_bool) {
                    List<Carage> current_garages = human.getGarages();
                    current_garages.add(garage);
                    human.setGarages(current_garages);
                    List<Human> current_humans = garage.getHumans();
                    current_humans.add(human);
                    garage.setHumans(current_humans);
                    session.update(garage);
                }
            }
            session.update(human);
            session.flush();
        }

    }
    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }
    private static void Section4_1() throws Exception{
        List<Carage> garages = getAllGarages();
        for (Carage garage : garages) {
            System.out.print("Garage_id: " + garage.getGarage_id());
            System.out.print(", location: "+garage.getLocation());
            System.out.print(", phone_number: "+ garage.getPhone_Number());
            System.out.print('\n');
            for (int i  = 0;i<garage.getCars().size();i++) {
                Car car = garage.getCars().get(i);
                System.out.print("  car_licensePlate: "+car.getLicensePlate());
                System.out.print("\n");
            }
        }
    }

    private static void Section4_2() throws Exception{
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Car_Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print(", picture_name: "+car.getPicture().getPicture_name()+"\n");
            for(Carage garage : car.getGarages()) {
                System.out.print("  location: "+garage.getLocation()+", Humans:\n");
                for (Human human : garage.getHumans()) {
                    System.out.print("      human_id: "+human.getId());
                    System.out.print(", First_name: "+human.getFirstName());
                    System.out.print(", Last_name: "+human.getLastName());
                    System.out.print(", Email: "+human.getEmail());
                    System.out.print(", Password: "+human.getPassword());
                    System.out.print("\n");
                }

                System.out.print('\n');

            }

        }
    }

    public static void main( String[] args ) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter User Name:\n");
            String User_name = scanner.nextLine();
            System.out.print("Enter Password\n");
            String Password = scanner.nextLine();


            SessionFactory sessionFactory = getSessionFactory(User_name,Password);
            session = sessionFactory.openSession();
            session.beginTransaction();


            generateCars();
            generateHumans();
            generateGarages();
            create_relations();
            List<Car>currrent = getAllCars();
            Section4_1();
            Section4_2();


            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
                    exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}
