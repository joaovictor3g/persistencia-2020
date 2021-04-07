import controllers.BookController;
import models.Book;
import org.elasticsearch.client.RestHighLevelClient;
import spring.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);
    public static int menu() {
        int choice = 0;
        System.out.println(
                "1 - Add a book                    2 - Search a book by title\n" +
                "3 - Search book by author         4 - Amount books by category\n" +
                "5 - See categories price average  0 - Exit ");
        choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    public static void main(String[] args) {

        BookController bookController = new BookController();
        Book book = new Book();

        int id = 0;
        float value = 0;
        String title, category, publishYear;
        List<String> authors = null;

        try {
            RestHighLevelClient client = Config.clientConfiguration();
            int choice = menu();

            while (true) {
                switch (choice) {
                    case 1: {
                        System.out.println("Type ID: ");
                        id = input.nextInt();
                        input.nextLine();
                        book.setId(id);

                        System.out.println("Type title: ");
                        title = input.nextLine();
                        book.setTitle(title);

                        System.out.println("Type publish year: ");
                        publishYear = input.nextLine();
                        book.setPublishYear(publishYear);

                        int howManyAuthors = 0;
                        System.out.println("How many authors?");
                        howManyAuthors = input.nextInt();
                        input.nextLine();
                        authors = new ArrayList<>();

                        System.out.println("Type name(s)");
                        for(int i = 0; i < howManyAuthors; i++) {
                            authors.add(input.nextLine());
                        }
                        book.setAuthors(authors);

                        System.out.println("Type category");
                        category = input.nextLine();
                        book.setCategory(category);

                        System.out.println("Type price");
                        value = input.nextFloat();
                        book.setValue(value);

                        bookController.create(client, book);
                        choice = menu();
                    }
                    break;

                    case 2: {
                        String valueKey;
                        System.out.println("Type a title");
                        valueKey = input.nextLine();

                        bookController.searchByTitle(client, "title", valueKey);

                        choice = menu();
                    }
                    break;

                    case 3: {
                        String valueKey;
                        System.out.println("Type an author name");
                        valueKey = input.nextLine();

                        bookController.searchByAuthor(client, "authors", valueKey);
                        choice = menu();
                    }
                    break;

                    case 4: {
                        bookController.HowManyBooksByCategory(client);
                        choice = menu();
                    }
                    break;

                    case 5: {
                        bookController.getAveragePriceByCategory(client);
                        choice = menu();
                    }
                    break;
                    default: {
                        System.exit(0);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
