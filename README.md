# RecipeBook

This is small Java application (CLI) for adding, updating, removing and viewing recipes.
Due to time restrictions, the application is completely commandline based.
Since it is not expected to have vast amounts of recipes stored by a user, the application does not use a dedicated database but Java Serialization to store and fetch the information of saved recipes (from the .recipe.ser file.).

This application was fully written using the IntelliJ IDEA by JetBrains.

## Usage
Make sure you have the Java Development Kit (JDK) installed on your system. This can be downloaded from the official Oracle website or use an OpenJDK distribution.
To compile:

```bash
  cd src
  javac *.java
```

To run:

```bash
  java Program
```
