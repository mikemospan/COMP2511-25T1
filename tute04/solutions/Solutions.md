# Tutorial 04
## A. Design Principles
### Part 1: Law of Demeter
In the `unsw.training` package there is some skeleton code for a training system.

- Every employee must attend a whole day training seminar run by a qualified trainer
- Each trainer is running multiple seminars with no more than 10 attendees per seminar

In the `TrainingSystem` class there is a method to book a seminar for an employee given the dates on which they are available. This method violates the principle of least knowledge (Law of Demeter).

1. How and why does it violate this principle?
> The `TrainingSystem` class extracts instances of `Seminar` from instances of `Trainer` and calls the methods of `Seminar`. Furthermore it extracts the start date from instances of `Seminar` and calls its methods. More informally, the `TrainingSystem` class is interacting with classes other than its "friends".

2. In violating this principle, what other properties of this design are not desirable?
> - The design is needlessly tightly coupled as `TrainingSystem` is dependent on both `Trainer` and `Seminar`.
> - `TrainingSystem` suffers from low cohesion as any change to the system requires a change to this class.
> - The `Seminar` class has no control over the number attendees. It relies on `TrainingSystem` to ensure there are never more than 10. This makes `Seminar` hard to re-use as any future client has to ensure they don't exceed the maximum of 10 attendees. This is an example of poor encapsulation.

3. Refactor the code so that the principle is no longer violated. How has this affected other properties of the design?
> See code [here](solutions/src/unsw/training).
>
> - The design is no longer tightly coupled. `TrainingSystem` no longer has any knowledge of `Seminar`.
> - Each of the classes now has a clear purpose in booking a training seminar, thus improving cohesion.
> - The `Seminar` class itself is now responsible for ensuring that the number of attendees does not exceed 10. This is an example of a class maintaining its invariant. We'll come back to that when discussing programming by contract.

4. More generally, are getters essentially a means of violating the principle of least knowledge? Does this make using getters bad design?
> Getters that return an object (as opposed to a primitive) likely only serve the purpose of letting clients invoke methods on that object, so a valid point can be made that getters can only be used as a means for violating the principle of least knowledge. A counter argument is that getters make classes more reusable. A client may need to do something with an object for which it has no method. In that case, getters can allow the client use the class in a way that was not originally foreseen, even if it does violate the principle of least knowledge.
>
> Another way in which a getter can be an example of bad design is in `Seminar` above. By having a `getAttendees()` method implemented as a simple getter, any client is able to add additional attendees to the list of attendees, potentially exceeding the maximum of 10. Unfortunately, Java does not offer any good solutions to this problem. Either `getAttendees()` has to create a copy of the list, or it can use `Collections.unmodifiableList(...)` to wrap the list up in a class that prevents any modification to the list. The former solution is inefficient as it needlessly copies data. The latter can be surprising to the client as the returned list still has an `add(...)` method, but it causes a runtime error every time it is used. Other languages resolve this problem by having proper immutable or read-only lists.

### Part 2: Liskov Substitution Principle
Look at the `OnlineSeminar` class. How does this violate the Liskov Substitution Principle?
> This class violates the Liskov Substitution Principle. Specifically a `Seminar` is defined as having a list of attendees, but `OnlineSeminar` does not require this. A client interacting with a `Seminar` would expect the seminar to be booked like any other. This is an example of classes having an IS-A relationship informally, but not a valid inheritance relationship when taking into account what the classes actually do and represent.

## B. Streams and Lambdas
1. Inside `src/stream/App.java`, rewrite the following code using the `.forEach()` method and a lambda:
```java
List<String> strings = new ArrayList<String>(Arrays.asList(new String[] {"1", "2", "3", "4", "5"}));
for (String string : strings) {
    System.out.println(string);
}
```
2. In the above example, discuss two different ways to write lambda expressions.
> One-liner or with curly braces

3. What is a stream? Rewrite the following code to use a stream and the `map` function.
```java
List<String> strings2 = new ArrayList<String>(Arrays.asList(new String[] {"1", "2", "3", "4", "5"}));
List<Integer> ints = new ArrayList<Integer>();
for (String string : strings2) {
    ints.add(Integer.parseInt(string));
}
```
4. Modify your answer to (3) to use a scope operator instead of a normal lambda.
> See [`App.java`](solutions/src/stream/App.java)

## C. Design by Contract
1. What is Design by Contract?
> Providing an interface for others to use with clear preconditions, postconditions and invariants which, when adhered to, guarantees correct and expected behaviour

2. Discuss how Design By Contract was applied in the Blackout assignment.
> Key things to note:
>
> - Interface functions have been specified in `Blackout`. As long as you match that interface, you can implement the function however you choose.
>
> - You have been told you don't have to account for invalid Satellite/Device IDs, and that no 2 devices would ever occupy the same position. These are examples of preconditions which you don't have to account for (the behaviour of the ADT is undefined in these cases).

3. Discuss what preconditions, postconditions and invariants are.
> Preconditions - conditions on the inputs which guarantee postconditions will be true
>
> Postconditions - guarantees from the actual software on what you can expect from a function (given preconditions)
>
> Invariants - guarantees from the actual software that are always maintained before and after a function call
> (they may not always hold *during* the function call, but the user shouldn't need to worry about that)

4. Consider a `Bird` class which has a function `fly`, which has a precondition that it is given a height to fly at greater than 5 metres in height, and a postcondition that it is now considered flying at that height. If I have a `Penguin` class which overrides that the `fly` method so that its preconditions are that it can only accept a height of 0 metres (since penguins can't fly) and its postconditions are that nothing changes, I have
    - *strengthened* my preconditions, as not every potential input for `Bird` works for `Penguin` (in fact, none of them do)
    - *weakened* my postconditions, as `Penguin` has an outcome which `Bird` doesn't
Discuss why strengthening preconditions and weakening postconditions violates good inheritance design

> - Strengthening preconditions violates LSP, as if I replaced a `Bird` with a `Penguin`, any calls to fly with an input of 5 metres will no longer be valid
> - Similarly, weakning postconditions violates LSP as replacing a `Bird` with a `Penguin` means that the object is no longer considered flying, which can break further code which relies on it now flying

5. In the `people` package, there are a few classes which represent the people at a university
    - Briefly discuss the preconditions and postconditions of the constructors, getters and setters in `Person.java`
    - Fill in the preconditions and postconditions for `setSalary` in `Person.java`
    - Discuss the validity of the subclasses of `Person`, and why they are/aren't valid subclasses
    - Fix any issues you identified before
> See [`people`](src/people/Person.java)

6. Will you need to write unit tests for something that doesn't meet the preconditions? Explain why.
> No, as the point is that inputs which don't meet the preconditions are not accounted for

7. If we were to try make our code more defensive, we could throw an exception on any inputs not satisfying the preconditions. Discuss whether these exceptions are now considered defined behaviour or not, and whether you now need to account for it in your postconditions.
> When you update your postconditions, include the possible exception cases Note that exception cases are still cases for which the preconditions aren't met - this is the subject of a bit of debate, because technically exceptional behaviour is 'accounted for' and defined which means the preconditions (user of the ADT) don't have to worry about it, but also looking at it from a formal 'proving-things about programs' perspective, an input which causes an exception to be raised doesn't map to an output, which means it's not formally 'defined' behaviour. So what this essentially means is that an input that doesn't meet the 'correct' preconditions will cause an exception to be raised (in a defensive programming style) but the behaviour of the ADT is still defined.
>
> From a library-writing perspective this sort of 'contract except I tell you where you went wrong you if you mess up' style of design is useful for 2 reasons. One is that it prevents weird stuff from happening that breaks everything, and the second is that it helps users of the ADT debug their code. Just like how when you enter jibberish on a date field in a web form it says 'error: invalid date' etc etc instead of a 500 Internal Server Error, or when you divide by 0 in Python or Java you get a ZeroDivisionError, rather than some message from the OS.
