import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Customer extends Thread {
	int loan;
	List<Bank> availableBanks;
	String message;
	Random random = new Random();
	public Customer(String threadName,int loan)
	{
		super(threadName);
		this.setName(threadName);
		this.loan = loan;
		this.message = "";
	}

	public void run()
	{
		try {
			sleep(1000);
			requestLoan();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addBanks(List<Bank> banks)
	{
		availableBanks = banks;
	}

	public void requestLoan() throws Exception
	{
		while(loan>0 && availableBanks.size()>0)
		{
		int requestAmount;
		Bank targetBank;
		String response="";
		requestAmount = random.nextInt(50)+1;
		int randomBankIndex = random.nextInt(availableBanks.size()); 
		targetBank = availableBanks.get(randomBankIndex);
		message = this.getName() + " requests a loan of "+requestAmount + " dollar(s) from " +targetBank.getName();
		System.out.println(message);

		targetBank.sendRequest(this.getName(), requestAmount);

		//    	   int waitTime = random.nextInt(100) + 10;
		//    	   sleep(waitTime);
		//    	   Bank bank=null;
		//    	   message = this.getName() + " requests a loan of "+requestAmount + " dollar(s) from ";
		//    	   Set<Thread> tSet =this.getAllStackTraces().keySet();
		//    	   for(Thread t : tSet)
		//    	   {
		//    		   System.out.println(t.getName().trim());
		//    		   if(t.getName().trim().equalsIgnoreCase(targetBank))
		//    		   {
		//    			  bank = (Bank)t;
		//    			  message = message + t.getName().trim();
		//    			  bank.sendRequest(this.getName(),requestAmount);
		//    			  break;
		//    		   }
		//    	   }
		//    	  
		if(targetBank!=null)
		{
			if(targetBank.response.contains("approves"))
			{
				loan-=requestAmount;
			}
			else
			{
				for(int i=0;i<availableBanks.size();i++)
				{
					if(availableBanks.get(i).equals(targetBank))
					{
						availableBanks.remove(i);
						break;
					}
				}
			}
		}
		//    	   response = sendRequest(targetBank,requestAmount);
		// wait();
		//notify();
		}
	}
	public void displayDetails()
	{
		System.out.print(this.getName() +" - ");
		System.out.print(loan + " - banks [");
		for(int i=0;i<availableBanks.size();i++)
			System.out.print(availableBanks.get(i)+" , ");
		System.out.println("]");
		notify();
	}
}
