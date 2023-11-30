import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program
{

    private final static int NEW_RECIPE = 1;
    private final static int VIEW_RECIPE = 2;
    private final static int SEARCH_RECIPE = 3;
    private final static int EXIT_APP = 9;

    public static void main(String[] unused)
    {
        List<Recipe>    AllRecipes = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(".recipe.ser"))) {
            AllRecipes = (List<Recipe>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (AllRecipes == null)
            AllRecipes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print(" ~ Hello and welcome to your personal recipe app! ~ \n");
        do {
            System.out.print("""
            === MAIN MENU ===
            Please pick one of the following options by using your keyboard:
            1. Add new recipe
            2. View, edit and/or delete saved recipes
            3. Search for a recipe with specific requirements
            9. Exit the application
            """);

            int input = scanner.nextInt();
            switch (input) {
                case NEW_RECIPE:
                    AllRecipes.add(new Recipe());
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(".recipe.ser")))
                    {
                        objectOutputStream.writeObject(AllRecipes);
                    } catch (IOException e)
                    {
                        //noinspection CallToPrintStackTrace
                        e.printStackTrace();
                    }
                    System.out.print("Recipe successfully added to your recipe book! ");
                    break;
                case VIEW_RECIPE:
                    AllRecipes = ManageRecipes.showRecipes(AllRecipes);
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(".recipe.ser")))
                    {
                        objectOutputStream.writeObject(AllRecipes);
                    } catch (IOException e)
                    {
                        //noinspection CallToPrintStackTrace
                        e.printStackTrace();
                    }
                    break;
                case SEARCH_RECIPE:
                    SearchRecipes.searchSelect(AllRecipes);
                    break;
                case EXIT_APP:
                    System.out.println("Enjoy your meal, see you next time!");
                    System.exit(0);
                default:
                    System.out.print("Error: invalid input, please try again. ");
            }
        } while (true);
    }
}
