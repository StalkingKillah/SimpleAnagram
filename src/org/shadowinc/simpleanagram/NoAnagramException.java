/**
 * 
 */
package org.shadowinc.simpleanagram;

/**
 * @author nighthawk
 *
 */
public class NoAnagramException extends Exception {
	private static final long serialVersionUID = -7553977942556276542L;

	public NoAnagramException() {
		super("No anagram found.");
	}
	
}
