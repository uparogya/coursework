.NOTPARALLEL :


GCC=gcc -Wall

default : help

define green = 
[38;2;0;255;0m
endef

define reset =
[m
endef

define usage_information =
Commands:
	$(green)make help$(reset)       - show this help message
	$(green)make compile$(reset)    - compile your project only
	$(green)make run$(reset)        - compile your project if necessary, then run it
	$(green)make test1$(reset)      - run the Step 1 tester
	$(green)make test2$(reset)      - run the Step 2 tester
	$(green)make test3$(reset)      - run the Step 3 tester
	$(green)make test4_5$(reset)    - run the Step 4/5 tester
	$(green)make tests$(reset)      - run all the tests, one by one, until one fails

You can give more than one command at once, for example
	make test1 test2
will run both the Step 1 and Step 2 testers.
endef

help :; @ $(info $(usage_information)) :

project2 : project2.c
	$(GCC) project2.c -o project2

compile : project2

run : project2
	./project2

test1 : project2.c
	/var/project02/test/step 1

test2 : project2.c
	/var/project02/test/step 2

test3 : project2.c
	/var/project02/test/step 3

test4_5 : project2.c
	/var/project02/test/step 4_5

tests : test1 test2 test3 test4_5


.SUFFIXES :

