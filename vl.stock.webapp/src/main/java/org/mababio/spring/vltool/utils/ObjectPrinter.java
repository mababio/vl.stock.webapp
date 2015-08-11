package org.mababio.spring.vltool.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Using reflection the values of the object and its nested objects are printed.
 */
public class ObjectPrinter {
	
	/**
	 * The class for logging.
	 */
	private Logger log = Logger.getLogger(getClass().getName());
	
	/**
	 * The simple date format.
	 */
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * Prints the object to a string.
	 * @param object the object
	 * @return the formatted object.
	 */
	public String toString(Object object){
		if (log.isDebugEnabled()) {
			log.debug(">>toString");
		}
		StringBuffer buffer = new StringBuffer("[");
		if (object != null) {
			Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				buffer.append(" ").append(fields[i].getName()).append(" = [");
				try {
					fields[i].setAccessible(true);
					Object temp = fields[i].get(object);
					if (temp != null && temp.getClass().isArray()) {
						Object[] objects = (Object[]) temp;
						List list = Arrays.asList(objects);
						Iterator listIterator = list.iterator();
						while (listIterator.hasNext()) {
							Object loopObject = listIterator.next();
							if (loopObject != null) {
								if (loopObject.getClass().getName().startsWith("com")) {
									buffer.append(toString(loopObject));
								} else {
									buffer.append(loopObject).append(" ");
								}
							}
						}
						buffer.append("]");
					} else if (temp instanceof java.util.Calendar) {
						Calendar tempCalendar = (Calendar) temp;
						buffer.append(dateFormat.format(tempCalendar.getTime())).append("] ");
					} else if (fields[i].getType().getName().startsWith("com")) {
				    	Object newObject = fields[i].get(object);
				    	if (newObject != null) {
				    		//Prevent inifinite recursion if the class names match.
				    		if (!object.getClass().getName().equals(newObject.getClass().getName())) {
		    					buffer.append(toString(newObject));
		    					buffer.append("]");
		    				}
				    	} else {
				    		buffer.append(" ");
				    	}
				    } else {
				    	buffer.append(fields[i].get(object)).append("] ");
				    }
				} catch (Exception e) {
					if (log.isDebugEnabled()) {
						log.debug("Exception", e);
					}
				}
			}
		} else {
			buffer.append("null input");
		}
		buffer.append("]");
		if (log.isDebugEnabled()) {
			log.debug("String value [" + buffer.toString() + "]");
		}
		return buffer.toString();
	}
}
