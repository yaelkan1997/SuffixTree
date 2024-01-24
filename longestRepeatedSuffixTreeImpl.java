public class longestRepeatedSuffixTreeImpl  extends  longestRepeatedSuffixTree{

    int maxLength = 0;
    SuffixTreeNode substringStartNode;
    /**
     * Constructs a suffix tree containing all suffices of a single word.
     * A dollar sign is appended to the end of the word
     *
     * @param word The word to create a tree of all its suffices
     */
    public longestRepeatedSuffixTreeImpl(String word) {
        super(word);
    }

    private void createLongestRepeatedSubstring(SuffixTreeNode node, int currLength, String sub){
        if(node.getChars() != null) {
            if (node.getNumOfChildren() != 0) {
                String longestRepeatedSubstring = getLongestRepeatedSubstring();
                if (maxLength < currLength) {
                    maxLength = currLength;
                    substringStartNode = node;
                } else if (maxLength == currLength) {
                    if (longestRepeatedSubstring.length() < sub.length() || (longestRepeatedSubstring.length() == sub.length() && longestRepeatedSubstring.charAt(0) < sub.charAt(0))) {
                        substringStartNode = node;
                    }
                }
            }
        }

        for (int i = 0; node.getChildren()[i] != null ; i++) {
            if (node.getChildren()[i].getNumOfChildren() > 0) {
                String ans = sub;
                for (char c : node.getChildren()[i].getChars()) {
                    ans = ans + c;
                }
                createLongestRepeatedSubstring(node.getChildren()[i], currLength + 1, ans);
            }
        }
    }

    @Override
    public void createLongestRepeatedSubstring() {
        createLongestRepeatedSubstring(this.root, 0, "");
    }

    @Override
    public String getLongestRepeatedSubstring() {
        if(substringStartNode == null){
            return "X";
        }
        String ans = substringStartNode.chars.toString();
        SuffixTreeNode currSuffixTreeNode = substringStartNode;
        while(currSuffixTreeNode.parent != null){
            CharLinkedList parentChars = currSuffixTreeNode.parent.getChars();
            if(parentChars != null) {
                ans = parentChars.toString() + ans;
            }
            currSuffixTreeNode = currSuffixTreeNode.parent;
        }
        return ans;
    }
}
