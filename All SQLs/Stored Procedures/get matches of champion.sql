USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[get matches of champion]    Script Date: 5/22/2015 8:10:19 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[get matches of champion]
(@champ_name nchar(20))
as

print @champ_name

if (select count(Name) from Champion where Name = @champ_name) = 0
begin
print 'Invalid Champion name, please try again.'
return 1
end

else
begin
select m.Match_id
from Match m, Fight f, Team t, Champion_of_Player cp, Champion c, Player_on_Team pt, Player p
where
	c.Name = @champ_name and
	m.Match_id = f.Match_id and
	(f.Winning_Team = t.Team_id or f.Losing_Team = t.Team_id) and
	pt.Team_id = t.Team_id and
	pt.Player_id = p.Player_id and
	p.Player_id = cp.Player_id and 
	cp.Champion_Name = c.Name
end

GO

