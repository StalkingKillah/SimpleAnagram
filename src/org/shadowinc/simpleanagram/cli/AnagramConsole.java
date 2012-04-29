/**
 * 
 */
package org.shadowinc.simpleanagram.cli;

//import org.apache.commons.cli.Options;
import org.shadowinc.simpleanagram.NoAnagramException;
import org.shadowinc.simpleanagram.Wordlist;

/**
 * @author nighthawk
 *
 */
public class AnagramConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Options opt = getOpt();
		Wordlist wl = new Wordlist();
		wl.loadWordlist(Wordlist.class.getResourceAsStream("sr_lat_only.wl"));
		try {
			String[] found = wl.search("palma".toCharArray(), 4, 5);
			for (int i=0; i<found.length; i++)
				System.out.println(found[i]+" => "+found[i].length());
		} catch (NoAnagramException e) {
			e.printStackTrace();
		}
	}

}
