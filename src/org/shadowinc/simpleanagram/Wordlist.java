/**
 * 
 */
package org.shadowinc.simpleanagram;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author nighthawk
 *
 */
public class Wordlist extends ArrayList<String> {
	private static final long serialVersionUID = -8712188466962894386L;
	private boolean complete = false;
	private ArrayList<String> found;

	public Wordlist() {
		super();
	}

	public void loadWordlist(InputStream is) {
			BufferedReader buffer;
			String line;
			try {
				buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = buffer.readLine()) != null)
					add(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public String[] search(char[] chars) throws NoAnagramException {
		return search(chars, 4, 5);
	}
	
	public String[] search(char[] chars, int minLength, int count) throws NoAnagramException {
		found = new ArrayList<String>();
		String word;

		Iterator<String> iter = iterator();
		while (iter.hasNext()) {
			word = iter.next();
			if (count(word.toCharArray(), chars) >= minLength)
				if (!found.contains(word))
					found.add(word);
		}
		
		Collections.sort(found, new Comparator<String>() {
			public int compare(String o1, String o2) {
				if (o1.length() < o2.length())
					return 1;
				else if (o1.length() > o2.length())
					return -1;
				else
					return o1.compareTo(o2);
			}
		});
		complete = true;
		if (found.size() == 0) throw new NoAnagramException(); 
		return getLastResult(count);
	}
	
	public String[] getLastResult(int count) {
		String[] result = null;
		if (count > found.size()) count = found.size();
		if (found.size() > 1) result = Arrays.copyOfRange(found.toArray(new String[found.size()]), 0, count);
		return result;
	}
	
	public boolean isSearchComplete() {
		return complete;
	}
	
	public void reset() {
		complete = false;
		found = null;
	}
	
	public static int count(String word, String find) {
		return count(word.toCharArray(), find.toCharArray());
	}
	
	public static int count(char[] word, char[] find) {
		int c = 0;
		char[] check = find.clone();
		char[] in = word.clone();
		Arrays.sort(check);
		Arrays.sort(in);
		if (word.length <= find.length)
			for (int i = 0; i < in.length; i++) {
				for (int j = 0; j < check.length; j++)
					if (check[j] != '\0')
						if (in[i] == check[j]) {
							c++;
							in[i] = check[j] = '\0';
						}
				if (in[i] != '\0') {
					c=0;
					break;
				}
			}
		return c;
	}
}
