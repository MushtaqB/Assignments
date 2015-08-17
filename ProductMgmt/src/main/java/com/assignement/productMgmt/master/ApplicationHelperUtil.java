package com.assignement.productMgmt.master;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Mushtaq Ahmed
 *
 */
public class ApplicationHelperUtil {
	
	public static <D> Set<D> getMissingEntity(Set<D> transfer, Set<D> original){
		if((transfer == null || transfer.size() <=0) && original != null && original.size()>0) return original;
		if(original == null || original.size() <=0) return new HashSet<D>();
		Set<D> removed = new HashSet<D>();
		for (D o : original) {
			boolean exists = false;
			for (D t : transfer) {
				if(o.hashCode() == t.hashCode()){
					exists = true;
					break;
				}
			}
			if(!exists) removed.add(o);
		}
		return removed;
	}
}
