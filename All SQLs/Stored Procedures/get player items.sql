USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[get player items]    Script Date: 5/22/2015 8:10:27 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[get player items]
	(@Match_id [int])
as
select distinct ip1.Player_id as Player, ip1.Item_Name as [Item 1], ip2.Item_Name as [Item 2]
from Item_of_Player ip1, Item_of_Player ip2, Summoner s, Summoner_of_Player sp, Match m, Fight f
where ip1.Item_Name < ip2.Item_Name and ip1.Item_Name != ip2.Item_Name and ip1.Player_id = ip2.Player_id
and s.[User_id] = sp.[User_id] and m.Match_id = f.Match_id and m.Match_id = @Match_id
order by ip1.Player_id, ip1.Item_Name

GO

