package snippetlab.java.hibernate.one2many_bidirectional_retrieve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
	public static void
		main( String[] args )
	{
		// create session factory
		/**
		 * [Trouble shooting] could not locate cfg.xml for hibernate
		 *
		 * "/home/hyde/ssd/atelier/snippetLab/java/hibernate/general/src/hibernate.cfg.xml" can't be used because
		 * because of configure() method waits for class path resource (loaded by a classloader) or URL.
		 *
		 * Refer to https://stackoverflow.com/questions/47069676/could-not-locate-cfg-xml-for-hibernate
		 */
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int theId;
			Instructor instructor;

			// insert an instructor data
			session.beginTransaction();

			instructor = new Instructor();
			instructor.setFirstName("Hyde");
			instructor.setLastName("Liao");

			session.save(instructor);

			theId = instructor.getId();

			System.out.println("=================== Before insert courses");
			System.out.println("=================== The instructor id: " + theId);
			System.out.println("=================== The number of courses: " + instructor.getCourses().size());

			session.getTransaction().commit();

			// create courses
			session = factory.getCurrentSession();
			session.beginTransaction();

			instructor = session.get(Instructor.class, theId);

			Course tempCourse1 = new Course("Guitar");
			Course tempCourse2 = new Course("Bass");

			instructor.add(tempCourse1);
			instructor.add(tempCourse2);

			session.save(tempCourse1);
			session.save(tempCourse2);

			session.getTransaction().commit();

			// get all courses for the specified instructor
			session = factory.getCurrentSession();
			session.beginTransaction();

			instructor = session.get(Instructor.class, theId);

			System.out.println("=================== After insert courses");
			for(Course course : instructor.getCourses())
			{
				System.out.println("=================== The courses: " + course);
			}

			session.getTransaction().commit();
			System.out.println("Done!!!!!!!!!!!!!!!!!!!!!!!!!1");
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
