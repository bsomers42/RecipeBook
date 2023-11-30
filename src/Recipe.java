import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Recipe implements Serializable
{
    private String          recipe;
    private List<String>    ingredients;
    private String          instructions;
    private boolean         vegan;
    private boolean         vegetarian;
    private int             prepTime;
    private int             servings;

    public Recipe()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipe its name: ");
        this.recipe = scanner.nextLine();
        System.out.print("Enter the ingredients, separated by a comma: ");
        String ingredientsInput = scanner.nextLine();
        String[] ingredientsArray = ingredientsInput.split(",");
        this.ingredients = Arrays.asList(ingredientsArray);
        System.out.print("Enter the instructions: ");
        this.instructions = scanner.nextLine();
        this.setVeg(scanner);
        System.out.print("Enter the preparation time in minutes: ");
        this.setTimeServings(scanner);
        System.out.print("Enter the number of servings: ");
        this.setTimeServings(scanner);
        //scanner.close();
    }

    public int     resetTimeServings(Scanner scanner)
    {
        int num = 0;
        System.out.print("Invalid value. Please fill in a number bigger than 0. Please try again: ");
        do {
            num = scanner.nextInt();
            if (num > 0)
                return (num);
            else
                System.out.print("Invalid value. Please fill in a number bigger than 0. Please try again: ");
        } while (num <= 0);
        return num;
    }
    public void     setTimeServings(Scanner scanner)
    {
        int num = 0;
        do {
            num = scanner.nextInt();
            if (num > 0)
                this.prepTime = num;
            else
                System.out.print("Invalid value. Please fill in a number bigger than 0. Please try again: ");
        } while (num <= 0);
    }
    public void   setVeg(Scanner scanner)
    {
        boolean choice = false;
        do {
            System.out.print("Is the recipe vegan? Type y/n: ");
            String ifvegan = scanner.nextLine();
            if (ifvegan.equalsIgnoreCase("n")) {
                System.out.print("Is the recipe vegetarian? Type y/n: ");
                String ifvegetarian = scanner.nextLine();
                if (ifvegetarian.equalsIgnoreCase("y"))
                {
                    this.vegetarian = true;
                    choice = true;
                }
                else if (ifvegetarian.equalsIgnoreCase("n"))
                    choice = true;
                else {
                    System.out.print("Wrong input, please enter y for yes or n for no. ");
                }
            } else if (ifvegan.equalsIgnoreCase("y")) {
                this.vegan = true;
                this.vegetarian = true;
                choice = true;
            } else {
                System.out.print("Wrong input, please enter y for yes or n for no. ");
            }
        } while (!choice);
    }

    public void     printFullRecipe()
    {
        System.out.println("Recipe name: " + recipe);
        System.out.print("Ingredients: ");
        for (String tmp : ingredients)
        {
            System.out.print(tmp);
        }
        System.out.println(" ");
        System.out.println("Instructions: " + instructions);
        if (vegetarian)
            System.out.println("Vegetarian: yes");
        else
            System.out.println("Vegetarian: no");
        if (vegan)
            System.out.println("Vegan: yes");
        else
            System.out.println("Vegan: no");
        System.out.println("Preparation time: " + prepTime);
        System.out.println("Servings: " + servings);
    }


    public String           getRecipe() { return recipe;}
    public List<String>     getIngredients() { return ingredients;}
    public String           getInstructions() { return instructions;}
    public boolean          getVegan() { return vegan;}
    public boolean          getVegetarian() { return vegetarian;}
    public int              getPrepTime() { return prepTime;}
    public int              getServings() { return servings;}
    public void           setRecipe(String newVal) {
        this.recipe = newVal;
        return ;}
    public void     setIngredients(List<String> newVal) {
        this.ingredients = newVal;
        return ;}
    public void           setInstructions(String newVal) {
        this.instructions = newVal;
        return ;}
    public void          setVegan(boolean newVal) {
        this.vegan = newVal;
        return ;}
    public void          setVegetarian(boolean newVal) {
        this.vegetarian = newVal;
        return ;}
    public void              setPrepTime(int newVal) {
        this.prepTime = newVal;
        return ;}
    public void              setServings(int newVal) {
        this.servings = newVal;
        return ;}
}
//