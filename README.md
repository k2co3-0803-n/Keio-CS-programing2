## how to use
compile all java files.

```java
javac *.java
```

exec MyApp
```
java -cp .:sqlite-jdbc-3.46.0.0.jar:slf4j-api-1.7.32.jar:slf4j-api-1.7.32.jar MyApp
```

when you want to reset .db
```
sqlite3 -column -header data.db 

sqlite> .read ./create_table.sql
sqlite> .read ./insert_into.sql
```