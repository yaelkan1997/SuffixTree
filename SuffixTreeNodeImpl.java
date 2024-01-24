public class SuffixTreeNodeImpl extends SuffixTreeNode {

    @Override
    public SuffixTreeNode search(char c) {
        return this.binarySearch(c, 0, this.numOfChildren - 1);
    }

    public SuffixTreeNodeImpl() {
        super();
    }

    public SuffixTreeNodeImpl(CharLinkedList charLinkedList, SuffixTreeNode suffixTreeNode) {
        super(charLinkedList, suffixTreeNode);
    }

    @Override
    public SuffixTreeNode binarySearch(char target, int left, int right) {
        while (left <= right) {
            int middle = (int) ((left + right) / 2);
            if (this.getChildren()[middle] == null)
                return null;
            if (this.getChildren()[middle].getChars().firstChar() == target)
                return this.getChildren()[middle];
            else {
                if (target < this.getChildren()[middle].getChars().firstChar())
                    right = middle - 1;
                else
                    left = middle + 1;
            }
        }
        return null;
    }

    @Override
    public void shiftChildren(int until) {
        int counter = this.getNumOfChildren() - 1;
        while (until <= counter) {
            this.children[counter + 1] = this.children[counter];
            counter--;
        }
    }

    @Override
    public void addChild(SuffixTreeNode node) {
        int place = 0;
        int childnum = this.numOfChildren;
        if (childnum ==0){
            this.children[0] = node;

        }
        else if (this.children[childnum-1].chars.firstChar() < node.chars.firstChar()){
            this.children[childnum] = node;

        }
        else{
            for(int i = 0;i<childnum;i++) {
                if (this.children[i].chars.firstChar() < node.chars.firstChar())
                    place++;
                else
                    break;
            }
            this.shiftChildren(place);
            this.children[place] = node;

        }
        this.numOfChildren = childnum+1;
        node.parent = this;
        this.children[place].totalWordLength = this.totalWordLength + this.children[place].chars.size();
        SuffixTreeNode parentNow = this.children[place].getParent();
        if (childnum > 0){
            while (parentNow != null){
                parentNow.descendantLeaves ++;
                parentNow = parentNow.getParent();
            }
        }
        else{
            this.descendantLeaves =1;
        }
        this.numOfChildren = childnum+1;
    }





    private void addSuffixHelper(SuffixTreeNode node, char[] word, int from) {
        if (from == word.length) {
            return;
        }
        SuffixTreeNode childNode = node.search(word[from]);
        if (childNode != null) {
            addSuffixHelper(childNode, word, from + 1);
        }
        CharLinkedList charLinkedList = new CharLinkedListImpl();
        charLinkedList.add(word[from]);
        SuffixTreeNode newNode = new SuffixTreeNodeImpl(charLinkedList, node);
        node.addChild(newNode);
        addSuffixHelper(newNode, word, from + 1);
        this.descendantLeaves++;


    }

    private void addSuffix(SuffixTreeNode node, char[] word, int from) {
        SuffixTreeNode newNode = node.search(word[from]);
        if (newNode == null) {
            addSuffixHelper(node, word, from);
        } else {
            addSuffix(newNode, word, from + 1);
        }
    }

    public void addSuffix(char[] word, int from) {
        if (word.length == 1) {
            return;
        }
        addSuffix(this, word, from);
    }

    private void compress(SuffixTreeNode node) {
        if (node.getNumOfChildren() == 1) {
            SuffixTreeNode suffixTreeNodeSon = node.getChildren()[0];
            if (suffixTreeNodeSon != null) {
                CharLinkedList charLinkedListToAppend = suffixTreeNodeSon.getChars();
                suffixTreeNodeSon.parent = node;
                node.chars.append(charLinkedListToAppend);
                node.setChildren(suffixTreeNodeSon.getChildren(), suffixTreeNodeSon.numOfChildren);
            }
            compress(node);
        } else {
            for (int i = 0; i < node.getNumOfChildren(); i++) {
                compress(node.children[i]);
            }
        }
    }

    @Override
    public void compress() {

        compress(this);
    }

    @Override
    public int numOfOccurrences(char[] subword, int from) {
        if (from == subword.length)
            return this.descendantLeaves;
        if (this.search(subword[from]) == null)
            return 0;
        SuffixTreeNode stand = this.search(subword[from]);
        ICharLinkedListNode checker = this.search(subword[from]).getChars().getFirst();
        while ((from < subword.length) && (checker != null) && (checker.getChar() == subword[from])) {
            if (from == subword.length)
                return stand.descendantLeaves;
            from++;
            checker = checker.getNext();
        }
        if (from == subword.length)
            return stand.descendantLeaves;
        else if (checker == null)
            return stand.numOfOccurrences(subword, from);
        else
            return 0;
    }

}