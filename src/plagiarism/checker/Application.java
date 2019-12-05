package plagiarism.checker;

public class Application {

    public static void main(String[] args){

        String[] input ={"coding programming|problem challenge.","this wa\'s a nice coding challenge","This was indeed a nice programming problem"};

        PlagiarismSolver plagiarismSolver = new PlagiarismSolver(input);
        double matchPercentage = plagiarismSolver.solve(3);
        System.out.println("Match percentage for given input and tuple size is : " + matchPercentage);
    }
}
