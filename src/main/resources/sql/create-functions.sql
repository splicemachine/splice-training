create function getleft(val1 varchar(100), val2 int) returns varchar(100)
language java
parameter style java
no sql
external name 'com.splicemachine.training.function.CustomFunctions.getleft';

create function getright(val1 varchar(100), val2 int) returns varchar(100)
language java
parameter style java
no sql
external name 'com.splicemachine.training.function.CustomFunctions.getright';

create function capitalize(val1 varchar(100)) returns varchar(100)
language java
parameter style java
no sql
external name 'com.splicemachine.training.function.CustomFunctions.capitalize';

create function replacestr(val1 varchar(100), val2 varchar(100), val3 varchar(100)) returns varchar(100)
language java
parameter style java
no sql
external name 'com.splicemachine.training.function.CustomFunctions.replacestr';

create function indexofstr(val1 varchar(100), val2 varchar(100)) returns integer
language java
parameter style java
no sql
external name 'com.splicemachine.training.function.CustomFunctions.indexofstr';