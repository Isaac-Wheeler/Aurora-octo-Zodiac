package org.mageTech.mageUtil;

/**
 * print utility class for printing 
 * @author Isaac.Wheeler
 * @version	12.3.14
 */
public class printUtil {
	//private logUtil for loging to a file
	private logUtil logUtil;
	
	/**
	 * sets up the printer class by getting a already defined logUtil
	 * @param logUtil the logUtil
	 */
	public printUtil(logUtil logUtil){
		this.logUtil = logUtil;
	}
	
	/**
	 * prints to console
	 * @param msg the msg being printed
	 */
	public void print(String msg){
		System.out.print(msg);
	}
	
	/**
	 * prints a line to console
	 * @param msg the line being printed
	 */
	public void println(String msg){
		System.out.println(msg);
	}
	
	/**
	 * prints to console and logs to a file
	 * @param msg the msg being printed/loged
	 */
	public void printLog(String msg){
		System.out.print(msg);
		logUtil.log(msg);
	}
	
	/**
	 * prints a line to console and logs a line to file
	 * @param msg the msg being printed/loged
	 */
	public void printlnLog(String msg){
		System.out.println(msg);
		logUtil.logln(msg);
	}
}