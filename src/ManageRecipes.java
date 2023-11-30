import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManageRecipes {
    public static List<Recipe> showRecipes(List<Recipe> recipes)
    {
        if (recipes.isEmpty()) {
            System.out.println("You have no recipes yet. Add a recipe first.");
            return recipes;
        }
        System.out.println("Recipe list: ");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getRecipe());
        }
        System.out.print("Which recipe would you like to view, edit or delete? Please give the corresponding number: ");
        Scanner scanner = new Scanner(System.in);
        int recipeNum = scanner.nextInt() - 1;
        while (recipeNum < 0 || recipeNum > recipes.size() - 1) {
            System.out.println("Invalid index. No recipe edited. Please try again:");
            recipeNum = scanner.nextInt() - 1;
        }
        System.out.println("Recipe chosen: ");
        recipes.get(recipeNum).printFullRecipe();
        System.out.print("Would you like to edit or delete this recipe? Press E for edit, D for delete. If you want to return to the main menu, press X. Choice: ");
        char action = scanner.next().toUpperCase().charAt(0);
        switch (action){
            case 'E':
                recipes.set(recipeNum, EditRecipe(recipes.get(recipeNum)));
                return (recipes);
            case 'D':
                System.out.println("Recipe at index " + (recipeNum + 1) + " with name " + recipes.get(recipeNum).getRecipe() + " removed.");
                recipes.remove(recipeNum);
                return (recipes);
            case 'X':
                System.out.println("Returning to main menu.");
                return (recipes);
            default:
                System.out.println("Invalid input. Please use E, D or X.");
        }
        return recipes;
    }

    public static Recipe EditRecipe(Recipe recipe)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type the new value if you would like to change the value of the recipe, or leave blank to leave unchanged.");
        System.out.println("Recipe name: "+ recipe.getRecipe());
        String newVal = scanner.nextLine();
        if (!newVal.isEmpty())
            recipe.setRecipe(newVal);
        System.out.print("Ingredients (note: separate the ingredients with a comma): ");
        for (String ingredient : recipe.getIngredients())
        {
            System.out.print(ingredient + ",");
        }
        System.out.println(" ");
        newVal = scanner.nextLine();
        if (!newVal.isEmpty())
        {
            String[] ingredientsArray = newVal.split(",");
            recipe.setIngredients(Arrays.asList(ingredientsArray));
        }
        System.out.println("Instructions: "+ recipe.getInstructions());
        newVal = scanner.nextLine();
        if (!newVal.isEmpty())
            recipe.setInstructions(newVal);
        recipe.setVeg(scanner);
        System.out.println("Preparation time: "+ recipe.getPrepTime());
        int newValInt = scanner.nextInt();
        if (newValInt <= 0) //But what if the user doesn't change the value?
            recipe.setPrepTime(recipe.resetTimeServings(scanner));
        else
            recipe.setPrepTime(newValInt);
        System.out.println("Servings: "+ recipe.getServings());
        newValInt = scanner.nextInt();
        if (newValInt <= 0)
            recipe.setServings(recipe.resetTimeServings(scanner));
        else
            recipe.setServings(newValInt);
        System.out.println("Recipe successfully saved.");
        return (recipe);
    }
}
//