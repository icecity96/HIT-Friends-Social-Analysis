package com.util.ldautil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
public class LdaUtil {
	/**
	 * 
	 * @param phi LDA modle
	 * @param vocabulary
	 * @param limit
	 * @return
	 */
	public static Map<String, Double>[] translate(double[][] phi,
			Vocabulary vocabulary,int limit) {
		limit = Math.min(limit, phi[0].length);
        Map<String, Double>[] result = new Map[phi.length];
        for (int k = 0; k < phi.length; k++) {
            Map<Double, String> rankMap = new TreeMap<Double, String>(Collections.reverseOrder());
            for (int i = 0; i < phi[k].length; i++) {
                rankMap.put(phi[k][i], vocabulary.getWord(i));
            }
            Iterator<Map.Entry<Double, String>> iterator = rankMap.entrySet().iterator();
            result[k] = new LinkedHashMap<String, Double>();
            for (int i = 0; i < limit; ++i) {
                Map.Entry<Double, String> entry = iterator.next();
                result[k].put(entry.getValue(), entry.getKey());
            }
        }
        return result;
	}
	 public static int translate(double[] tp, double[][] phi, Vocabulary vocabulary, int limit)
	    {
	        Map<String, Double>[] topicMapArray = translate(phi, vocabulary, limit);
	        double p = -1.0;
	        int t = -1;
	        for (int k = 0; k < tp.length; k++)
	        {
	            if (tp[k] > p)
	            {
	                p = tp[k];
	                t = k;
	            }
	        }
	        return t;
	    }

	    /**
	     * To print the result in a well formatted form
	     * @param result
	     */
	    public static void explain(Map<String, Double>[] result)
	    {
	        int i = 0;
	        for (Map<String, Double> topicMap : result)
	        {
	            System.out.printf("topic %d :\n", i++);
	            explain(topicMap);
	            System.out.println();
	        }
	    }

	    public static void explain(Map<String, Double> topicMap)
	    {
	        for (Map.Entry<String, Double> entry : topicMap.entrySet())
	        {
	            System.out.println(entry);
	        }
	    }
}
