package codesnippet.prototype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codesnippet.prototype.persistence.dao.CustomerDao;
import codesnippet.prototype.persistence.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public List<Customer> getCustomers(int theSortField) {
        return this.customerDao.getCustomers(theSortField);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        this.customerDao.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int customerId) {
        return this.customerDao.getCustomer(customerId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int customerId) {
        this.customerDao.deleteCustomer(customerId);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return this.customerDao.searchCustomers(theSearchName);
    }
}
