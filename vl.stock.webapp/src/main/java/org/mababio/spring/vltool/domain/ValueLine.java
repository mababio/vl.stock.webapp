/**
 * 
 */
package org.mababio.spring.vltool.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;






public class ValueLine implements Serializable {

	private static final long serialVersionUID = 234292435074726920L;
	
	

	private java.util.Date vlineDate;
	
	
	public java.util.Date getVlineDate() {
		return vlineDate;
	}

	public void setVlineDate(java.util.Date vlineDate) {
		this.vlineDate = vlineDate;
	}

	public String toString() {
		
		   return ReflectionToStringBuilder.toString(this);
		}

	/**
	* Equality must be implemented in terms of identity field
	* equality, and must use instanceof rather than comparing
	* classes directly (some JPA implementations may subclass the
	* identity class).
	*/
	public boolean equals(Object other) {
	if (other == this)
	return true;
	if (!(other instanceof ValueLine))
	return false;
	ValueLine mi = (ValueLine) other;
	return (this.vlineDate == mi.vlineDate);
	}
	
	@Override  
	public int hashCode()  
	{  
		HashCodeBuilder builder=new HashCodeBuilder() ;
		   
	   return  builder.toHashCode();
	      
	} 

	
	
		
}
