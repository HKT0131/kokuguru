## mysqltable

### database name : kokuguru

### tablename : shop_info
|Field|Type|Null|Key|Default|Extra|
|:---:|:---:|:---:|:---:|:---:|:---:|

|id|int|NO|PRI|NULL|auto_increment|
|shop_name|varchar(30)|NO||NULL||
|shop_reason|varchar(255)|NO||NULL||
|google_map|varchar(500)|NO||NULL||

### tablename : review
|Field|Type|Null|Key|Default|Extra|
|:---:|:---:|:---:|:---:|:---:|:---:|
|shop_id|int|NO|MUL|NULL||
|reason|varchar(100)|NO||NULL||
