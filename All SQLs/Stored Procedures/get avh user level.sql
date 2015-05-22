USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[get avg user level]    Script Date: 5/22/2015 8:10:09 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[get avg user level]
	(@Match_id [int])



as
select round(avg(cast([Level] as float)), 2) as [Average Summoner Level]
from Summoner s, Summoner_of_Player sp, Match m, Fight f
where s.[User_id] = sp.[User_id] and m.Match_id = f.Match_id and m.Match_id = @Match_id
GO

