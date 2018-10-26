import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];	
		for(int k=0; k <= strand.size()-3; k+=3) {
			String s = "";
			s+= Character.toString(strand.charAt(k))+Character.toString(strand.charAt(k+1))+Character.toString(strand.charAt(k+2));
			if (! map.containsKey(s)) map.put(s, 0);
			int temp = map.get(s);
			map.replace(s, temp+1);
		}
		int count = 0;
		for (String key : codons) {
			if (map.containsKey(key)) ret[count] = map.get(key);
			count++;
		}
		return ret;
	}
}
