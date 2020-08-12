package com.evaluation.choicesuper;

import java.util.ArrayList;
import java.util.Arrays;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail.TransactionMember;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JavaTests {

	public static void main(String[] args) throws Exception{
		 XmlMapper xmlMapper = new XmlMapper();
		MemberRegistrationTransactions transactions= new MemberRegistrationTransactions();
		MemberRegistrationTransactions.MemberRegistrationTransaction transaction=new MemberRegistrationTransactions.MemberRegistrationTransaction();
		transaction.setTransactionIdentifier("1");
		TransactionDetail transactionDetail=new TransactionDetail();
		transactionDetail.setTransactionEmployer("Emp1");
		transactionDetail.setTransactionFundForMember("fund1");
		TransactionMember transactionMember=new TransactionMember();
		transactionMember.setMemberAddress("add1");
		transactionMember.setMemberContactNumber("con1");
		transactionMember.setMemberDateOfBirth("01/02/2020");
		transactionMember.setMemberEmail("test@test.com");
		transactionMember.setMemberEmploymentStatus("empstatus");
		transactionMember.setMemberFirstName("fname1");
		transactionMember.setMemberFundRegistrationDate("10/01/2020");
		transactionMember.setMemberGender("ungen");
		transactionMember.setMemberLastName("laname");
		transactionMember.setMemberNumber("45454");
		transactionMember.setMemberOtherNames("othernames");
		transactionMember.setMemberPayrollNumber("45454");
		transactionMember.setMemberTFN("tfc1");
		
		transactionDetail.setTransactionMembers(Arrays.asList(transactionMember));
		
		transaction.setTransactionDetails(Arrays.asList(transactionDetail));
		transactions.setMemberRegistrationTransaction( Arrays.asList(transaction));
		
		System.out.println( xmlMapper.writeValueAsString(transactions));


	}
}
