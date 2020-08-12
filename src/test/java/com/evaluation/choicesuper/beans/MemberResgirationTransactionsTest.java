package com.evaluation.choicesuper.beans;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;


import org.junit.Test;

public class MemberResgirationTransactionsTest {
	
	@Test
	public void testMemberRegistrationTransactions() {
		MemberRegistrationTransactions memTransactions=new MemberRegistrationTransactions();
		assertTrue("Object Initialize", memTransactions!=null);
		MemberRegistrationTransactions.MemberRegistrationTransaction transaction=new MemberRegistrationTransactions.MemberRegistrationTransaction();
		ArrayList aLst=new ArrayList();
		aLst.add(transaction);
		memTransactions.setMemberRegistrationTransaction(aLst);
		assertTrue("Object Initialize", memTransactions.getMemberRegistrationTransaction()!=null);
	}
	
	public static void main(String[] args) {
		new MemberResgirationTransactionsTest().testMemberRegistrationTransactions();
	}

}
