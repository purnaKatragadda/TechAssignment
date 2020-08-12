package com.evaluation.choicesuper.logic;

import java.util.ArrayList;
import java.util.Map;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail;
import com.evaluation.choicesuper.beans.MemberRegistrationTransactions.MemberRegistrationTransaction.TransactionDetail.TransactionMember;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


/**
 * Unlike XML CSV cannot be Mapped by JSON directly into bean so the fastest way is this method.
 *
 */
public class CSVTransformer {


	/**
	 * @param content
	 *  Unlike XML CSV cannot be Mapped by JSON directly into bean so the fastest way is this method.
	 * @return
	 * @throws Exception
	 */
	public  final MemberRegistrationTransactions getObjectFromCSV(String content) throws Exception {

		MemberRegistrationTransactions objRetMemTransactions=null;
		
		try {

			objRetMemTransactions=toMemberMap(content);
			
		}catch (Exception e) {
			
			throw e;
		}


		return objRetMemTransactions;

	}



	/**
	 * @param csvContent
	 * Converts CSV into Bean,Using all Fields for now
	 * @return
	 */
	private final  MemberRegistrationTransactions toMemberMap(String csvContent) throws Exception{

		MemberRegistrationTransactions memberTransactions=new MemberRegistrationTransactions();
		ArrayList<MemberRegistrationTransaction> memberRegistrationTransactionList= new ArrayList<MemberRegistrationTransaction>();
		MemberRegistrationTransaction memberRegistrationTransaction=null;

		try {
			CsvMapper csvMapper =new  CsvMapper();

			MappingIterator<Map> rows = csvMapper
					.readerWithSchemaFor(Map.class)
					.with(CsvSchema.emptySchema().withHeader())
					.readValues(csvContent);

			Map<String,String> csvMap=null;
			if(rows!=null) {
				
				ArrayList<TransactionDetail> transactionDetailsList=null;
				ArrayList<TransactionMember> transactionMembersList=null;
				TransactionDetail transDetail=null;
				TransactionMember transMember=null;
				
				while(rows.hasNext()) {
					csvMap =rows.next();
     				memberRegistrationTransaction=new MemberRegistrationTransaction();
					memberRegistrationTransaction.setTransactionIdentifier(csvMap.get("transactionIdentifier"));

					transactionDetailsList=new ArrayList<TransactionDetail>();
				    transDetail=new TransactionDetail();
					
				    transDetail.setTransactionEmployer( csvMap.get("fundEmployerIdentifier"));

				    
					transDetail.setTransactionFundForMember(csvMap.get("fundIdentifier"));
					
			        transactionMembersList=new ArrayList<TransactionMember>();
			        transMember=new TransactionMember();
					
			        transMember.setMemberAddress( csvMap.get("memberFirstName"));
					transMember.setMemberContactNumber( csvMap.get("memberContactNumber"));
					transMember.setMemberDateOfBirth( csvMap.get("memberDateOfBirth"));
					transMember.setMemberEmail( csvMap.get("memberEmail"));
					transMember.setMemberEmploymentStatus( csvMap.get("memberEmploymentStatus"));
					transMember.setMemberFirstName( csvMap.get("memberFirstName"));
					transMember.setMemberFundRegistrationDate( csvMap.get("memberFundRegistrationDate"));
					transMember.setMemberGender( csvMap.get("memberGender"));

					transMember.setMemberLastName( csvMap.get("memberLastName"));
					transMember.setMemberNumber( csvMap.get("memberNumber"));
					transMember.setMemberOtherNames( csvMap.get("memberOtherNames"));
					transMember.setMemberPayrollNumber( csvMap.get("memberPayrollNumber"));
					transMember.setMemberTFN( csvMap.get("memberTFN"));

					transactionMembersList.add(transMember);
					transDetail.setTransactionMembers(transactionMembersList);
					
					transactionDetailsList.add(transDetail);
					memberRegistrationTransaction.setTransactionDetails(transactionDetailsList);
					memberRegistrationTransactionList.add(memberRegistrationTransaction);

				}
				memberTransactions.setMemberRegistrationTransaction(memberRegistrationTransactionList);
			}
		}catch (Exception e) {
		
			throw e;
		}



		return memberTransactions;

	}

	


}
