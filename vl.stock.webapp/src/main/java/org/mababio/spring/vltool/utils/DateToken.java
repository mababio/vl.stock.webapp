/**
 * 
 */
package org.mababio.spring.vltool.utils;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author Isaac Brobbey
 *
 */
public class DateToken {

	private String firstToken;
	private String secondToken;
	private String thirdToken;
	
	
	
	
	
	/**
	 * @return the firstToken
	 */
	public String getFirstToken() {
		return firstToken;
	}
	/**
	 * @return the secondToken
	 */
	public String getSecondToken() {
		return secondToken;
	}
	/**
	 * @return the thirdToken
	 */
	public String getThirdToken() {
		return thirdToken;
	}
	/**
	 * @param firstToken the firstToken to set
	 */
	public void setFirstToken(String firstToken) {
		this.firstToken = firstToken;
	}
	/**
	 * @param secondToken the secondToken to set
	 */
	public void setSecondToken(String secondToken) {
		this.secondToken = secondToken;
	}
	/**
	 * @param thirdToken the thirdToken to set
	 */
	public void setThirdToken(String thirdToken) {
		this.thirdToken = thirdToken;
	}
	
	public String toString() {
		   return ReflectionToStringBuilder.toString(this);
		}


}
