SELECT Teams.Num ,Teams.Team ,Teams.City ,COUNT(Players.PlayerID) FROM Teams LEFT JOIN Players ON Teams.Num = Players.TeamNum GROUP BY Teams.Num, Teams.Team,Teams.City;