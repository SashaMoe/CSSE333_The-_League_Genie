USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Item win rate]    Script Date: 5/22/2015 8:10:56 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[Item win rate]
(@ItemName nchar(20))
as

declare @r float

set @r = (select round(cast((count(i1.name) / count(i2.name) * 100) as float), 2) as [Win Percent]
from item i1, item i2, Item_of_Player ip1, Item_of_Player ip2, Player p1, Player p2, Fight f1, Fight f2, Player_On_Team pt1, Player_on_Team pt2
where i1.name = ip1.Item_Name and ip1.Player_id = p1.Player_id and 
p1.Player_id = pt1.Player_id and pt1.Team_id = f1.Winning_Team and i1.name = @ItemName
and
i2.name = ip2.Item_Name and ip2.Player_id = p2.Player_id and 
p2.Player_id = pt2.Player_id and (pt2.Team_id = f2.Winning_Team or pt2.Team_id = f2.Losing_Team) and i2.name = @ItemName)

return @r
GO

