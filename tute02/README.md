# Tutorial 02
## A. Code Review
Your tutor will provide you a link or open up the `src/shapes`, and use the `Shape` and `Rectangle` classes.

In groups, analyse the classes to answer the following questions:

1. What is the difference between `super` and `this`?
2. What about `super(...)` and `this(...)`?
3. What will the code print and why?

## B. JavaDoc & Commenting
Within the `src` directory, you have been provided with an `Employee` class, where an employee has a name and salary.

The class has been documented with [JavaDoc](https://www.oracle.com/au/technical-resources/articles/java/javadoc-tool.html).

In this course we are not going to require that you write JavaDoc, except when specified.

- What are the main features of the JavaDoc present in Employee.java?
- What is meant by the term "self-documenting code"?
- Discuss as a class whether code should always have comments / JavaDoc

## C. Basic Inheritance & Polymorphism
This exercise continues on from the `Employee` class in Exercise B.

Create a `Manager` class that is a subclass of `Employee` and has a field for the manager's hire date.

Use VSCode to create the getters and setters.

- What constructors are appropriate?
- Is it appropriate to have a getter for the hire date? What about a setter?
- Why might adding certain getters and setters be bad design?

## D. toString and Equals
This exercise continues on from the work in Exercise C.

Override the `toString()` method inherited from `Object` in both classes.

- What should the result of `toString()` contain?
- Does the method in `Manager` call the one in `Employee`?

We will now look at equality checks in Java.

- What does the `==` operator do when comparing objects?
- Where have you seen this sort of behaviour before in other languages? How is the underlying data checked for equality in that scenario?
- How can we compare two objects for equality?
- What does it mean for objects to be considered equal?
- What is the relationship between a super type and a sub type in terms of equality? Can a concrete instance of an `Employee` be equal to an instance of a `Manager`

Override the `equals()` method inherited from `Object` in both classes.
- What key things should the `equals()` method do?
- How should the type of the input object be checked? How should it be compared to the type of the calling object?
- How can the method in `Manager` utilise code in `Employee` to avoid repetition?

## E. Abstract classes and Interfaces

Look at the code in the [src/dogs](src/dogs/) package, including the `main` method in `Chihuahua.java`.
- What is the purpose of using an interface in this code?
- What are some downsides of the use of the interface here?
- What is the difference between an abstract class and an interface? Why would you use one or the other?

If you have time, refactor the code to improve its quality, perhaps using an abstract class.

## F. Access Modifiers & Packages
In the [src/access](src/access/) package, answer the questions marked `TODO`.
