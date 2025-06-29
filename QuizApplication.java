import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    static class Question {
        String question;
        String[] options;
        String correctAnswer;

        Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private static ArrayList<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static ArrayList<Boolean> answerResults = new ArrayList<>();
    private static ArrayList<String> userAnswers = new ArrayList<>(); // Store user's selected answers
    private static boolean answered = false;
    private static String userInput = "";

    public static void main(String[] args) {
        initializeQuestions();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            answered = false;
            userInput = "";
            System.out.println("\nQuestion " + (i + 1) + ": " + questions.get(i).question);
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + questions.get(i).options[j]);
            }

            // Set up timer for 10 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int timeLeft = 10; // Changed to 10 seconds

                @Override
                public void run() {
                    if (timeLeft > 0) {
                        System.out.print("\rTime left: " + timeLeft + " seconds,Enter your answer (1-4): ");
                        timeLeft--;
                    } else {
                        System.out.println("\nTime's up!");
                        timer.cancel();
                        answered = true;
                    }
                }
            }, 0, 1000);

            // Get user input
            System.out.print("\nEnter your answer (1-4): ");
            while (!answered) {
                if (scanner.hasNextLine()) {
                    userInput = scanner.nextLine();
                    answered = true;
                    timer.cancel();
                }
            }

            // Check and store answer
            checkAnswer(i, userInput);
        }

        // Display results
        showResults();
        scanner.close();
    }

    private static void initializeQuestions() {
        questions.add(new Question("Who is the current Prime Minister of India (as of 2025)?",
                new String[]{"Narendra Modi", "Amit Shah", "Rahul Gandhi", "Yogi Adityanath"},
                "Narendra Modi"));
        questions.add(new Question("What is India's national bird?",
                new String[]{"Peacock", "Parrot", "Eagle", "Sparrow"},
                "Peacock"));
        questions.add(new Question("What is India's national song?",
                new String[]{"Vande Mataram", "Jana Gana Mana", "Saare Jahan Se Achha", "Maa Tujhe Salaam"},
                "Vande Mataram"));
        questions.add(new Question("Who was India's first Prime Minister?",
                new String[]{"Jawaharlal Nehru", "Mahatma Gandhi", "Sardar Patel", "Indira Gandhi"},
                "Jawaharlal Nehru"));
        questions.add(new Question("What is India's national animal?",
                new String[]{"Bengal Tiger", "Lion", "Elephant", "Leopard"},
                "Bengal Tiger"));
        questions.add(new Question("What is India's national flower?",
                new String[]{"Lotus", "Rose", "Jasmine", "Sunflower"},
                "Lotus"));
        questions.add(new Question("Who became Prime Minister of India in 2014?",
                new String[]{"Narendra Modi", "Manmohan Singh", "Atal Bihari Vajpayee", "Rajiv Gandhi"},
                "Narendra Modi"));
        questions.add(new Question("What is India's national emblem?",
                new String[]{"Ashoka Chakra", "Tricolor Flag", "Banyan Tree", "Lotus Temple"},
                "Ashoka Chakra"));
        questions.add(new Question("What is India's national anthem?",
                new String[]{"Jana Gana Mana", "Vande Mataram", "Saare Jahan Se Achha", "Ae Mere Watan"},
                "Jana Gana Mana"));
        questions.add(new Question("Which state is the residence of the Prime Minister of India located in?",
                new String[]{"New Delhi", "Mumbai", "Kolkata", "Chennai"},
                "New Delhi"));
    }

    private static void checkAnswer(int questionIndex, String userInput) {
        Question q = questions.get(questionIndex);
        String selectedAnswer = "None (Time's up or invalid input)";
        boolean isCorrect = false;

        try {
            int choice = Integer.parseInt(userInput) - 1;
            if (choice >= 0 && choice < 4) {
                selectedAnswer = q.options[choice];
                isCorrect = selectedAnswer.equals(q.correctAnswer);
            }
        } catch (NumberFormatException e) {
            // Invalid input treated as incorrect
        }

        // Store results
        answerResults.add(isCorrect);
        userAnswers.add(selectedAnswer);

        // Provide immediate feedback
        if (isCorrect) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. Your answer: " + selectedAnswer);
            System.out.println("Correct answer: " + q.correctAnswer);
        }
    }

    private static void showResults() {
        System.out.println("\n=== Quiz Results ===");
        System.out.println("Final Score: " + score + "/" + questions.size());
        System.out.println("\nSummary:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.question);
            System.out.println("Correct Answer: " + q.correctAnswer);
            System.out.println("Your Answer: " + userAnswers.get(i));
            System.out.println("Result: " + (answerResults.get(i) ? "Correct" : "Incorrect") + "\n");
        }
    }
}