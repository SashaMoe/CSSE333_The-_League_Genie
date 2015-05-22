USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Position win rate]    Script Date: 5/22/2015 8:11:13 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[Position win rate]
(@PosName nchar(20))
as

select @PosName, round(cast((count(p1.position) / count(p2.position) * 100) as float), 2) as [Win Percent]
from Player p1, Player p2, Fight f1, Fight f2, Player_On_Team pt1, Player_on_Team pt2
where p1.Player_id = pt1.Player_id and pt1.Team_id = f1.Winning_Team and p1.Position = @PosName
and
p2.Player_id = pt2.Player_id and (pt2.Team_id = f2.Winning_Team or pt2.Team_id = f2.losing_team) and p2.Position = @PosName
GO

