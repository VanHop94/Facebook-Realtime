package com.datasection.facebook.be.entities;

import java.util.ArrayList;

public class ListFixedSize<T> {

	private ArrayList<T> listFixedSize;
	private int SIZE = 30;

	public ListFixedSize() {
		listFixedSize = new ArrayList<T>();
	}
	
	public ListFixedSize(int size){
		SIZE = size;
		listFixedSize = new ArrayList<T>();
	}
	
	public ArrayList<T> getList() {
		return listFixedSize;
	}

	public void add(T element) {
		if(listFixedSize.size() + 1 > SIZE)
			listFixedSize.remove(0);
		listFixedSize.add(element);
	}

}
