import java.util.Scanner;
import java.util.ArrayList;

public class Johan {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Johan");
        System.out.println("What can I do for you?");
        ArrayList<String> words = new ArrayList<>();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                int count = 0;
                for (String word : words) {
                    System.out.println(++count + ". " + word);
                }
            } else {
                System.out.println("added: " + input);
                words.add(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
