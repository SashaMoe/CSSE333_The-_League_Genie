USE [The_League_Genie]
GO

/****** Object:  Trigger [dbo].[Team_Turrets_number]    Script Date: 5/22/2015 8:11:58 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE trigger [dbo].[Team_Turrets_number] on [dbo].[Team] instead of insert as
declare @t int
select @t = Inserted.Turrets
from Inserted
declare @i int
select @i = Inserted.Inhibitor
from Inserted
if @t<0 or @t>4
begin
print 'Incorrect number of turrets'
end
else if @i<0 or @i>1
begin
print 'Incorrect number of Inhibitor'
end
else
begin
insert into [Team] select * from [Inserted]
end

GO

