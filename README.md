# PetriNetwork

#Introduction
The following file is a an explanation of the MAPD module project "PetriNetwork". We provide a software solution to simulate a functional PetriNetwork using various aspects and definitions. We will also provide a set of tests that justifies our selections and proposals. 

## Description
The following is a summary of the classes used in the project
### Arc : 
Arc is an abstraction of the arrows in a PetriNetwork. Its attributes are : weight (the value used by the arrow when activated), sens ( is it going from place to transition or the opposite ? ), place and transition (the too entities that it linked). The class uses too important methods : Activate() (to execute the effect of the arrow), isActivate() used to verify if the arrow can be activated. 

### ArcN : 
ArcN is the abstraction of a normal arrow with the regular behavior. It inherits from Arc and add methods to modify the weight and showing the sens. 

### ArcV : 
ArcV inherits as well from Arc. it represents the set of "Arc videur". This type is always from place to transition(hence the true value used in the constructor), and to be activated the place must contain at least one token. The activation set the tokens in the place to 0. 

### ArcZ : 
The final arrow abstraction is called "ArcZ". This class inherits from Arc and modifies the activation methods accordingly, its always from place to transition. IsActivate() returns true when the place contains exactly zero token and the activation in of itself do not change anything (we only need the isActivate() method in this case to use it in tranistion class).

### IPetriNetwork : 
This Interface is designed to specify regular methods to be used in a PetriNetwork class. It helps defining the general outlayer of the software.

### PetriNetwork : 
This is the class that uses all other entities (except UserInterface) in order to manage a PetriNetwork correctly. It contains methods to manipulate the network ( for example : adding a transition, a place, establishing an Arc, removing a place...)

### Place : 
Place is the class used to handle a concrete place in a network. It contains and manipulate mainly tokens to ensure their flow when transitions are activated. 

### Transition : 
Transition is the abstraction of a transition in a network. 
It handles activations by using too main methods :isFireable() that verifies if all conditions (all the conditions of the arrows) are true for the transition to be executed, and fire() that execute the activations of the arrows to modify the places accordingly. 

### UserInterface : 
This interface is used to interact with the user using the console. It shows to the user a set of actions he can execute and take as input his commands from the console. 

### code execution : 
The PetriNetwork classes are used to reify the concrete PetriNetwork. They do not possess any main methods, they only contain methods to reify their actions. Therefore we created the UserInterface class, that communicates with the user trough the console and uses the class PetriNetwork methods ( that in they turn use the other entities methods) in order to manipulate the network. 
As a consequence, to visualize the network functions globaly, a user can access to class and run its main method. 
In the console, the first lines to be shown are used to insert the name of the network :

```
-----starting-----
Enter Network name : 
```
After choosing the name a list of commands are shown to the user : 

```
-----starting-----
Enter Network name : usr
Choose action : 
1 : add transition
2 : show transitions
3 : remove transition
4 : add place 
5 : show places 
6 : remove places 
7 : set tokens
8 : add normal arc 
9 : add arc videur
10 : add arc zero 
11 : remove arc 
12 : remove  arc 
13 : show arc
14 : show petri
E : end

```
At the end of each action, the programm asks to user if he wish to continue or not (typing y to continue or n to stop) : 

```
-----starting-----
Enter Network name : usr
Choose action : 
1 : add transition
2 : show transitions
3 : remove transition
4 : add place 
5 : show places 
6 : remove places 
7 : set tokens
8 : add normal arc 
9 : add arc videur
10 : add arc zero 
11 : remove arc 
12 : remove  arc 
13 : show arc
14 : show petri
E : end
4
Enter Number of tokens : 7
Place added
Continue usr y/n : 
```
If the user decided to stop the simulation he can either choose the end action (typing E ) or selecting no when his final action ends (typing n).

```
-----starting-----
Enter Network name : usr
Choose action : 
1 : add transition
2 : show transitions
3 : remove transition
4 : add place 
5 : show places 
6 : remove places 
7 : set tokens
8 : add normal arc 
9 : add arc videur
10 : add arc zero 
11 : remove arc 
12 : remove  arc 
13 : show arc
14 : show petri
E : end
4
Enter Number of tokens : 7
Place added
Continue usr y/n : n
-----Ending-----

```
```
```
Next we are going tpo explain each command how it works : 

```
1 : add transition
```
To add a transition, a user can type "1". An isolated transition is then added. The transitions are stored 
in a ArrayList, the user can access them using indexes 
starting from 1 (not 0). If removed a transition (as we are 
going to see next) the order of the transitions on the 
right of the list is going to decrease by one. After adding a transition he will receive the following message : 


```
Transition added
```
This message ensures the actions was executed.

```
2 : show transitions
```
The user can get the number of transitions by typing "2"

```
Number of transitions is : 1
```
The above text is the output of the software. 

```
3 : remove transition
```
Typing "3" enables the user to delete a transition. He needs
to provide its order in the list.

```
Choose index to remove :
```
after selecting the index the transition will be removed

```
Transition removed
```

```
4 : add place 
```

To add a place a user needs to type 4.
He will then be asked to insert the number of tokens. 
Places are stored and accessed the same way as transitions.

```
Enter Number of tokens : 
```
The place will be added afterwards. 

```
¨Place added
```

```
5 : show places
```
The user can get the number of places by typing "5"

```
Number of places is : 1
```
The above text is the output of the software. 

```
6 : remove place
```
Typing "6" enables the user to delete a place. He needs
to provide its order in the list.

```
Choose index to remove :
```
after selecting the index the transition will be removed

```
Place removed
```


```
7 : set tokens
```
To change tokens of a place, the user need to type "7". 
He then needs to choose the place :

```
Choose Place : 
```
He will be asked afterward the provided the new number of tokens ( it should be positive if not it will be set to zero)

```
Choose Number of tokens : 7
Tokens changed 
```

```
8 : add normal arc 
```
To add a normal arc the user can type 8. He will then 
provide the necessary arguments : 
The order of the place to link
The order of the transition to link 
The direction of the arc (it must be strictly either "true" or "false")
The weight of the arc (if it is given as a negative value 
it will be set to zero)

```
Choose Place : 
1
Choose Transition : 
1
Choose Sens : 
true
Choose Weight : 
1
Normal Arc added 
```

To add an arc videur the user must type "9". He will be asked afterwards to provide the Place and the transition
orders. 

```
Choose Place : 
1
Choose Transition : 
1
Arc Videur added 
```

To add an arc zero the user must type "10". He will be asked afterwards The same previous procedure.

```
Choose Place : 
1
Choose Transition : 
1
Arc Zero added 
```
##Important Note 
The software do not accept double arcs (arcs with the same place and transition and direction) it will wrote : 

```
Double arcs not allowed, please choose another one
```

To show the arcs number, the user can type "11". 

```
Number of Arcs is : 1
```
To remove an arc, the user can type "12", he should specifies the place and the transition orders. He will then select if he is looking 
for a normal arc (choosing "N") or an other arc (choosing
"E"). By choosing "N" he needs to specify the sens (only "true or "false") and the weight.

```
Choose Place : 
1
Choose Transition : 
1
Choose Option : N/E
N
Choose Sens : 
true
Choose Weight : 
1
Arc removed 
```

By choosing "E", the software looks for an arc of other type


```
Choose Place : 
1
Choose Transition : 
1
Choose Option : N/E
E
Arc removed 
```

To execute a transition, the user can type "13"


```
13
Choose Transition : 
2
Transition executed 
Continue pp y/n : y

```

If the transition is not fireable: 

```
13
Choose Transition : 
1
Transition not fireable 
```

Finally, typing "14" provides a summary of the network.

```
13
Petri summary :
Places :
P0 => jetons : 4
Transitions :
T0
Arcs :
Arc 0: P0 -> T0 type : network.ArcV
```


### Tests execution : 
To execute the tests for the software, the user must have JUnit installed in the eclipse environment. He can then execute the package "network.tests" using the following commands : Run As -> JUnit. There is a total of 29 tests, that covers all of the PetriNetwork entities.


### Notes on the initial conception 
The resulting software come up as conceived at the conception phase except some few changes. The main modification we had to do was to change Arc class to abstract; since we are not using the class itself but rather the inheritance, we decided to make it abstract to represent a more correct semantic. We also changed the name of the method "updateArcValue" -> "UpdateWeight" to be more coherent with the attributes. Moreover, we removed the method getSens() from the class ArcN, since it was merely an exact override of the method from Arc (At the conception stage we changed some the way we handle sens and we forgot to remove this method). Otherwise, the software respects the conception diagram. 

### Conclusion 
We described earlier the function of the classes, and we gave a brief summary of their main methods. We also discussed how the run the source code and the tests of the program. Finally, we wrote some notes on the conception and how it changed during the developing phase. 



