package net.mrjohndoe.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Analyzer {
	public static final String domain = "https://infosec-jobs.com/?";
	//cit = city, cou = country of origin, exp = experience, key = keywords, typ = job types, reg = regions
	public static final String[] parameters =
		{
				"cit",
				"cou",
				"reg",
				"key",
				"typ",
				"exp"
		};
	
	public static void main(String[] args)
	{
		System.out.println("Parameters? [cit=-1] [cou=-1] [reg=-1] [key=-1] [typ=-1] [exp=-1]");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String params = "";
		
		try {
			String[] userParams = input.readLine().split(" ");
			
			for(int i = 0; i < userParams.length; i++)
			{
				int p = -1;
				
				try {
					p = Integer.parseInt(userParams[i]);
					
					if(p == -1)
					{
						params += parameters[i]+"=&";
					}
					else
					{
						params += parameters[i]+"="+userParams+"&";
					}
				} catch (NumberFormatException nfe)
				{
					params += parameters[i]+"="+userParams[i]+"&";
				}
			}
			
			if(params.substring(params.length() - 1) == "&")
			{
				params = params.substring(0, params.length() - 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(domain+params)).GET().build();
		
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
