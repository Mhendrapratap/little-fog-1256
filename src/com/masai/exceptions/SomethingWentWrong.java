package com.masai.exceptions;

public class SomethingWentWrong extends Exception{
	public SomethingWentWrong() {
		// TODO Auto-generated constructor stub
		System.out.println("Something went wrong");
	}
	public SomethingWentWrong(String str) {
		super(str);
	}
	@Override
	public String toString() {
		return "Some thing went wrong, try again later";
	}
	
}
