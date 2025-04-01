# Tutorial 04
## A. Design Principles
### Part 1: Law of Demeter
In the `unsw.training` package there is some skeleton code for a training system.

- Every employee must attend a whole day training seminar run by a qualified trainer
- Each trainer is running multiple seminars with no more than 10 attendees per seminar

In the `TrainingSystem` class there is a method to book a seminar for an employee given the dates on which they are available. This method violates the principle of least knowledge (Law of Demeter).

1. How and why does it violate this principle?
2. In violating this principle, what other properties of this design are not desirable?
3. Refactor the code so that the principle is no longer violated. How has this affected other properties of the design?
4. More generally, are getters essentially a means of violating the principle of least knowledge? Does this make using getters bad design?

### Part 2: Liskov Substitution Principle
Look at the `OnlineSeminar` class. How does this violate the Liskov Substitution Principle?

## B. Streams and Lambdas
1. Inside `src/stream/App.java`, rewrite the following code using the `.forEach()` method and a lambda:
```java
List<String> strings = new ArrayList<String>(Arrays.asList(new String[] {"1", "2", "3", "4", "5"}));
for (String string : strings) {
    System.out.println(string);
}
```
2. In the above example, discuss two different ways to write lambda expressions.
3. What is a stream? Rewrite the following code to use a stream and the `map` function.
```java
List<String> strings2 = new ArrayList<String>(Arrays.asList(new String[] {"1", "2", "3", "4", "5"}));
List<Integer> ints = new ArrayList<Integer>();
for (String string : strings2) {
    ints.add(Integer.parseInt(string));
}
```
4. Modify your answer to (3) to use a scope operator instead of a normal lambda.

## C. Design by Contract
1. What is Design by Contract?
2. Discuss how Design By Contract was applied in the Blackout assignment.
3. Discuss what preconditions, postconditions and invariants are.
4. Consider a `Bird` class which has a function `fly`, which has a precondition that it is given a height to fly at greater than 5 metres in height, and a postcondition that it is now considered flying at that height. If I have a `Penguin` class which overrides that the `fly` method so that its preconditions are that it can only accept a height of 0 metres (since penguins can't fly) and its postconditions are that nothing changes, I have
    - *strengthened* my preconditions, as not every potential input for `Bird` works for `Penguin` (in fact, none of them do)
    - *weakened* my postconditions, as `Penguin` has an outcome which `Bird` doesn't
Discuss why strengthening preconditions and weakening postconditions violates good inheritance design
5. In the `people` package, there are a few classes which represent the people at a university
    - Briefly discuss the preconditions and postconditions of the constructors, getters and setters in `Person.java`
    - Fill in the preconditions and postconditions for `setSalary` in `Person.java`
    - Discuss the validity of the subclasses of `Person`, and why they are/aren't valid subclasses
    - Fix any issues you identified before
6. Will you need to write unit tests for something that doesn't meet the preconditions? Explain why.
7. If we were to try make our code more defensive, we could throw an exception on any inputs not satisfying the preconditions. Discuss whether these exceptions are now considered defined behaviour or not, and whether you now need to account for it in your postconditions.
