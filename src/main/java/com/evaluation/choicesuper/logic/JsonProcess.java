package com.evaluation.choicesuper.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail;
import com.evaluation.choicesuper.config.AppProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configurable

public class JsonProcess {
	
	Logger logger = LoggerFactory.getLogger(JsonProcess.class);

	 static String BASEPATH=AppProperties.appProps.getProperty("jsonBasePath");
	 static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("ddMMyyyyHHmmss"); 
	 static String CRLF="\r\n";

	/**
	 * @param memTransPOJO
	 * Converts POJO to JSON specific to the business requiremnt.
	 * Returns the Status with count of Transactions
	 * @return
	 */
	public final String processToJson(MemberRegistrationTransactions memTransPOJO) {
		
		String returnMessage=null;
		StringBuilder runningResults = new StringBuilder();
		
		
		try {
			if(memTransPOJO.getMemberRegistrationTransaction()!=null&&memTransPOJO.getMemberRegistrationTransaction().size()>0) {
				
				
				 
				  try {
				  
					  if(makeAndWriteJson(memTransPOJO)){
						  runningResults.append(memTransPOJO.getMemberRegistrationTransaction().size()+"-Transactions Processed For ID "+
					        (memTransPOJO.getMemberRegistrationTransaction().size()>0? memTransPOJO.getMemberRegistrationTransaction().get(0).getTransactionIdentifier():"None")
								  +CRLF);
					  }else {
						  runningResults.append(memTransPOJO.getMemberRegistrationTransaction().size()+"-Transactions Failed For- "+
								  (memTransPOJO.getMemberRegistrationTransaction().size()>0? memTransPOJO.getMemberRegistrationTransaction().get(0).getTransactionIdentifier():"None")
									
								  +CRLF);
					  }
				  
				  
				  }catch (IOException e) {
					
					  logger.error("Error in JSON PRocess -"+e.getLocalizedMessage());
					  runningResults.append("Error in Process");
				  }
				}

				
			logger.debug(runningResults.toString());
			
			returnMessage=runningResults.toString();
		}catch (Exception e) {

			returnMessage="Error Processing File "+e.getLocalizedMessage();
		}
		
		return returnMessage;
	}

	
	/**
	 * @param transactions
	 * Converts Bean into JSON and includes grouping and Mapping
	 * Get each transaction->For each in Parallel Get employer and Then get fund, group them into a Map of Maps..
	 * 
	 * @return
	 * @throws IOException
	 */
	private final boolean makeAndWriteJson(MemberRegistrationTransactions transactions) throws IOException  {		
		boolean status=false;
		try {
			
			HashMap<String, HashMap<String,List<TransactionDetail>>> fundMap=new HashMap<String, HashMap<String,List<TransactionDetail>>>();
			
			
			transactions.getMemberRegistrationTransaction()
			.parallelStream()
			.forEach(memberTransaction->{
				memberTransaction.
				getTransactionDetails().
				parallelStream().forEach(
						transaction->
						{
							//Group BY ->transaction.getTransactionEmployer();, 	//transaction.getTransactionFundForMember();
							HashMap<String,List<TransactionDetail>> employerMap=null;
							List<TransactionDetail> pushList=null;
							
							if(fundMap.containsKey(transaction.getTransactionFundForMember())) {
								employerMap=fundMap.get(transaction.getTransactionFundForMember());
							}else {
								employerMap=new HashMap<String,List<TransactionDetail>>();
							}
							//pushList.add(transaction);
							if(employerMap.containsKey(transaction.getTransactionEmployer())) {
								pushList=employerMap.get(transaction.getTransactionEmployer());
							}else {
								pushList=new ArrayList<MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail>();
								
							}
							pushList.add(transaction);
							employerMap.put(transaction.getTransactionEmployer(), pushList);				
							fundMap.put(transaction.getTransactionFundForMember(), employerMap);
						}
				       );
				
			});
			
		   
			 String jsonContent=convertToJson(fundMap);
			 if(jsonContent!=null&&jsonContent.length()>0) {
				 if(writetoFile(transactions.getMemberRegistrationTransaction().get(0).getTransactionIdentifier(), jsonContent)) {
					 status=true;
				 }else {
					 status=false;
				 }
			 }
			
			
			
		}catch (IOException e) {

			status=false;
			logger.error("Error in Making JSON->"+e.getLocalizedMessage());
			throw e;
		}
		
		return status;
	}
	
	
	
	
	/**
	 * @param fundMap
	 * Use JACKSON to convert MAP object to JSON
	 * @return
	 */
	private final String convertToJson(HashMap<String, HashMap<String,List<TransactionDetail>>> fundMap) {		
		String returnJson;
		 ObjectMapper objMapper ;
		try {
			objMapper= new ObjectMapper(); 
			returnJson=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fundMap);
		}catch (Exception e) {
			
			logger.error("Error in JACKSON conversion"+e.getLocalizedMessage());
			returnJson=null;
		}
		
		return returnJson;
	}
	
	/**
	 * @param strID
	 * @param strContent
	 * Write File with ID
	 * @return
	 * @throws IOException
	 */
	private final boolean writetoFile(String strID,String strContent) throws IOException {
	   
		boolean success=false;
	    BufferedWriter writer =null;
	    try {
	    	 writer= new BufferedWriter(new FileWriter(BASEPATH+strID+"_"+DATEFORMAT.format(new Date()) 
	    	    +".json"));
	         writer.write(strContent);	    
	        writer.close();
	        success=true;
	    }catch (IOException e) {
	
	    	logger.error("Error writing JSON"+e.getLocalizedMessage());
	    	success=false;
	    	throw e;
		}
	    return success;
	}
	
	
	
}
