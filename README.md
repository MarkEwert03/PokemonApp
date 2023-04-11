# Pokémon Team Builder

## 2022W2 CPSC210 Project by Mark Ewert

In this project I am aiming to create a program that
will allow users to create a team of up to six pokémon.
Users will be able to pick from a wide varitey of species,
and can then customize the specific ones they chose in
minor ways.

**Some elements I would like to include are**
- Changable nicknames for the Pokémon
- Reordering of the team list
- A rendering of the trainer and the Pokémon team
- Possibly changable moves/abilities of the Pokémon
- Possibly provide statics about the team composition

This project is meant to be a source of entertainment
and as an application for anybody who is interested in
Pokémon. It could either be used to devise a possible
team for battling, or just to try out different combinations

I enjoy playing video games and Pokémon is one of my
favourite series, so this idea came naturally. I am very
excited to combine two of my passions into this project,
Pokémon and programming, and I look forward to completing
it!

## User Stories
- As a user, I want to be able to create and delete Pokemon for my trainer
- As a user, I want to be able to create a new Pokémon team, and add it to a list of teams
- As a user, I want to be able to select a team and add up to 6 new Pokémon to it along with a name
- As a user, I want to be able to pick a team, and see all the Pokémon on it
- As a user, I want to be able to be able to set certain attributes for each Pokémon on my team
- As a user, I want to be able to save the current state of my ranch and list of teams
- As a user, I want to be able to choose to reload that previous save state when I reopen my project, or start fresh
- As a user, I want to see a rendering of me, the trainer, along with all my Pokémon on the team

# Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by adding a Pokemon in the ranch panel. 
  Click the "Add Pokemon" button and follow the window popup instructions.
- You can generate the second required action related to adding Xs to a Y by modifying a specific Pokemon in the Pokemon tab.
  Pick one of your made Pokemon, then you can change attributes and click to confirm those changes.
- You can locate my visual component by seeing the chainable rendering of the trainer on the trainer panel
- You can save the state of my application by clicking the save button on the main menu
- You can reload the state of my application by clicking the load button on the main menu

# Phase 4: Task 2
- Sat Apr 08 19:54:15 PDT 2023
- Changed Trainer name from Trainer to Mark
- Sat Apr 08 19:54:16 PDT 2023
- Changed Trainer gender from OTHER to MALE
- Sat Apr 08 19:54:25 PDT 2023
- Added baba the Bulbasaur to Mark Ranch
- Sat Apr 08 19:54:36 PDT 2023
- Changed baba's nickname from baba to bloom
- Sat Apr 08 19:54:56 PDT 2023
- Added beast the Charizard to Mark Ranch
- Sat Apr 08 19:55:02 PDT 2023
- Changed beast's gender from MALE to FEMALE
- Sat Apr 08 19:55:02 PDT 2023
- Changed beast's shiny status from false to true
- Sat Apr 08 19:55:07 PDT 2023
- Made new team called cool team
- Sat Apr 08 19:55:14 PDT 2023
- Made new team called empty team
- Sat Apr 08 19:55:22 PDT 2023
- Added bloom the Bulbasaur to cool team
- Sat Apr 08 19:55:24 PDT 2023
- Added beast the Charizard to cool team
- Sat Apr 08 19:55:26 PDT 2023
- Moved beast the Charizard to front of cool team
- Sat Apr 08 19:55:32 PDT 2023
- Changed very cool team's name from cool team to very cool team
- Sat Apr 08 19:55:38 PDT 2023
- Deleted empty team (0 Pokemon)

# Phase 4: Task 3
&emsp; As I started work on my GUI I liked the idea of keeping the code for each panel in my
tabbed pane as separate classes all extending from one abstract class (ColorPanel). This class
has a protected Trainer field that gets inherited. My idea was for this to be a universal trainer
that each subpanel can reference, but I forgot that each panel would only know about an
independent trainer and changes wouldn't be universal. To get around this, I had to pass a
Trainer object to each subpanel constructors to initialize it, which worked but was a bit
clunky.

&emsp; My idea of a refactor would be to modify the Trainer field in (ColorPanel) to adhere to
the singleton design pattern. I would still have it protected so the subclasses could all
access it but instead of creating a new trainer, I would use the getInstance() method to make
sure that everyone is refering to the same Trainer object. This would help declutter my code
as well as make it easier to understand that I am only interested in ONE trainer object.