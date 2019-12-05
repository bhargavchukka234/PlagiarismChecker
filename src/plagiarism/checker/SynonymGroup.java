package plagiarism.checker;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a structured representation of synonyms from the given synonyms string. Maps each word to a common synonym string
 */
class SynonymGroup {

    private Map<String, String> wordToMappedString;

    private StringFormatter stringFormatter;

    public SynonymGroup(String synonyms , StringFormatter stringFormatter){

        this.stringFormatter = stringFormatter;
        init(synonyms);
    }

    private void init(String synonyms){

        wordToMappedString = new HashMap<>();
        String[] synonymGroups = stringFormatter.formatStrings(synonyms.split(Constants.pipelineRegex));

        for(int i = 0;i< synonymGroups.length;i++){

            String[] wordsInAGroup = synonymGroups[i].split(Constants.space);

            for(String word : wordsInAGroup) {
                wordToMappedString.put(word, wordsInAGroup[0]);
            }
        }
    }

    public String getMappedString(String s){

        return wordToMappedString.get(s) == null ? s : wordToMappedString.get(s);
    }
}