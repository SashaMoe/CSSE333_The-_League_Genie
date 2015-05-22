USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Kill Difference]    Script Date: 5/22/2015 8:11:04 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


create procedure [dbo].[Kill Difference]
(@matchid int)
as

select (t1.[kill] - t2.[kill]) as [Kill Difference]
from team t1, team t2, fight f
where f.Winning_team = t1.team_id and f.losing_team = t2.team_id and f.match_id=@matchid
GO

