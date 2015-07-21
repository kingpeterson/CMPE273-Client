import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.sun.jersey.api.client.*;


public class RESTfulPostClient2 {
	public static void main(String[] args){
		int count = 0;
		while(count != 5){
			try{
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:8080/CMPE273/webResource/Rest/postInfo");
				String time =  new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				String input ="{\"ID\": \"0011\", \"Data\": \""+time+"\"}";
				ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
				
				if (response.getStatus() != 201){
					throw new RuntimeException("Failed HTTP error code:" + response.getStatus());
				}
				
				System.out.println("Output from Server\n");
				String output = response.getEntity(String.class);
				System.out.println(output);
				
			} catch (Exception e){
				e.printStackTrace();
			}
			try {
			    Thread.sleep(2000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			count++;
		}
	}

}
