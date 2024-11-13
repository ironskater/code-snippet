package codesnippet.prototype.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codesnippet.prototype.persistence.entity.Customer;
import codesnippet.prototype.utils.SortUtils;

@Repository
public class CustomerDaoImpl implements CustomerDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(int theSortField) {
        // get hibernate session
        Session session = this.sessionFactory.getCurrentSession();

        // determine sort field
        String theFieldName = null;

		switch (theSortField) {
			case SortUtils.FIRST_NAME:
				theFieldName = "first_name";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "last_name";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				theFieldName = "last_name";
		}

        // create a query
        String sql = "SELECT * FROM customer ORDER BY " + theFieldName + " ASC";
        Query<Customer> query = session.createNativeQuery(sql, Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        // return result list
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Customer.class, customerId);
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Customer> query = session.createNativeQuery("DELETE FROM customer WHERE id = :id", Customer.class);
        query.setParameter("id", customerId);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if(theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createNativeQuery(
                            "SELECT * FROM customer WHERE first_name ILIKE :theName OR last_name LIKE :theName",
                            Customer.class);
            theQuery.setParameter(  "theName",
                                    "%" + theSearchName + "%");
        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createNativeQuery(
                            "SELECT * FROM customer ORDER BY last_name ASC",
                            Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}
