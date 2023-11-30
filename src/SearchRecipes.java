import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchRecipes {
    public static void searchSelect(List<Recipe> recipes)
    {
        if (recipes.size() == 0){
            System.out.println("You have no recipes yet.");
            return ;
        }
        System.out.println("Please indicate on which value you would like to search by pressing the corresponding number:");
        System.out.println("1. Recipe name");
        System.out.println("2. Ingredients");
        System.out.println("3. Instructions");
        System.out.println("4. Vegetarian or vegan");
        System.out.println("5. Servings");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 5)
        {
            System.out.print("Invalid value. Please fill in a number bigger than 0 and smaller than 8. Please try again: ");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
                searchRecipeName(recipes);
                break;
            case 2:
                searchIngredients(recipes);
                break;
            case 3:
                searchInstructions(recipes);
                break;
            case 4:
                searchVeg(recipes);
                break;
            case 5:
                searchServings(recipes);
                break;
            default:
                break;
        }
    }

    public static void  searchServings(List<Recipe> recipes)
    {
        List<Recipe>    FilteredRecipes = new ArrayList<>();
        System.out.println("How many people can be served?");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int i = 1;
        for (Recipe recipe : recipes) {
            if (recipe.getServings() == input) {
                System.out.println("Recipe found: " + i + ". " + recipe.getRecipe());
                FilteredRecipes.add((recipe));
                i++;
            }
        }
        if (FilteredRecipes.size() == 0)
        {
            System.out.println("No recipes in your book meet the criteria. You will return to the main menu now.");
            return ;
        }
        if (extraSearch(FilteredRecipes, scanner) == 1)
            return ;
        selectRecipeToView(FilteredRecipes, scanner);
    }

    public static void  searchVeg(List<Recipe> recipes)
    {
        List<Recipe>    FilteredRecipes = new ArrayList<>();
        System.out.println("Should the recipe selected be 1. vegetarian or 2. vegan? Press 1 or 2");
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        do {
            input = scanner.nextInt();
            int i = 1;
            switch (input) {
                case 1:
                    for (Recipe recipe : recipes) {
                        if (recipe.getVegetarian() == true) {
                            System.out.println("Recipe found: " + i + ". " + recipe.getRecipe());
                            FilteredRecipes.add((recipe));
                            i++;
                        }
                    }
                    break;
                case 2:
                    for (Recipe recipe : recipes) {
                        if (recipe.getVegan() == true) {
                            System.out.println("Recipe found: " + i + ". " + recipe.getRecipe());
                            FilteredRecipes.add((recipe));
                            i++;
                        }
                    }
                    break;
                default:
                    System.out.print("Wrong input, please enter 1 for vegetarian or 2 for vegan. ");

            }
        } while (input != 1 && input != 2);
        if (FilteredRecipes.size() == 0)
        {
            System.out.println("No recipes in your book meet the criteria. You will return to the main menu now.");
            return ;
        }
        if (extraSearch(FilteredRecipes, scanner) == 1)
            return ;
        selectRecipeToView(FilteredRecipes, scanner);
    }
    public static void searchInstructions(List<Recipe> recipes)
    {
        List<Recipe>    FilteredRecipes = new ArrayList<>();
        System.out.println("What should specifically be mentioned in the instructions?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int i = 1;
        for (Recipe recipe : recipes) {
            if (recipe.getRecipe().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Recipe found: " + i + ". " + recipe.getRecipe());
                FilteredRecipes.add((recipe));
                i++;
            }
        }
        if (FilteredRecipes.size() == 0)
        {
            System.out.println("No recipes in your book meet the criteria. You will return to the main menu now.");
            return ;
        }
        if (extraSearch(FilteredRecipes, scanner) == 1)
            return ;
        selectRecipeToView(FilteredRecipes, scanner);
    }
    public static void searchRecipeName(List<Recipe> recipes)
    {
        List<Recipe>    FilteredRecipes = new ArrayList<>();
        System.out.println("What should be in the recipe its name?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int i = 1;
        for (Recipe recipe : recipes) {
            if (recipe.getRecipe().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Recipe found: " + i + ". " + recipe.getRecipe());
                FilteredRecipes.add((recipe));
                i++;
            }
        }
        if (FilteredRecipes.size() == 0)
        {
            System.out.println("No recipes in your book meet the criteria. You will return to the main menu now.");
            return ;
        }
        if (extraSearch(FilteredRecipes, scanner) == 1)
            return ;
        selectRecipeToView(FilteredRecipes, scanner);
    }

    public static void searchIngredients(List<Recipe> recipes)
    {
        List<Recipe>    FilteredRecipes = new ArrayList<>();
        boolean         incl = false;
        System.out.println("Would you like a recipe that includes or excludes a specific ingredient? (press i/e)");
        Scanner scanner = new Scanner(System.in);
        char choice = 0;
        do {
            choice = scanner.next().toLowerCase().charAt(0);
            scanner.nextLine(); //To take the newline after the char
            switch (choice) {
                case 'i':
                    incl = true;
                    break ;
                case 'e':
                    break;
                default:
                    System.out.println("Invalid input, please try again:");
            }
        } while (choice != 'e' && choice != 'i');
        if (incl)
            System.out.println("Which ingredient should be included in the recipe?");
        else
            System.out.println("Which ingredient should be excluded from the recipe?");
        String input = scanner.nextLine();
        int i = 1;
        if (incl) {
            FilteredRecipes = recipes.stream()
                    .filter(recipe -> recipe.getIngredients().stream().map(String::toLowerCase).toList().contains(input.toLowerCase()))
                    .collect(Collectors.toList());
            for (Recipe filteredRecipe : FilteredRecipes) {
                System.out.println("Recipe found: " + i + ". " + filteredRecipe.getRecipe());
                i++;
            }
        }
        else {
            FilteredRecipes = recipes.stream()
                    .filter(recipe -> !recipe.getIngredients().contains(input))
                    .collect(Collectors.toList());
            for (Recipe filteredRecipe : FilteredRecipes) {
                System.out.println("Recipe found: " + i + ". " + filteredRecipe);
                i++;
            }
        }
        if (FilteredRecipes.size() == 0)
        {
            System.out.println("No recipes in your book meet the criteria. You will return to the main menu now.");
            return ;
        }
        if (extraSearch(FilteredRecipes, scanner) == 1)
            return ;
        selectRecipeToView(FilteredRecipes, scanner);
    }

    public static int extraSearch(List<Recipe> searchResults, Scanner scanner)
    {
        System.out.println("Would you like to perform another search on these results? (y/n)");
        char choice = 0;
        do {
            choice = scanner.next().toLowerCase().charAt(0);
            switch (choice) {
                case 'y':
                    searchSelect(searchResults);
                    return (1);
                case 'n':
                    break;
                default:
                    System.out.println("Invalid input, please try again:");
            }
        } while (choice != 'n' && choice != 'y');
        return (0);
    }

    public static void selectRecipeToView(List<Recipe> searchResults, Scanner scanner)
    {
        if (searchResults.size() > 1) {
            System.out.println("Which recipe would you like to view?");
            int view = 0;
            do {
                try {
                    view = scanner.nextInt();
                    if (view <= searchResults.size() && view > 0)
                        searchResults.get(view - 1).printFullRecipe();
                    else
                        System.out.println("Wrong input, please try again.");
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                }
            } while (view < 1 || view > searchResults.size());
        }
        else{
            System.out.println("Recipe found after searching:");
            searchResults.get(0).printFullRecipe();
        }
    }
}
