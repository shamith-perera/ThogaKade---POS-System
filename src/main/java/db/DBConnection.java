package db;

import model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static DBConnection instance;
    private final ArrayList<Customer> customers;
    private int nextCustomerId;

    private DBConnection() {
        customers = new ArrayList<>();
        nextCustomerId = 1;
    }

    public static DBConnection getInstance() {
        return null == instance ? instance = new DBConnection() : instance;
    }

    public String generateCustomerId() {
        return String.format("C%03d", nextCustomerId);
    }

    public void addCustomer(String title, String name, String address, String contactNumber, LocalDate dateOfBirth) throws RuntimeException {
        if (title == null || name.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || dateOfBirth == null) {
            throw new RuntimeException("All fields must be filled.");
        }
        if (!validateContactNumber(contactNumber)) {
            throw new RuntimeException("Invalid contact number. It must start with 0 and be 10 digits long.");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new RuntimeException("Date of birth cannot be in the future.");
        }
        String customerId = generateCustomerId();
        Customer customer = new Customer(customerId, title, name, address, contactNumber, dateOfBirth);
        nextCustomerId++;
        customers.add(customer);
    }

    public void updateCustomer(String customerId, String name, String address, String contactNumber) throws RuntimeException {
        if (name.isEmpty() || address.isEmpty() || contactNumber.isEmpty()) {
            throw new RuntimeException("Name, address, and contact number must not be empty.");
        }
        if (!validateContactNumber(contactNumber)) {
            throw new RuntimeException("Invalid contact number. It must start with 0 and be 10 digits long.");
        }
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found.");
        }
        customer.setName(name);
        customer.setAddress(address);
        customer.setContactNumber(contactNumber);
    }

    public void deleteCustomer(String customerId) throws RuntimeException {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found.");
        }
        customers.remove(customer);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomerDetails(String customerId) throws RuntimeException {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found.");
        }
        return new Customer(customer.getCustomerId(), customer.getTitle(), customer.getName(), customer.getAddress(),
                customer.getContactNumber(), customer.getDateOfBirth()); // Return a copy
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    private boolean validateContactNumber(String contactNumber) {
        return contactNumber.matches("0\\d{9}"); // Starts with 0, followed by exactly 9 digits
    }
}
