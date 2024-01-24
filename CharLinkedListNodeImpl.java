public class CharLinkedListNodeImpl implements ICharLinkedListNode{

    private ICharLinkedListNode next;
    private char value;

    public CharLinkedListNodeImpl(char value){
        this.value = value;
        this.next = null;
    }

    @Override
    public char getChar() {
        return this.value;
    }

    @Override
    public ICharLinkedListNode getNext() {
        return this.next;
    }

    @Override
    public void setNext(ICharLinkedListNode next) {
        this.next = next;
    }
}
