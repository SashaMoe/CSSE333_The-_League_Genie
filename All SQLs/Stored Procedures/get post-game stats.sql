USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[get post-game stats]    Script Date: 5/22/2015 8:10:38 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


create procedure [dbo].[get post-game stats]
	(@Match_id [int])
as

select distinct ip1.Player_id as Player, c.Name, p.Kills, p.Deaths, p.Assists, ip1.Item_Name as [Item 1], ip2.Item_Name as [Item 2], p.Creep_score
from Item_of_Player ip1, Item_of_Player ip2, Summoner s, Summoner_of_Player sp, Match m, Fight f, Champion c, Player p, Champion_of_Player cp
where ip1.Item_Name < ip2.Item_Name and ip1.Item_Name != ip2.Item_Name and ip1.Player_id = ip2.Player_id
and s.[User_id] = sp.[User_id] and m.Match_id = f.Match_id  and c.Name = cp.Champion_Name and cp.Player_id = p.Player_id
and p.Player_id = ip1.Player_id and m.Match_id = @Match_id
GO

