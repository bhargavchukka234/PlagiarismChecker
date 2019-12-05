package plagiarism.checker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlagiarismSolver {


    //to format given strings
    private StringFormatter stringFormatter;

    //Object to match synonym words to mapped string
    private SynonymGroup synonymGroup;

    //Set contains all tuples from original text
    private Set<List<String>> originalTextTupleSet;


    private String[] inputStrings;

    /**
     *
     * @param inputStrings format is {synonym group , original text , suspect text}
     */
    public PlagiarismSolver(String[] inputStrings){
        this.inputStrings = inputStrings;
    }
    /**
     * @param tupleSize Indicates the consecutive word length to match
     * @return returns match percentage of suspect text tuples with original text considering synonyms
     */
    public double solve(int tupleSize) {

        if(inputStrings.length != 3){
            throw new IllegalArgumentException("Expected size of input string array is 3, given array length : " + inputStrings.length);
        }
        stringFormatter = new StringFormatter();
        synonymGroup = new SynonymGroup(inputStrings[0], stringFormatter);

        String originalText = stringFormatter.formatStrings(inputStrings[1])[0];
        String suspectText = stringFormatter.formatStrings(inputStrings[2])[0];

        originalTextTupleSet = new HashSet<>();
        populateOriginalTextTupleSet(originalText, tupleSize);
        return getMatchPercentage(suspectText, tupleSize);
    }


    /**
     *
     * Generates tuples of size tupleSize from the originalText and stores in a set for further reference
     *
     * @param originalText String which is iterated over to get tuples of tupleSize word count
     * @param tupleSize Number of words in each tuple
     */
    private void populateOriginalTextTupleSet(String originalText, int tupleSize) {

        TupleGenerator tupleGenerator = new TupleGenerator(originalText, tupleSize, synonymGroup);
        while (tupleGenerator.hasNext()) {

            originalTextTupleSet.add(tupleGenerator.next());
        }
    }

    /**
     *
     * @param suspectText String which is iterated over to get tuples of tupleSize word count
     * @param tupleSize Number of words in each tuple
     * @return Gets match percentage of suspect text tuples by checking existence of each tuple in originalTextTupleSet
     */
    private double getMatchPercentage(String suspectText , int tupleSize) {

        TupleGenerator tupleGenerator = new TupleGenerator(suspectText, tupleSize, synonymGroup);
        int matchPercentage = 0;
        while(tupleGenerator.hasNext()){

            List<String> tuple = tupleGenerator.next();
            if(originalTextTupleSet.contains(tuple)){

                matchPercentage++;
            }
        }
        int suspectTextWordCount = tupleGenerator.getWordCount();
        return matchPercentage == 0 ? 0 : 1.0 * matchPercentage * 100/(suspectTextWordCount - tupleSize + 1);
    }
}
