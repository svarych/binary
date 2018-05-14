MAIN PAGE --------------------
0. Precondition: load application on https://volodymyr-kushnir.github.io/recipes/#/;

=============================
CHECKLIST
=============================

1. Header should be loaded (one random header)
2. Recipes should be loaded (no errors, 15 recipes per page)

RECIPES ----------------------

0. Recipe block header has "favorite" and "already cooked" checkboxes and timer;
1. Recipe block has picture;
2. Recipe block has header with dish name
3. Recipe block has meta data about ingridients used (hash-tags)
4. Recipe block has dish description
5. Recipe block footer has "Ingridients" block
6. Recipe block footer has "Directions" block

=============================
CASES
=============================

0. Precondition: You see recipe that has no views, notes or activated checkboxes. (A newly activated account?)
1. Click on star icon ("favorite") | ExR: Favorite icon changed color from gray to yellow. | AcR: Same | STATUS: Passed
2. Click on star icon ("favorite") again | ExR: Favorite icon reverts color back from yellow to gray: | AcR. Same | STATUS: Passed

3. Click on spoon icon ("already cooked") | ExR: Favorite icon changed color from gray to black | AcR: Same | STATUS: Passed
4. Click on spoon icon ("already cooked") | ExR: Favorite icon reverts color back from black to gray. | AcR: Same | STATUS: Passed

5. Click on "Ingredients" link | ExR. Ingredients list appears under link | AcR: Same | STATUS: Passed
6. Click on "Directions" link | ExR. Directions appears under link | AcR: Same | STATUS: Passed

* ExR = Expected result
* AcR = Actual result

=============================
STORY
=============================

0. As a regular user, I want the page to load faster, so correct the code or upgrade the server;

1. As a test user, I do not want to have something like that in the code: "if (Math.random() > 0.75) throw new Error('randomly forced throw, 25% chance')" so hit the hands of those who wrote it!

=============================
BUG REPORT
=============================

Bug Summary: Recipe block not loads with some periodicity
Severity: critical
Steps to reproduce: 	open the link: https://volodymyr-kushnir.github.io/recipes/#/
			You can see error message: "Can't fetch recipes because of "randomly forced throw, 25% chance".
			If you don't see it - reload page until bug is reproduced (25% chance)
Expected Result: Recipe list loads properly, without error message.


-----------------------------
WE CAN AUTOMATE ALL THIS TESTS

-----------------------------
TO RUN TESTS TYPE "mvn clean test"

-----------------------------
TO GENERATE REPORT TYPE "allure generate ./target/surefire-reports/ --clean"
- 'allure-report' will be generated;
- To view the results open './allure-report/index.html' in your favorite browser.

![Screenshot](Screenshot_2018-05-14_11-02-19.png?raw=true "Allure report live screenshot")

* Note that 'allure' should be installed on your system

-----------------------------
Documentation, Demo-report and Examples you can see here: http://allure.qatools.ru/


