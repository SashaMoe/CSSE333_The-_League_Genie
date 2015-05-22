USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Generate Game]    Script Date: 5/22/2015 8:09:59 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[Generate Game]
(@matchid int,
@time int,
--match

@t1id int,
@t1turrets int, 
@t1inhib int, 
@t1gold int,
@t1exp int, 
@t1kills int,
@t1deaths int,
--team1

@t2id int,
@t2turrets int, 
@t2inhib int, 
@t2gold int,
@t2exp int, 
@t2kills int,
@t2deaths int,
--team2

@u1id int,
@c1 varchar(50),
@p1id int,
@p1kills int,
@p1deaths int,
@p1summskill int,
@p1pos varchar(50),
@p1assists int,
@p1cs int,
@p1i1 varchar(50),
@p1i2 varchar(50),
--player1

@u2id int,
@c2 varchar(50),
@p2id int,
@p2kills int,
@p2deaths int,
@p2summskill int,
@p2pos varchar(50),
@p2assists int,
@p2cs int,
@p2i1 varchar(50),
@p2i2 varchar(50),
--player2

@u3id int,
@c3 varchar(50),
@p3id int,
@p3kills int,
@p3deaths int,
@p3summskill int,
@p3pos varchar(50),
@p3assists int,
@p3cs int,
@p3i1 varchar(50),
@p3i2 varchar(50),
--player3

@u4id int,
@c4 varchar(50),
@p4id int,
@p4kills int,
@p4deaths int,
@p4summskill int,
@p4pos varchar(50),
@p4assists int,
@p4cs int,
@p4i1 varchar(50),
@p4i2 varchar(50),
--player4

@u5id int,
@c5 varchar(50),
@p5id int,
@p5kills int,
@p5deaths int,
@p5summskill int,
@p5pos varchar(50),
@p5assists int,
@p5cs int,
@p5i1 varchar(50),
@p5i2 varchar(50),
--player5
--end of team 1

@u6id int,
@c6 varchar(50),
@p6id int,
@p6kills int,
@p6deaths int,
@p6summskill int,
@p6pos varchar(50),
@p6assists int,
@p6cs int,
@p6i1 varchar(50),
@p6i2 varchar(50),
--player6

@u7id int,
@c7 varchar(50),
@p7id int,
@p7kills int,
@p7deaths int,
@p7summskill int,
@p7pos varchar(50),
@p7assists int,
@p7cs int,
@p7i1 varchar(50),
@p7i2 varchar(50),
--player7

@u8id int,
@c8 varchar(50),
@p8id int,
@p8kills int,
@p8deaths int,
@p8summskill int,
@p8pos varchar(50),
@p8assists int,
@p8cs int,
@p8i1 varchar(50),
@p8i2 varchar(50),
--player8

@u9id int,
@c9 varchar(50),
@p9id int,
@p9kills int,
@p9deaths int,
@p9summskill int,
@p9pos varchar(50),
@p9assists int,
@p9cs int,
@p9i1 varchar(50),
@p9i2 varchar(50),
--player9

@u10id int,
@c10 varchar(50),
@p10id int,
@p10kills int,
@p10deaths int,
@p10summskill int,
@p10pos varchar(50),
@p10assists int,
@p10cs int,
@p10i1 varchar(50),
@p10i2 varchar(50))
--player10
--end of team 2

as

--create match
insert into match (Match_id, Time)
	values(@matchid, @time)

--create two teams
insert into team (Team_id, Turrets, Inhibitor, Gold, Experience, [Kill], Death)
	values(@t1id, @t1turrets, @t1inhib, @t1gold, @t1exp, @t1kills, @t1deaths)

insert into team (Team_id, Turrets, Inhibitor, Gold, Experience, [Kill], Death)
	values(@t2id, @t2turrets, @t2inhib, @t2gold, @t2exp, @t2kills, @t2deaths)

--create fight
insert into fight (Match_id, Winning_Team, Losing_Team)
	values(@matchid, @t1id, @t2id)

--create ten players
--team 1 (winners)
insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p1id, @p1kills, @p1deaths, @p1summskill, @p1pos, @p1assists, @p1cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p2id, @p2kills, @p2deaths, @p2summskill, @p2pos, @p2assists, @p2cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p3id, @p3kills, @p3deaths, @p3summskill, @p3pos, @p3assists, @p3cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p4id, @p4kills, @p4deaths, @p4summskill, @p4pos, @p4assists, @p4cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p5id, @p5kills, @p5deaths, @p5summskill, @p5pos, @p5assists, @p5cs)

--team 2 (losers)
insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p6id, @p6kills, @p6deaths, @p6summskill, @p6pos, @p6assists, @p6cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p7id, @p7kills, @p7deaths, @p7summskill, @p7pos, @p7assists, @p7cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p8id, @p8kills, @p8deaths, @p8summskill, @p8pos, @p8assists, @p8cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p9id, @p9kills, @p9deaths, @p9summskill, @p9pos, @p9assists, @p9cs)

insert into player (Player_id, Kills, Deaths, Summoner_skill, Position, Assists, Creep_score)
	values(@p10id, @p10kills, @p10deaths, @p10summskill, @p10pos, @p10assists, @p10cs)

--create ten summoners
--Team 1 (winners)
insert into summoner ([User_id], [Level])
	values(@u1id, 30)

insert into summoner ([User_id], [Level])
	values(@u2id, 30)

insert into summoner ([User_id], [Level])
	values(@u3id, 29)

insert into summoner ([User_id], [Level])
	values(@u4id, 30)

insert into summoner ([User_id], [Level])
	values(@u5id, 28)

--Team 2 (losers)
insert into summoner ([User_id], [Level])
	values(@u6id, 29)

insert into summoner ([User_id], [Level])
	values(@u7id, 24)

insert into summoner ([User_id], [Level])
	values(@u8id, 25)

insert into summoner ([User_id], [Level])
	values(@u9id, 30)

insert into summoner ([User_id], [Level])
	values(@u10id, 30)

--create ten player-team relationships
--Team 1 (Winners)
insert into player_on_team (Player_id, Team_id)
	values(@p1id, @t1id)

insert into player_on_team (Player_id, Team_id)
	values(@p2id, @t1id)

insert into player_on_team (Player_id, Team_id)
	values(@p3id, @t1id)

insert into player_on_team (Player_id, Team_id)
	values(@p4id, @t1id)

insert into player_on_team (Player_id, Team_id)
	values(@p5id, @t1id)

--Team 2 (Losers)
insert into player_on_team (Player_id, Team_id)
	values(@p6id, @t2id)

insert into player_on_team (Player_id, Team_id)
	values(@p7id, @t2id)

insert into player_on_team (Player_id, Team_id)
	values(@p8id, @t2id)

insert into player_on_team (Player_id, Team_id)
	values(@p9id, @t2id)

insert into player_on_team (Player_id, Team_id)
	values(@p10id, @t2id)

--create ten summoner-player relationships
--Team 1 (Winners)
insert into summoner_of_player ([User_id], Player_id)
	values(@u1id, @p1id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u2id, @p2id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u3id, @p3id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u4id, @p4id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u5id, @p5id)

--Team 2 (Losers)
insert into summoner_of_player ([User_id], Player_id)
	values(@u6id, @p6id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u7id, @p7id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u8id, @p8id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u9id, @p9id)

insert into summoner_of_player ([User_id], Player_id)
	values(@u10id, @p10id)

--create ten champion-player relationships
--Team 1 (Winners)
insert into champion_of_player (Champion_name, Player_id)
	values(@c1, @p1id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c2, @p2id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c3, @p3id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c4, @p4id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c5, @p5id)

--Team 2 (Losers)
insert into champion_of_player (Champion_name, Player_id)
	values(@c6, @p6id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c7, @p7id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c8, @p8id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c9, @p9id)

insert into champion_of_player (Champion_name, Player_id)
	values(@c10, @p10id)

--create ten item-player relationships (boots)
--Team 1 (Winners)
insert into item_of_player (Player_id, Item_name)
	values(@p1id, @p1i1)

insert into item_of_player (Player_id, Item_name)
	values(@p2id, @p2i1)

insert into item_of_player (Player_id, Item_name)
	values(@p3id, @p3i1)

insert into item_of_player (Player_id, Item_name)
	values(@p4id, @p4i1)

insert into item_of_player (Player_id, Item_name)
	values(@p5id, @p5i1)

--Team 2 (Losers)
insert into item_of_player (Player_id, Item_name)
	values(@p6id, @p6i1)

insert into item_of_player (Player_id, Item_name)
	values(@p7id, @p7i1)

insert into item_of_player (Player_id, Item_name)
	values(@p8id, @p8i1)

insert into item_of_player (Player_id, Item_name)
	values(@p9id, @p9i1)

insert into item_of_player (Player_id, Item_name)
	values(@p10id, @p10i1)

--create ten item-player relationships
--Team 1 (Winners)
insert into item_of_player (Player_id, Item_name)
	values(@p1id, @p1i2)

insert into item_of_player (Player_id, Item_name)
	values(@p2id, @p2i2)

insert into item_of_player (Player_id, Item_name)
	values(@p3id, @p3i2)

insert into item_of_player (Player_id, Item_name)
	values(@p4id, @p4i2)

insert into item_of_player (Player_id, Item_name)
	values(@p5id, @p5i2)

--Team 2 (Losers)
insert into item_of_player (Player_id, Item_name)
	values(@p6id, @p6i2)

insert into item_of_player (Player_id, Item_name)
	values(@p7id, @p7i2)

insert into item_of_player (Player_id, Item_name)
	values(@p8id, @p8i2)

insert into item_of_player (Player_id, Item_name)
	values(@p9id, @p9i2)

insert into item_of_player (Player_id, Item_name)
	values(@p10id, @p10i2)
GO

