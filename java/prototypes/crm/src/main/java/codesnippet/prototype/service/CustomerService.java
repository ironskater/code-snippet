package codesnippet.prototype.service;

import java.util.List;

import codesnippet.prototype.persistence.entity.Customer;

public interface CustomerService
{
    public List<Customer> getCustomers(int theSortField);
    public Customer getCustomer(int customerId);
    public void saveCustomer(Customer customer);
    public void deleteCustomer(int customerId);
    public List<Customer> searchCustomers(String theSearchName);
}
