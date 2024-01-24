/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;

	/**
	 * This entry function will test all classes created in this assignment.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		// Each function here should test a different class.

		// CharLinkedList
		testCharLinkedList();

		// SuffixTreeNode
		testSuffixTreeNode();

		//longestRepeatedSuffixTree
		testLongestRepeatedSuffixTree();

		//SuffixTree
		testSuffixTree();



		/* TODO - continue writing a function for each class */

		testCharlinkedlistnode();


		// Notifying the user that the code have passed all tests.
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked.
	 * In addition, if a test fails the function will print the error message.
	 *
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;

		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: " + msg);
		}
	}

	/**
	 * Checks the CharLinkedList class.
	 */
	private static void testCharLinkedList() {
		CharLinkedList list = new CharLinkedListImpl();
		test(list.size() == 0, "The size of the list should be 0");
		list.add('a');
		test(list.size() == 1, "The size of the list should be 1");
		test(list.firstChar() == 'a', "The first char should be 'a'");
		CharLinkedList list2 = new CharLinkedListImpl();
		list2.add('w');
		list2.add('k');
		list2.append(list);
		test(list2.size() == 3, "The size of list2 after appending list should be 3");
		test(list2.firstChar() == 'w', "The first char should be 'w'");
		CharLinkedList list3 = new CharLinkedListImpl();
		list3.append(list2);
		test(list3.size() == 3, "The size of list3 after appending list2 should be 3");
		test(list3.firstChar() == 'w', "The first char should be 'w'");
	}

	/**
	 * Checks the SuffixTreeNode class.
	 */
	private static void testSuffixTreeNode() {
		// test empty root
		SuffixTreeNode node = new SuffixTreeNodeImpl();
		test(node.getTotalWordLength() == 0, "node word length should be 0");
		test(node.getNumOfChildren() == 0, "node num of children should be 0");
		test(node.getChars() == null, "nodes chars should be null");
		test(node.getChildren().length == 28, "node children  should be in a size of alpabet + 2 = 28 ");
		test(node.getDescendantLeaves() == 0, "nodes DescendantLeaves should be 0");


		// test search, binary search, shiftChildren and addChild
		SuffixTreeNode child1 = new SuffixTreeNodeImpl(CharLinkedList.from("abc"), node);
		SuffixTreeNode child2 = new SuffixTreeNodeImpl(CharLinkedList.from("bcd"), node);
		SuffixTreeNode child3 = new SuffixTreeNodeImpl(CharLinkedList.from("hello"), node);
		SuffixTreeNode child4 = new SuffixTreeNodeImpl(CharLinkedList.from("mmmmm"), node);
		node.setChildren(new SuffixTreeNode[]{child1, child2, child3, child4, null, null, null, null}, 4);
		SuffixTreeNode child41 = new SuffixTreeNodeImpl(CharLinkedList.from("11"), child4);
		SuffixTreeNode child42 = new SuffixTreeNodeImpl(CharLinkedList.from("12"), child4);
		SuffixTreeNode child43 = new SuffixTreeNodeImpl(CharLinkedList.from("13"), child4);
		SuffixTreeNode child31 = new SuffixTreeNodeImpl(CharLinkedList.from("13"), child3);

		// binary search
		test(node.binarySearch('b', 0, 3) == child2, "search for 'b' should find child2");
		test(node.binarySearch('a', 0, 3) == child1, "search for 'a' should be find child1");
		test(node.binarySearch('l', 0, 3) != child3, "search for 'l' should not find in child3");


		// search
		test(node.search('a') == child1, "search for 'a' should return child1");
		test(node.search('x') == null, "search for 'x' should fail");
		test(node.search('l') == null, "search for 'l' should fail");


		// add child
		SuffixTreeNode child5 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), node);
		node.addChild(child5);
		test(node.getChildren()[2] == child5, "3rd child should be child5");
		test(node.getNumOfChildren() == 5, "node's should have 5 children");
		test(child5.getParent() == node, "node should be child 5 parent");
		SuffixTreeNode child51 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child5);
		SuffixTreeNode child52 = new SuffixTreeNodeImpl(CharLinkedList.from("yog"), child5);
		SuffixTreeNode child53 = new SuffixTreeNodeImpl(CharLinkedList.from("fog"), child5);
		child5.addChild(child51); child5.addChild(child52); child5.addChild(child53);
		SuffixTreeNode child520 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child52);
		SuffixTreeNode child521 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child52);
		SuffixTreeNode child530 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child53);
		SuffixTreeNode child531 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child53);
		SuffixTreeNode child532 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), child53);
		child52.addChild(child520); child52.addChild(child521);
		child53.addChild(child530); child53.addChild(child531); child53.addChild(child532);
		test(child5.descendantLeaves==6,"the number of leafs is 6");
		test(child5.getNumOfChildren()==3,"3");
		test(child520.getParent()==child52,"aba");
		test(child530.getTotalWordLength()==9,"9");
		test(child5.getChildren()[2]==child52,"y");
		test(child530.getNumOfChildren()==0,"no child");
		test((child531==child532)==false,"different storage memory");





		//add Suffix
		child1.addSuffix("rol".toCharArray(), 0);
		child1.addSuffix("rol".toCharArray(), 1);
		child1.addSuffix("rol".toCharArray(), 2);
		test(child1.getNumOfChildren() == 3, "num of children of child 1 should be 3");
		test(child1.search('r') == child1.getChildren()[2], "should find in third place");
		SuffixTreeNode nodenode = new SuffixTreeNodeImpl();



	}

	private static void testLongestRepeatedSuffixTree(){
		testLongestRepeated("mississippi", "issi");
		testLongestRepeated("abc", "X");;
		testLongestRepeated("abbc", "b");
		testLongestRepeated("aaa", "aa");
		testLongestRepeated("", "X");
	}

	private static void testLongestRepeated(String word, String expected) {
		test(new longestRepeatedSuffixTreeImpl(word).getLongestRepeatedSubstring().equals(expected), "Longest repeated substring should be " + expected);
	}
	//MORE TESTS-

	//CHARLINKEDLISTNODE
	public static void testCharlinkedlistnode() {
		ICharLinkedListNode listnode1 = new CharLinkedListNodeImpl('a');
		ICharLinkedListNode listnode2 = new CharLinkedListNodeImpl('b');
		ICharLinkedListNode listnode3 = new CharLinkedListNodeImpl('c');
		listnode1.setNext(listnode2);
		listnode2.setNext(listnode3);
		listnode3.setNext(listnode1);
		test((listnode1.getNext() == listnode2), "node1.next= should be node 2");
		test((listnode1.getNext().getNext() == listnode3), "node1.next.next= should be node 3");
		test((listnode1.getChar() == 'a'), "node1.chat= a");
		test((listnode2.getChar() == 'b'), "node1.chat= b");
		test(listnode1.getNext().getNext().getNext()==listnode1,"list node 1 should be the same ");
	}

	private static void testSuffixTree() {
		//numOfOccurrences
		SuffixTree tree = new SuffixTreeImpl("mississippi");
		test(tree.numOfOccurrences("ssi") == 2, "ssi should be 2 times in mississippi");
		test(tree.numOfOccurrences("i") == 4, "i should be 4 times in mississippi");
		test(tree.numOfOccurrences("mississippi") == 1, "mississippi should be 1 times in mississippi");
		test(tree.numOfOccurrences("a") == 0, "a should be 0 times in mississippi");
		test(tree.numOfOccurrences("$") == 0, "$ should be 0 times in mississippi");
		SuffixTree newtree = new SuffixTreeImpl("aaa");
		test(newtree.numOfOccurrences("a") == 3, "a should be 3 times in aaa");
		test(newtree.numOfOccurrences("aa") == 2, "aa should be 2 times in aaa");

		//contain
		test(tree.contains("ssi") == true, "mississippi contains issi");
		test(tree.contains("a") == false, "mississippi doesn't contains a");
		test(newtree.contains("aa") == true, "mississippi contains aa");
	}




}