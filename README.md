## Time zones in Java vs time zones in PostgreSQL

To run it you need to have a postgresql database named `tz` running on your localhost, 
and a user named `user` with a password `password` that can connect to that database.

To start the app, fetch the repo and run it with `./gradlew run`

## Result

Checked against `openjdk 11.0.10 2021-01-19` and PostgreSQL 14.0

Timezones in Java but not in PostgreSQL:
`ACT`, `AET`, `AGT`, `ART`, `AST`, `BET`, `BST`, `CAT`, `CNT`, `CST`, `CTT`, `EAT`, `ECT`, `IET`, `IST`, `JST`, `MIT`, 
`NET`, `NST`, `PLT`, `PNT`, `PRT`, `PST`, `SST`, `VST`

Timezones in PostgreSQL but not in Java:
`Factory`, `GMT+0`, `GMT-0`, `Pacific/Kanton`, `ROC`, `localtime`, `posixrules`

The PostgreSQL timezones whose names start with `posix` where excluded from the query.