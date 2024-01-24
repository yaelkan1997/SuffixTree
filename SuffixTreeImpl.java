public class SuffixTreeImpl extends SuffixTree{

    public SuffixTreeImpl(String word) {
        super(word);
    }
    @Override
    public boolean contains(String subword) {
        char[] charArray = subword.toCharArray();
        if (this.getRoot().numOfOccurrences(charArray,0) > 0){
            return true;
        }
        return false;
    }

    @Override
    public int numOfOccurrences(String subword) {
        char[] charArray = subword.toCharArray();
        return this.getRoot().numOfOccurrences(charArray, 0);
    }
}
