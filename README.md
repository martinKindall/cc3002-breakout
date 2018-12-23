# Breakdown Game

This game is about sliding an horizontal bar and destroying blocks using a bouncing ball. There are different kinds of blocks, some harder to destroy than others.

## Getting started

Follow the instructions if you want to play this game.

### Instructions

- Press J or L to move
- Press Space to throw ball
- Press Q to restart game (only when game is finished)
- Press N to add levels
- Press W to check stats

## Features

### Bonus

Whenever a brick is destroyed, there is a small chance of obtaining an interesting feature! Each feature produces a unique sound when it occurs.

- Extra Score: 3% of probabilities in obtaining 50,000 extra score.

- Extra Ball: 5% of probabilities in obtaining an extra ball. Whenever an extra ball is lost, you don't lose one of the remaining balls.

### Sound

Glass and Metal brick produce an unique sound when they are hit. When a brick is destroyed, it makes also a unique sound.

### Installation

This game runs only on Java 8, as it uses Observer and Observable. Be sure to install Java 8 jdk as you will need to compile the game. 

It is highly recommended that you open the project using IntelliJ, as this is the IDE where the game was built and it follows its structure pattern.

### Running the tests

To run the tests you must have JUnit 4 installed. Just go to test package and execute the desired test with IntelliJ.


## Built With

- Java 8
- IntelliJ

## Authors

- https://github.com/martinKindall (code and tests)

## GUI Package

### View

This class contains the collision handlers, keyboard handler and View controller. 

### (Abstract)Bonus

This class contains the structure of bonus interface. There are two bonus implemented:

- Extra Score
- Extra Ball


### GameState

There are three posible states:

- GameNotReadyState: When the game starts, there is no level available. Thus, in this state the ball cannot be thrown. When the player press N, the state changes to GamePlayingState.

- GamePlayingState: In this state the player can destroy bricks in order to increase the score and advance to new levels.

- GameFinishedState: Whether the player has won or lost, the player stays in this state until he press Q in order to restart the game.

### GameFactory

This class contains the methods that creates the game's entities (player slider, bricks, walls, ball). It defines the game physics laws. 


### Nota al ayudate:

Usé mi propio código. Es posible que hayan comentarios de JuanPablo pero esos corresponden a los métodos públicos de Game, los cuales fueron entregados en la Tarea 2.