USE [The_League_Genie]
GO

/****** Object:  StoredProcedure [dbo].[Authenticate Admin]    Script Date: 5/22/2015 8:09:17 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[Authenticate Admin]
(@password varchar(20))
as

declare @r smallint

if (select [password] from [Admin_login] where CONVERT(NVARCHAR(32),HashBytes('MD5', @password),2)=[password]) is null
begin
	return -1
end
else
begin
	return 0
end

GO

