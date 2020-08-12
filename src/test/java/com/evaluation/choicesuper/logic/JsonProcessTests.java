package com.evaluation.choicesuper.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.junit.Test;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;

public class JsonProcessTests {

	
	@Test
	public void testJsonProcess() throws Exception{
		String content=new String(Files.readAllBytes(Paths.get(
				System.getProperty("user.home") + "\\Desktop\\choicecsv.csv"
				)), StandardCharsets.UTF_8);
		//new CSVTransformer().getObjectFromCSV(content);



		MemberRegistrationTransactions translatedPojo=new CSVTransformer().getObjectFromCSV(content);
		JsonProcess jsonProcess=new JsonProcess();
	    assertTrue("processed",jsonProcess.processToJson(translatedPojo)!=null);
	    
	    content=new String(Files.readAllBytes(Paths.get(
				System.getProperty("user.home") + "\\Desktop\\choicexml.xml"
				)), StandardCharsets.UTF_8);
		//new CSVTransformer().getObjectFromCSV(content);



		 translatedPojo=new CSVTransformer().getObjectFromCSV(content);
		 assertFalse("Failed process",jsonProcess.processToJson(translatedPojo)!=null);
		 
		 java.lang.reflect.Field field = jsonProcess.getClass().getDeclaredField("BASEPATH");
		    field.setAccessible(true);
		    field.set(null, "emptypath");
		    
		 content=new String(Files.readAllBytes(Paths.get(
					System.getProperty("user.home") + "\\Desktop\\choicexml.xml"
					)), StandardCharsets.UTF_8);
			//new CSVTransformer().getObjectFromCSV(content);



			 translatedPojo=new CSVTransformer().getObjectFromCSV(content);
			 assertFalse("Failed process",jsonProcess.processToJson(translatedPojo)!=null);
		
		
	}
}
