package org.simple.project.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.simple.project.model.ListToMapId;
import org.simple.project.model.ListToMapModel;

public class ListToMapUtil {
	
	public static <T> Map<T,String> toMapModelString(List<T> list){
		Map<T,String> m = new TreeMap<T,String>();
		
		for (T object : list) {
			ListToMapModel x = (ListToMapModel)object;
			m.put(object, x.getName());
		}
		return m;
	}

	public static <T> Map<String,T> toMapStringModel(List<T> list){
		Map<String,T> m = new TreeMap<String,T>();
		
		for (T object : list) {
			ListToMapModel x = (ListToMapModel)object;
			m.put(x.getName(),object);
		}
		return m;
	}
	
	public static <T> Map<T,Long> toMapId(List<T> list){
		Map<T,Long> m = new TreeMap<T,Long>();
		
		for (T object : list) {
			ListToMapId x = (ListToMapId)object;
			m.put(object, x.getId());
		}
		return m;
	}
}
