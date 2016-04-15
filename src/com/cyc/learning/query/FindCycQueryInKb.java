package com.cyc.learning.query;

import com.cyc.kb.KbIndividual;
import com.cyc.kb.KbIndividualFactory;
import com.cyc.kb.exception.CreateException;
import com.cyc.kb.exception.KbException;
import com.cyc.kb.exception.KbTypeException;
import com.cyc.query.Query;
import com.cyc.query.QueryFactory;
import com.cyc.query.exception.QueryConstructionException;
import com.cyc.session.exception.UnsupportedCycOperationException;

public class FindCycQueryInKb {
	
public Query findQueryByName(String queryName) throws  KbException{
	
		
		KbIndividual kbIndividual = null;
		Query cycQuery = null;
		
		try {
			kbIndividual =  KbIndividualFactory.get("(TestQueryFn " + queryName + ")");
		} catch (KbTypeException | CreateException e) {
			throw new KbTypeException(e);
		}
		
		try {
			cycQuery =  QueryFactory.getQuery(kbIndividual);
			cycQuery.retainInference();
			cycQuery.setContinuable(true);
		} catch (UnsupportedCycOperationException | QueryConstructionException e) {
			throw new UnsupportedCycOperationException(e);
		}
		return cycQuery;
	}

}
