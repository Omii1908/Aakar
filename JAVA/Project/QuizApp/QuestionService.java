package Project.QuizApp;

import java.util.Scanner;

public class QuestionService {

    Question[] questions = new Question[5];
    String selection[] = new String[5];

    public QuestionService() {
        questions[0] = new Question(1, "Size of int", "2", "6", "8", "1", "4");
        questions[1] = new Question(2, "Size of double", "2", "6", "8", "1", "8");
        questions[2] = new Question(3, "Size of char", "2", "6", "8", "1", "2");
        questions[3] = new Question(4, "Size of long", "2", "6", "8", "1", "8");
        questions[4] = new Question(5, "Size of char", "2", "6", "8", "1", "1");
    }

    public void playQuiz() {
        int i = 0;
        for (Question q : questions) {
            System.out.print("Question no. : " + q.getId());
            System.out.println("  " + q.getQuestion());
            System.out.print("\t1. " + q.getOpt1());
            System.out.println("\t\t2. " + q.getOpt2());
            System.out.print("\t3. " + q.getOpt3());
            System.out.println("\t\t4. " + q.getOpt4());
            System.out.print("Your Answer : ");
            try (Scanner scan = new Scanner(System.in)) {
                selection[i] = scan.nextLine();
            }
            i++;
        }

        for (String s : selection) {
            System.out.println(s);
        }
    }

    public void printScore() {
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            Question que = questions[i];
            String actualanswer = que.getAnswer();
            String useranser = selection[i];

            if (actualanswer.equals(useranser)) {
                score++;
            }
        }
        System.out.println("Your score is : " + score);
    }
}