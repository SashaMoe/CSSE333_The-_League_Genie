USE [The_League_Genie]
GO

/****** Object:  Trigger [dbo].[Trigger_on_Summoner]    Script Date: 5/22/2015 8:12:15 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


create trigger [dbo].[Trigger_on_Summoner] on [dbo].[Summoner] instead of insert as
declare @level int
select @level = Inserted.Level
from Inserted
if @level<5 or @level>30
begin
print 'Incorrect Summoner Level'
end
else
begin
insert into Summoner select * from [Inserted]
end
GO

