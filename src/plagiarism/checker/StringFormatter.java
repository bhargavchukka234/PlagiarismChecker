package plagiarism.checker;

/**
 * Formats given strings, by excluding non alphanumeric characters, converting to lowercase and removing repeating spaces.
 */
class StringFormatter{

    public String[] formatStrings(String... originalStrings){

        String[] formattedStrings = new String[originalStrings.length];
        for(int i = 0;i < originalStrings.length;i++){

            formattedStrings[i] = formatString(originalStrings[i]);
        }
        return formattedStrings;
    }

    private String formatString(String original){

        String formattedString = original.toLowerCase().trim();
        formattedString = formattedString
                .replaceAll(Constants.spaceAlphanumericRegex, Constants.emptyString)
                .replaceAll(Constants.spaceRegex, Constants.space);
        return formattedString;
    }
}
