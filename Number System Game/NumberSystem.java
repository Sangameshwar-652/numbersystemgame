import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
}

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

class QuizSession {
    private User user;
    private List<QuizQuestion> questions;
    private int timeLimitInSeconds;

    public QuizSession(User user, List<QuizQuestion> questions, int timeLimitInSeconds) {
        this.user = user;
        this.questions = questions;
        this.timeLimitInSeconds = timeLimitInSeconds;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int remainingTimeInSeconds = timeLimitInSeconds;

        System.out.println("Welcome, " + user.getUsername() + "!");
        System.out.println("You have " + timeLimitInSeconds + " seconds for the quiz.");

        for (QuizQuestion question : questions) {
            System.out.println("\n" + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int selectedOption = scanner.nextInt();

            if (question.isCorrect(selectedOption)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }

            // Subtract the time taken for this question
            remainingTimeInSeconds -= 5; // Simulating time for each question

            // Check if time is up
            if (remainingTimeInSeconds <= 0) {
                System.out.println("Time's up!");
                break;
            }
        }

        System.out.println("\nQuiz session ended. Your score: " + score);

        // Close the Scanner when done
        scanner.close();
    }
}

public class NumberSystem {
    public static void main(String[] args) {
        User user = new User("username", "password");
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(
            new QuizQuestion("What is the capital of India?", Arrays.asList("Goa", "New Delhi", "Chennai"), 2));
        // Add more questions here...

        int timeLimitInSeconds = 60; // Total time for the quiz

        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (user.getUsername().equals(username) && user.checkPassword(password)) {
            QuizSession quizSession = new QuizSession(user, questions, timeLimitInSeconds);
            quizSession.start();
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }

        // Close the Scanner when done
        scanner.close();
    }
}
