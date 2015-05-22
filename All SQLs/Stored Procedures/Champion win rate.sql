USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Champion win rate]    Script Date: 5/22/2015 8:09:31 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[Champion win rate]
(@ChampName nchar(20))
as

declare @r float

set @r = (select round(cast((count(c1.name) / count(c2.name) * 100) as float), 2) as [Win Percent]
from Player p1, Player p2, Fight f1, Fight f2, Player_On_Team pt1, Player_on_Team pt2, Champion c1, Champion c2, champion_of_player cp1, champion_of_player cp2
where p1.Player_id = pt1.Player_id and 
pt1.Team_id = f1.Winning_Team and cp1.champion_name = c1.name and cp1.Player_id = p1.Player_id and pt1.player_id = p1.player_id and 
pt1.team_id = f1.winning_team and c1.name = @ChampName 
and
pt2.Team_id = f2.Winning_Team and cp2.champion_name = c2.name and cp2.Player_id = p2.Player_id and pt2.player_id = p2.player_id and 
(pt2.team_id = f2.winning_team or pt2.team_id = f2.losing_team) and c2.name = @ChampName )

return @r
GO

