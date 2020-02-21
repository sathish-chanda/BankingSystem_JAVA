import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankingDriver {
	public static void main(String[] args) throws Exception
	{
		HashMap<String,Integer> bankDetails = new HashMap<String,Integer>();
		HashMap<String,Integer> customerDetails = new HashMap<String,Integer>();
		List<String> banksNames = new ArrayList<String>();
		List<Customer> customersList = new ArrayList<Customer>();
		List<Bank> banksList = new ArrayList<Bank>();
	    Customer c = null;
	    File customers = new File("customers.txt");
	    BufferedReader br = new BufferedReader(new FileReader(customers));
	    String line;
	    System.out.println("** Customers and loan objectives **");
	    while((line = br.readLine())!=null)
	    {
	       String[] tmp = line.substring(1).split(",");
	        String customerName = tmp[0].trim();
	        int loan = Integer.parseInt(tmp[1].substring(0, tmp[1].indexOf('}')).trim());
	        customerDetails.put(customerName,loan);
	        customersList.add(new Customer(customerName,loan));
	        System.out.println(customerName+": "+loan);
	    }
	    System.out.println();
	    File banks = new File("banks.txt");
	    br = new BufferedReader(new FileReader(banks));
	    System.out.println("** Banks and financial resources **");
	    while((line = br.readLine())!=null)
	    {
	        String[] tmp = line.substring(1).split(",");
	        String bankName = tmp[0].trim();
	        int funds = Integer.parseInt(tmp[1].substring(0, tmp[1].indexOf('}')).trim());
	        bankDetails.put(bankName,funds);
	        banksNames.add(bankName);
	        banksList.add(new Bank(bankName,funds));
	        System.out.println(bankName+": "+funds);
	    }
	    System.out.println();
	    for(int i=0;i<banksList.size();i++)
	    	banksList.get(i).start();
	    for(int i=0;i<customersList.size();i++)
	    	customersList.get(i).addBanks(banksList);
	    for(int i=0;i<customersList.size();i++)
	    	{
	    	   customersList.get(i).start();
	    	}
//	    Customer c1 = new Customer("C1",100,200);
//	    Customer c2 = new Customer("C2",10,20);
//	    Customer c3 = new Customer("C3",56,78);
//	    Customer c4 = new Customer("C4",99,33);
//	    c1.start();
//	    c2.start();
//	    c3.start();
//	    c4.start();
	}
}
