package com.employee.payroll;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {

	public static void main(String[] args) {

		final int BLOCK_SIZE = 1024;
		final int BUFFER_SIZE = 8 * BLOCK_SIZE;
		DataOutputStream dataOut = null;
		BufferedReader in =null;

		try {

		  //API endpoint for API sandbox 
		  String url = "https://sandbox.api.sap.com/successfactors/odata/v2/EmployeePayrollRunResultsItems?%24top=20";


		  URL urlObj = new URL(url);
		  HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
		  //setting request method
		  connection.setRequestMethod("GET");

		  //adding headers
		  //API Key for API Sandbox
		  connection.setRequestProperty("APIKey","Il9ANtquu7RRFSQtyMYr0yl1oNNez7d1");
		  connection.setRequestProperty("DataServiceVersion","2.0");
		  connection.setRequestProperty("Accept","application/json");


		  connection.setDoInput(true);

		  int responseCode = connection.getResponseCode();
		  in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  StringBuilder response = new StringBuilder();
		    char[] charArray = new char[BUFFER_SIZE];
		    int charsCount = 0;
		    while ((charsCount = in.read(charArray)) != -1) {
		      response.append(String.valueOf(charArray, 0, charsCount));
		    }

		  //printing response
		  System.out.println(response.toString());

		} catch (Exception e) {
		  //do something with exception
		  e.printStackTrace();
		} finally {
		  try {
		    if(dataOut != null) {
		      dataOut.close();
		    }
		    if(in != null) {
		      in.close();
		    }

		  } catch (IOException e) {
		    //do something with exception
		    e.printStackTrace();
		  }
		}

	}

}
