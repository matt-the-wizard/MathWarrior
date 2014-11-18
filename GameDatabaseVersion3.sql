
-- Table: Players
CREATE TABLE Players ( 
    PlayerID      INTEGER PRIMARY KEY AUTOINCREMENT,
    PlayerName          CHAR,
    MaximumHealth INTEGER,
    HealthPoints  INTEGER,
    Strength      INTEGER,
    Score         INTEGER DEFAULT ( 0 ),
    Password      CHAR 
);

INSERT INTO [Players] ([PlayerName], [MaximumHealth], [HealthPoints], [Strength], [Score], [Password]) VALUES ('matt-the-wizard', 100, 100, 5, 0, 'Kyungheetkd99#');

-- Table: Rooms
CREATE TABLE Rooms (
ID INTEGER PRIMARY KEY AUTOINCREMENT,
    RoomID          INTEGER,
    RoomName        CHAR,
    RoomDescription CHAR,
    PlayerID        INTEGER REFERENCES Players ( PlayerID ),
    Solved          BOOLEAN 
);

INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (1, 'Gymnasium', 'The school gym. You notice cracked concreate floors and spiderwebs covering the windows.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (2, 'Cafeteria', 'Cafeteria where shattered plates of fried chicken and green goo decorate the floor and walls.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (3, 'Principles Office', 'Office of the school principle where documents are scattered everywhere.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (4, 'Locker Room', 'The stench of football equipment fills the air as you enter the school locker room. Who knows what lies buried in lockers.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (5, 'Attendance Office', 'Office where attendance slips were filed. Everything is out of place and file cabinets are turned over.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (6, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (7, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (8, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (9, 'History Classroom', 'Classroom where history lessons were taught. A giant skeletion head has replaced where the globe stood on the teachers desk.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (10, 'Counseling Office', ' A skeleton lies on the floor of the room unmoving. Besides that, the room looks pretty normal. Oh wait, a witches cauldron is on the desk, never mind.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (11, 'Bleachers', 'Rusty old metal benches by the football field. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (12, 'Janitors Closet', 'The closet that houses cleaning supplies in the school. Mysterious sounds were always heard coming from this room.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (13, 'Bathroom', 'The school bathroom, flooded with nasty water. All the mirrors are broken and an air vent is torn down from the ceiling.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (14, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (15, 'English Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (16, 'Hallway', 'A wide, large hallway decorated with cobblestones. Claw marks etched into the stone lead the way down the center. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (17, 'Parking Lot', 'Lot where all the vehicles were parked. Only one school bus remains and it is covered in help signs written in black spray paint. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (18, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (19, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (20, 'Hallway', 'A wide, large hallway decorated with cobblestones. Claw marks etched into the stone lead the way down the center. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (21, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (22, 'Softball Field', 'Field covered in treaded grass from football cleats. The ground looks ripped up as if lots of people were running on it. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (23, 'Baseball Field', 'Field covered in treaded grass from football cleats. The ground looks ripped up as if lots of people were running on it. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (24, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (25, 'Theatre', 'The school theatre with a huge amount of props lying about. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (26, 'Football Field', 'Field covered in treaded grass from cleats. The ground looks ripped up as if lots of people were running on it. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (27, 'Soccer Field', 'Field covered in treaded grass from cleats. The ground looks ripped up as if lots of people were running on it. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (28, 'Chemistry Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (29, 'Physics Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (30, 'Math Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (31, 'Langauage Room', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (32, 'Band Room', 'Music room full of instruments, however most appear to be broken. Large indents and claw marks have destroyed these. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (33, 'Orchestra Room', 'Music room full of instruments, however most appear to be broken. Large indents and claw marks have destroyed these. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (34, 'Vice Principals Office', 'Office of the school vice principle where documents are scattered everywhere.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (35, 'School Store', 'The retail store of the school where clothes were sold. Piles of clothes lay scattered.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (36, 'Grand Hall', 'The grand hall where ceremonies are held. Floating lit candles cover the room and a cauldron of wiches brew lies alone on the podium.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (37, 'Garden ', 'A once peaceful garden, this school greenhouse is sickly. All the plants are dying and fungus is growing from their remains. Gravel covers the floor of this cold place.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (38, 'Forest ', 'The forest behind the school said to be haunted by ghosts. There is a burial graveyard not far from hear, and a mist densely covers the air. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (39, 'Track', 'The track surrounding the school. It is littered with trash and green goo puddles from zombie tracks. It looks very ominous. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (40, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (41, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (42, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (43, 'Basement', 'The basement, where none have ever ventured. Barely any light lights the way, and cobwebs are everywhere. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (44, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (45, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (46, 'Faculty Office', 'The faculty offices of the school with lesson plans neatly piled on the desks. This area seems mostly untouched from disaster, very strange. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (47, 'Staff Office', 'A regular office room. The window is broken and a rope was used for someone to climb out the window. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (48, 'Tutoring Center', 'The tutoring center of the school, left in ruins. The brick walls are caving in and green vines have taken over much of what is left. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (49, 'Recreation Center', 'Swimming area where the swimming pool is used. The water is as dark as night, and something looks like it is moving in the water, something VERY BIG. ', 1, 'false');
INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (50, 'Tech Hub', 'The game area where all the IT nerds gathered after school. Mountain dw and pizza are left on the table, they looked as if they left in a hurry. ', 1, 'false');

-- Table: Monsters
CREATE TABLE Monsters ( 
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    MID   INTEGER      REFERENCES Rooms ( RoomID ),
    MonsterName              CHAR,
    MonsterDescription       CHAR,
    MonsterStrength          INTEGER,
    MonsterHealth            INTEGER,
    MonsterAttackDescription CHAR,
    PlayerID        INTEGER REFERENCES Players ( PlayerID )
);
-- Create Location Table
CREATE TABLE Location (
LocationID            PRIMARY KEY
                    REFERENCES Players ( PlayerID ),
XPos                Integer,
YPos                Integer
);

INSERT INTO [Location]  
([LocationID], [Xpos], [YPos])
VALUES (1, 0, 0);


INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription]) VALUES (1, 'Cheerleader Ghost Captain', 'The captain of the cheerleading squad. Beware her ferocious pom poms chained with metal spikes. ', 8, 10, 'The spiked pom poms strike you hard against the head. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (2, 'Principle Goo Monster ', 'He ozzes with green goo and slimes everything he touches. Beware his toxicity!', 10, 15, 'Green goo is thrown at you hitting you in the face!', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (3, 'Sassy Substitute Teacher Zombie', 'She wears high heels and laughs hysterically like a mad woman. Green goo covers her once beautiful red dress. ', 9, 11, 'High heels strike you in the rear!', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (4, 'Gym Coatch Frankenstein', 'The large menacing frankenstein bores down at you with red glowing eyes and huge monstrous green hands.', 12, 12, 'Large green hands grab you and squeeze the breath out of you. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (5, 'Cafeteria Pumpkin Monster', 'A large pumpkin monster mutated from zombie goo in the cafeteria. He opens his mouth and you see little pumpkin minions dancing around inside his mouth.', 6, 7,'A mini pumpkin is thrown at you at a very fast speed. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (6, 'Zombie Nurse', 'The school nurse turned into a moaning zombie! Beware her medecinal needles full of green goo!', 5, 8, 'The nurse injects green goo into your arm!', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (7, 'Ghost Bully', 'The school bully turned into a ghost! He hallows at you in a wailing voice!', 4, 10,  'The ghost hallows a deadly wind that sweeps you off your feet. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (8, 'Mad Science Teacher', 'The chemistry professor has turned into a mad scientist! His hair has frizzed out everywhere and he holds flasks of green goo boiling and producing an odd odor. ', 6, 5, 'The scientist hurls a flask at you exploding green goo all over your body. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (9, 'Rampaging Math Tutor', 'The math tutor has gone out of control and is running around the room destroying everything with his textbook! Beware his giant Calculus book!', 9, 8,  'The math tutor swings his Calculus book at you dealing a deadly blow!', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (10, 'Dead Lunch Ladies', 'The lunch ladies of the cafeteria are zombified and spray green goo from their mouths. ', 8, 13,  'The lunch ladies spray green goo at you. ', 1);
INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (11, 'Pumpkin Witch', 'A witch riding a flying pumpkin. She is cloaked in black with a large pointy hat. Her hand holds a green flame, beware! ', 7, 10, 'The witch hurls a ball of green flame at you burning your skin.', 1);

-- Table: Puzzles
CREATE TABLE Puzzles ( 
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    PID        INTEGER REFERENCES Rooms ( RoomID ),
    PuzzleDescription   CHAR,
    PuzzleHint          CHAR,
    PuzzleTerminator    CHAR,
    PuzzleSolvedMessage CHAR, 
    PlayerID        INTEGER REFERENCES Players ( PlayerID )
);

INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (35, 'There is no light and you cannot see. There is a lantern lying on the ground with matches next too it covered in goo. Maybe the matches can be lit in the lantnern.', 'Try using "light" with what you are trying to light.', 'light matches', 'The matches light the lantern and you can now see the way!', 1);
INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (36, 'OH NO! Your foot triggered a rope trap and are snagged up into the air. Scissors are laying on the ground below and you think you can reach them with your fingertips. ', 'Try using "grab" with what you are trying to grab.', 'grab scissors', 'You grabbed the scissors and cut the rope. You fall with a steady landing but seem ok. The room is now cleared!', 1);
INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (37, 'As you journey further into the room, a poisonous gas is released and you are losing your breadth. A gas mask is lying on a table, if only you could reach the gas mask. ', 'Try using "reach" with what you are trying to reach.', 'reach mask', 'You reached the gas mask and were able to breath. The fumes dissipated and now the room is clear.', 1);
INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (38, 'The room has a blockade and you must climb over it to pass. A ladder is set aside, maybe if you could use the ladder. ', 'Try using "use" with what you are trying to use.', 'use ladder', 'You put the ladder against the rubbage and climbed over the blockade. You can now journey forward.', 1);
INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (39, 'You come across a massive puddle of green goo with a plank sticking out of it. You need to pass, but how? Try using the plank to cross. ', 'Try using "use" with what you are trying to use.', 'use plank', 'You maneuver the plank out of the puddle and place it just right to fit on each side of the puddle. You can now pass!', 1);
INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (40, 'As you journey further, you stumble and fall. Your leg is cut, and you need bandages. Try tearing your shirt to use as a bandage. ', 'Try using "tear" with what you are trying to tear.', 'tear shirt', 'You tear your shirt and use it as a bandage for your cut. You can now walk and venture on.', 1);

INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (41, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: 30 white horses on a red hill. First they chump, then they stump, then they stand still.', 'What do you use to eat your food?', 'teeth', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', 1);

INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (42, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: I have oceanless seas, sandless deserts, heightless mountains, and grassless lands. What am I?', 'What do you use to look at geography?', 'map', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', 1);

INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (43, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: This thing all things devours. Birds, beasts, trees, flowers. Gnaws iron, bites steel. Grinds hard stones to meal. Slays king, ruins town, and beats mountain down.', 'What never stops?', 'time', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', 1);

INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (44, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: It cannot be seen, cannot be felt,Cannot be heard, cannot be smelt.It lies behind stars and under hills,And empty holes it fills.It comes out first and follows after, Ends life, kills laughter. ', 'Opposite of light', 'dark', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', 1);

-- Table: Items
CREATE TABLE Items ( 
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ItemID   INTEGER REFERENCES Rooms ( RoomID ),
    ItemName        CHAR,
    Type        CHAR,
    ItemDescription CHAR,
    Value       INTEGER, 
    PlayerID        INTEGER REFERENCES Players ( PlayerID )
);

CREATE TABLE Inventory
(
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ItemName        CHAR,
    Type        CHAR,
    ItemDescription CHAR,
    Value       INTEGER, 
    PlayerID        INTEGER REFERENCES Players ( PlayerID )
);

INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (20, 'Heavy Textbook', 'WEAPON', 'Equip this item to increase your strenth in battle.', 2, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (21, 'Mountain Dew', 'ITEM', 'Use this item to heal your character''s current health points.', 25, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (22, 'Trash Can Lid', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 10, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (23, 'Coffee', 'ITEM', 'Use this item to heal your character''s current health points.', 14, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (24, 'Lunch Tray', 'WEAPON', 'Equip this item to increase your strenth in battle.', 3, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (25, 'Beer', 'ITEM', 'Use this item to heal your character''s current health points.', 12, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (26, 'Football Shoulder Pads', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 8, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (27, 'Skateboard', 'WEAPON', 'Equip this item to increase your strenth in battle.', 2, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (28, 'Work Boots', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 12, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (29, 'Coke', 'ITEM', 'Use this item to heal your character''s current health points.', 20, 1);
INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (30, 'Red Bull', 'ITEM', 'Use this item to heal your character''s current health points.', 20, 1);
