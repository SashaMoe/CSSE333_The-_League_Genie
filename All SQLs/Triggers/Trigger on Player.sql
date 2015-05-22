USE [The_League_Genie]
GO

/****** Object:  Trigger [dbo].[Trigger_on_Player]    Script Date: 5/22/2015 8:11:40 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE trigger [dbo].[Trigger_on_Player] on [dbo].[Player] instead of insert as
declare @champ_level int
select @champ_level = Inserted.Champion_level
from Inserted
declare @position nchar(10)
select @position = Inserted.Position
from Inserted
if @champ_level<1 or @champ_level>18
begin
print 'Incorrect Champion Level'
end
else if @position!='APC' or @position!='ADC' or @position!='Tank' or @position!='Support'
begin
print 'Incorrect Position'
end
else
begin
insert into Player select * from [Inserted]
end
GO

