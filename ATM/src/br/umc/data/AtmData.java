package br.umc.data;

import java.util.ArrayList;
import java.util.List;

import br.umc.model.Account;
import br.umc.model.Customer;

public class AtmData {
	private static List<Customer> customers;
	private static List<Account> accounts;
	
	static {
		customers = new ArrayList<>();
		accounts = new ArrayList<>();
		
		for (int i = 0; i < 15; i++) {
			customers.add(new Customer(new Long(i), "Cliente de número " + i));
			accounts.add(new Account("21" + i + "2" + i, "12" + i));
		}
	}
	
	public static List<Customer> getCustomers() {
		return customers;
	}
	
	public static List<Account> getAccounts() {
		return accounts;
	}
}
