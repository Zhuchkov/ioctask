package ua.rd.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class TestObj {

	private SecondTestObject obj;
	private String objText;
	TestObj(){}
	public TestObj(SecondTestObject obj) {
		this.setObj(obj);
	}

	public SecondTestObject getObj() {
		return obj;
	}

	public void setObj(SecondTestObject obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "TestObj [obj=" + obj + "]";
	}
	public String getObjText() {
		return objText;
	}
	public void setObjText(String objText) {
		this.objText = objText;
	}

}
