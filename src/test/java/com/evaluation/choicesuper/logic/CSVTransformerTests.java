package com.evaluation.choicesuper.logic;

import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;

public class CSVTransformerTests {
	
	public CSVTransformerTests() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void  testCSVCreation() throws Exception {

		String content=new String(Files.readAllBytes(Paths.get(
				System.getProperty("user.home") + "\\Desktop\\choicecsv.csv"
				)), StandardCharsets.UTF_8);
		//new CSVTransformer().getObjectFromCSV(content);



		MemberRegistrationTransactions translatedPojo=new CSVTransformer().getObjectFromCSV(content);

		//  System.out.println(translatedPojo.getMemberRegistrationTransaction().size());
		assertTrue("Object created", translatedPojo!=null);
	
	}
	
	public static void main(String[] args) throws Exception{

		String content=new String(Files.readAllBytes(Paths.get(
				System.getProperty("user.home") + "\\Desktop\\choicecsv.csv"
				)), StandardCharsets.UTF_8);
		//new CSVTransformer().getObjectFromCSV(content);



		MemberRegistrationTransactions translatedPojo=new CSVTransformer().getObjectFromCSV(content);

		//  System.out.println(translatedPojo.getMemberRegistrationTransaction().size());
		assertTrue("Object created", translatedPojo!=null);

	}



}
