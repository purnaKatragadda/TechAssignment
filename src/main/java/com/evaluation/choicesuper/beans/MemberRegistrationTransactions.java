package com.evaluation.choicesuper.beans;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class MemberRegistrationTransactions  {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<MemberRegistrationTransaction>  memberRegistrationTransaction =  null;




	public List<MemberRegistrationTransaction> getMemberRegistrationTransaction() {
		return memberRegistrationTransaction;
	}




	public void setMemberRegistrationTransaction(List<MemberRegistrationTransaction> memberRegistrationTransaction) {
		this.memberRegistrationTransaction = memberRegistrationTransaction; 
	}



	
	public static class MemberRegistrationTransaction {
		private String transactionIdentifier;
		@JacksonXmlElementWrapper(useWrapping = false)
		List < TransactionDetail > transactionDetails = new ArrayList < TransactionDetail > ();
		// Getter Methods 



		public List<TransactionDetail> getTransactionDetails() {
			return transactionDetails;
		}


		public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
			this.transactionDetails = transactionDetails;
		}


		public String getTransactionIdentifier() {
			return transactionIdentifier;
		}


		// Setter Methods 

		public void setTransactionIdentifier(String transactionIdentifier) {
			this.transactionIdentifier = transactionIdentifier;
		}


	  public static class TransactionDetail {
			private String transactionEmployer;
			private String transactionFundForMember;
			@JacksonXmlElementWrapper(useWrapping = false)
			List<TransactionMember> transactionMembers = new ArrayList <TransactionMember> ();

			

			public List<TransactionMember> getTransactionMembers() {
				return transactionMembers;
			}

			public void setTransactionMembers(List<TransactionMember> transactionMembers) {
				this.transactionMembers = transactionMembers;
			}

			public String getTransactionEmployer() {
				return transactionEmployer;
			}

			public String getTransactionFundForMember() {
				return transactionFundForMember;
			}

			// Setter Methods 

			public void setTransactionEmployer(String transactionEmployer) {
				this.transactionEmployer = transactionEmployer;
			}

			public void setTransactionFundForMember(String transactionFundForMember) {
				this.transactionFundForMember = transactionFundForMember;
			}



			public static class TransactionMember {
				private String memberFirstName;
				private String memberLastName;
				private String memberOtherNames;
				private String memberDateOfBirth;
				private String memberGender;
				private String memberAddress;
				private String memberEmail;
				private String memberContactNumber;
				private String memberNumber;
				private String memberTFN;
				private String memberPayrollNumber;
				private String memberEmploymentStatus;
				private String memberFundRegistrationDate;


				// Getter Methods 

				public String getMemberFirstName() {
					return memberFirstName;
				}

				public String getMemberLastName() {
					return memberLastName;
				}

				public String getMemberOtherNames() {
					return memberOtherNames;
				}

				public String getMemberDateOfBirth() {
					return memberDateOfBirth;
				}

				public String getMemberGender() {
					return memberGender;
				}

				public String getMemberAddress() {
					return memberAddress;
				}

				public String getMemberEmail() {
					return memberEmail;
				}

				public String getMemberContactNumber() {
					return memberContactNumber;
				}

				public String getMemberNumber() {
					return memberNumber;
				}

				public String getMemberTFN() {
					return memberTFN;
				}

				public String getMemberPayrollNumber() {
					return memberPayrollNumber;
				}

				public String getMemberEmploymentStatus() {
					return memberEmploymentStatus;
				}

				public String getMemberFundRegistrationDate() {
					return memberFundRegistrationDate;
				}

				// Setter Methods 

				public void setMemberFirstName(String memberFirstName) {
					this.memberFirstName = memberFirstName;
				}

				public void setMemberLastName(String memberLastName) {
					this.memberLastName = memberLastName;
				}

				public void setMemberOtherNames(String memberOtherNames) {
					this.memberOtherNames = memberOtherNames;
				}

				public void setMemberDateOfBirth(String memberDateOfBirth) {
					this.memberDateOfBirth = memberDateOfBirth;
				}

				public void setMemberGender(String memberGender) {
					this.memberGender = memberGender;
				}

				public void setMemberAddress(String memberAddress) {
					this.memberAddress = memberAddress;
				}

				public void setMemberEmail(String memberEmail) {
					this.memberEmail = memberEmail;
				}

				public void setMemberContactNumber(String memberContactNumber) {
					this.memberContactNumber = memberContactNumber;
				}

				public void setMemberNumber(String memberNumber) {
					this.memberNumber = memberNumber;
				}

				public void setMemberTFN(String memberTFN) {
					this.memberTFN = memberTFN;
				}

				public void setMemberPayrollNumber(String memberPayrollNumber) {
					this.memberPayrollNumber = memberPayrollNumber;
				}

				public void setMemberEmploymentStatus(String memberEmploymentStatus) {
					this.memberEmploymentStatus = memberEmploymentStatus;
				}

				public void setMemberFundRegistrationDate(String memberFundRegistrationDate) {
					this.memberFundRegistrationDate = memberFundRegistrationDate;
				}
			}
		}



	}


}