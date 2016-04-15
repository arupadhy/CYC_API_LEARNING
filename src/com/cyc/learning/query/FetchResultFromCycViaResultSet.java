package com.cyc.learning.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyc.kb.exception.KbException;
import com.cyc.query.Query;
import com.cyc.query.QueryResultSet;

public class FetchResultFromCycViaResultSet {
	
	//This is setup in local cyc kb
	private static final String CYC_QUERY =   "InitialTVOnAirOrOffForPringles-MCT";

	
	public static void main(String[] args) throws KbException {
		
		//Using local cyc instance for this example
		System.setProperty("cyc.session.server", "localhost:3600");
		
		
		//using default options, get a query setup in local cyc kb
		
	   Query cycQuery = new FindCycQueryInKb().findQueryByName(CYC_QUERY);
	   
	   //total number of answers for this query
	   
	   System.out.println(String.format("Total no of answers found for this query= %s", cycQuery.getAnswerCount()));
	   
	   //iterate over some answers
	   List<Map<String,String>> answerList = new ArrayList<>();
		
		QueryResultSet rs = cycQuery.getResultSet();
		//want to start from position 3 and loop through 10th answer.
		
		rs.relative(3);
		for(int i=0;rs.next()&&i<=10;i++) {
			Map<String, String> variableAndBindingMap = new HashMap<>();
			for(String variable: rs.getColumnNames()) {
				Object testObj = rs.getObject(variable, Object.class);
				variableAndBindingMap.put(variable, testObj.toString());
				System.out.println(String.format("Got variable and binding for %s answer %s : %s", i,variableAndBindingMap.keySet(),variableAndBindingMap.values()));
				if(rs.getInferenceIdentifier() == null) {
					System.out.println("Why I am Null????");
				}
				
			}
			answerList.add(variableAndBindingMap);
		}
		
		
		
	}

}
