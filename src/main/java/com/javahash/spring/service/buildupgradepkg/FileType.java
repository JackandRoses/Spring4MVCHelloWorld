package com.javahash.spring.service.buildupgradepkg;

public enum FileType {
	JS("JS"),
	JSP("JSP"),
	TXT("TXT"),
	SQL("SQL"),
	XLS("XLS"),
	XML("XML"),
	JAVA("JAVA"),
	CSS("CSS"),
	PNG("PNG"),
	DOC("DOC");
	private String value;
	FileType(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
	public boolean isFlieType(String value){
		return this.value.equals(value);
	}
}
