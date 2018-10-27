public class LinkStrand implements IDnaStrand {

	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String str) {
		initialize(str); 
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		myAppends+=1;
		mySize+= dna.length();
		Node curr = myFirst;
		while (curr.next != null) {
			curr= curr.next;
		}
		curr.next = new Node(dna);
		myLast = curr.next;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder first = new StringBuilder(this.myFirst.info);
		first.reverse();
		LinkStrand copy = new LinkStrand(first.toString());
		LinkStrand temp;
		Node curr = this.myFirst;
		for (int i = 0; i<myAppends; i++) {
			curr = curr.next;
			StringBuilder tempInfo = new StringBuilder(curr.info);
			tempInfo.reverse();
			Node newFirst = new Node(tempInfo.toString());
			temp = copy;
			copy.myFirst = newFirst;
			copy.myFirst.next = temp.myFirst;
		}
		return copy;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		int count = myIndex, dex = myLocalIndex;
		Node list = myCurrent;
		
		if (index<myIndex) {
			count = 0;
			dex = 0;
			list = myFirst;
		}
		
		if (index>=mySize || index<0) throw new IndexOutOfBoundsException();
		
		while (count != index) {
				count++;
				dex++;
				if (dex>= list.info.length()) {
					dex = 0;
					list = list.next;
				}
		}
		myIndex = count;
		myLocalIndex = dex;
		myCurrent = list;
		return list.info.charAt(dex);
	}
	
	@Override
	public String toString() {
		Node curr = myFirst;
		StringBuilder ret = new StringBuilder();
		ret.append(myFirst.info);
		while (curr.next!=null) {
			curr = curr.next;
			ret.append(curr.info);
		}
		return ret.toString();
	}
}
