
public class Bank extends Thread implements Runnable {
       int funds;
       String response;
       public Bank(String bankName, int funds)
       {
    	   super(bankName);
    	   this.setName(bankName);
    	   this.funds = funds;
    	   this.response = "";
       }
       public void run()
       {
		   try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	   
       }
       public void sendRequest(String customerName,int requestedAmount)
       {
    	   response = this.getName() + " ";
    	   if(funds - requestedAmount > 0)
    	   {
    		   funds-=requestedAmount;
    		   response = response + "approves ";
    	   }
    	   else
    		   response =  response + "denies ";
    	   response = response + "a loan of "+ requestedAmount + " dollar(s) from "+customerName;
    	   System.out.println(response);
       }
}
