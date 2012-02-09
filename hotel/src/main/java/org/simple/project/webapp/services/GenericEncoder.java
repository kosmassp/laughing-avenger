package org.simple.project.webapp.services;

import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ValueEncoder;
import org.simple.project.model.ListToMapModel;
import org.simple.project.util.ListToMapUtil;

public class GenericEncoder<T> implements ValueEncoder<T> {
	private Map<String,T> map;
	public GenericEncoder(List<T> l){
		map = ListToMapUtil.toMapStringModel(l);
	}

	public String toClient(T t) {
		return ((ListToMapModel)t).getName();
	}

	public T toValue(String clientValue) {
		return map.get(clientValue);
	}
		
}
