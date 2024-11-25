import ofofo.controllers.DiaryController;
import ofofo.controllers.EntryController;
import ofofo.data.models.Diary;
import ofofo.services.EntryService;
import ofofo.services.EntryServiceImpl;

import java.util.Scanner;
public class Main {
    private static DiaryController diaryController;
    private static EntryController entryController;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        goToMainMenu();
    }

    private static void goToMainMenu(){
        String menu = """
                Welcome to Your Diary App
                1 => Register
                2 => Login
                3 => Logout
                4 => Exit from Application 
                """;
        print(menu);

        char choice = scanner.next().charAt(0);

        switch (choice) {
            case '1': register();
            case '2': login();
            case '3': exit();
        }
    }

    private static void register(){
        diaryController = new DiaryController();
        String name = input("Enter ya name: ");
        String password = input("Enter ya password: ");
        print(diaryController.registerUser(name, password));
        goToMainMenu();
    }

    private static void login(){
        diaryController = new DiaryController();
        String username = input("Enter ya username: ");
        String password = input("Enter ya password: ");
        try {
            diaryController.loginUser(username, password);
            insideLogin(username);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            goToMainMenu();
        }
    }

    private static void insideLogin(String currentUsername) {
        String option = """
                1. Create Entry
                2. Find Entry By Id
                3. Find All Entries By Id
                4. Count Number Of Entries
                5. Update Entry
                6. Delete Entry By id
                7. Delete Entry By title
                8. Delete All Entries
                9. Logout
                """;
            int result = 0;
        try {
             result = Integer.parseInt(input(option));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            insideLogin(currentUsername);
        }


        switch (result) {
            case 1: createEntry(currentUsername);
            case 2: findEntryById(currentUsername);
            case 3: findAllEntriesById(currentUsername);
            case 4: countNumberOfEntries(currentUsername);
            case 2: deleteEntryById(currentUsername);
            case 3: deleteEntryByTitle(currentUsername);
            case 4: deleteAllEntries(currentUsername);
            case 5: logout(currentUsername);
        }
    }

    private static void deleteAllEntries(String currentUsername) {
        entryController.deleteAll(currentUsername);
    }

    private static void deleteEntryByTitle(String currentUsername) {
        entryController.deleteAllEntriesByTitle(currentUsername);
    }

    private static void deleteEntryById(String currentUsername) {
        entryController = new EntryController();
    }

    private static void logout(String currentUsername) {
        diaryController = new DiaryController();
        String username = input("Enter ya username: ");
        diaryController.logOutUser(username);
    }

    private static void createEntry(String currentUsername){
        String name = input("Enter ya name: ");
        String password = input("Enter ya password: ");
        diaryController.loginUser(name, password);
        String title = input("Enter title: ");
        String description = input("Enter description: ");
        entryController.createEntry(name, title, description);
    }

    private static void exit(){
        System.exit(0);
    }

    private static String input(String prompt){
        System.out.println(prompt);
        return scanner.next();
    }

    private static void print(String prompt){
        System.out.println(prompt);
    }


}
