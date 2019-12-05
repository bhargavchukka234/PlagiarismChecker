package plagiarism.checker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Iterator to generate tuples, given text and tuple size
 */
class TupleGenerator implements Iterator<List<String>> {

    private String[] words;
    private int tupleSize;
    private SynonymGroup synonymGroup;
    private LinkedList<String> tuple;
    private int currentEndIndex;

    /**
     *
     * @param text String which is iterated over to get tuples of tupleSize word count
     * @param tupleSize Number of words in each tuple
     * @param synonymGroup To match synonyms and replace with mapped string from this object
     */
    public TupleGenerator(String text, int tupleSize, SynonymGroup synonymGroup){

        this.words = text.split(Constants.space);
        this.tupleSize = tupleSize;
        this.synonymGroup = synonymGroup;
        this.tuple = new LinkedList<>();

        init();
    }

    private void init(){

        if(words.length < tupleSize){
            return;
        }

        for(currentEndIndex = 0; currentEndIndex < tupleSize; currentEndIndex++){

            tuple.add(synonymGroup.getMappedString(words[currentEndIndex]));
        }
        currentEndIndex--;
    }

    @Override
    public boolean hasNext() {
        if(tupleSize == 0 || words.length < tupleSize){
            return false;
        }
        if(currentEndIndex < words.length){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<String> next() {

        List<String> listCopy = new LinkedList<>(tuple);
        currentEndIndex++;
        if(currentEndIndex < words.length){

            tuple.removeFirst();
            tuple.add(synonymGroup.getMappedString(words[currentEndIndex]));
        }
        return listCopy;
    }

    public int getWordCount(){

        return words.length;
    }
}
