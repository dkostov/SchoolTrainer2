import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;

// The ExerciseTask class has all the business logic for the exercise type - generating and evaluating tasks
public abstract class ExerciseTask {
    public String sText = ""; // the definition of the task, i.e. 3 + 5 = ?
    protected String sCondition = ""; // the internal representation of the task, good for evaluation
    public String sDescription = ""; // optional instructions
    protected Vector<String> slAnswers = new Vector<String>();
    protected Date dtStartTime = new Date();
    protected Date dtEndTime = new Date();
    protected boolean bSolved = false;

    // get the task instance back to initial state
    public void resetTask(){
        dtStartTime = new Date();
        dtEndTime = new Date();
        sText = "not set";
        sDescription = "not set either";
        slAnswers.clear();
        bSolved = false;
    }

    // how many times an answer was attempted
    public int getNumberOfAttempts() { return slAnswers.size(); }

    // how long did it take to answer this task
    public int getDuration() { return (int) (dtEndTime.getTime() - dtStartTime.getTime())/1000; }

    // was the task ever solved
    public boolean isSolved() { return bSolved; }

    // add an answer, checking it for correctness and updating the Solved status
    public boolean addAnswer(String sAnswer) {
        String sA = sAnswer.trim();

        bSolved = evalAnswer(sA);
        slAnswers.add(sA);
        dtEndTime = new Date();

        return isSolved();
    }

    abstract void generateTask(); // override this to generate a new, random task
    abstract boolean evalAnswer(String sAnswer);   // override this to check answer for a current instance
}
