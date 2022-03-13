# LAB1 - Unit Testing (with JUnit 5)

### Pergunta

**Which classes/methods offer less coverage? Are all possible decision branches being covered?**

The class that offered less coverage was CuponEuromillions because there was no tests for the format function, and this function was the one that was occuping most of the code of that class. The class Dip didn't have tests for the functions that were automatically implemented and the class EuromillionsDraw owned two methods without tests.

Also, in the class Dip most of the decision branches (mostly if/else) were being treated of. Although in the class CuponEuromillions, the branch with the for on the format method wasn't covered.
