public class CharLinkedListImpl extends  CharLinkedList {

    @Override
    public void add(char c) {
        CharLinkedListNodeImpl newnode = new CharLinkedListNodeImpl(c);
        if (this.first == null){
            this.first = newnode;
            this.last = newnode;
        }
        else{
            this.last.setNext(newnode);
            this.last = newnode;
        }
    }

    @Override
    public char firstChar() {
        if (this.first == null) {
            return (Character) null;
        } else {
            return this.first.getChar();
        }
    }
    @Override
    public int size() {
            int counter = 1;
            if (this.first != null){
                ICharLinkedListNode temp =(ICharLinkedListNode) this.first;
                while (temp.getNext()!= null){
                    temp = temp.getNext();
                    counter ++;
            }
                return counter;
            }
            else {
                return 0;
            }


        }



    @Override
    public void append(CharLinkedList toAppend) {
            if (this.first == null){
                this.first = toAppend.first;
            }
            else{
                this.last.setNext(toAppend.first);
            }
            this.last = toAppend.last;

    }
}