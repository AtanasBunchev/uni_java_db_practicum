The .sql files should be run on a new h2 instance


# Setup H2 database

## CLI

To setup h2, run the following prompt (modify the path to h2.jar):

```
java -cp /usr/share/java/h2/h2.jar org.h2.tools.Shell
```
Then insert:
- URL: `jdbc:h2:~/test/db`
- username: `sa`
- password: `pwd2`
And exit the shell to release the lock on the new database file at `~/test/`.


Then start the h2 server with
```
java -cp /usr/share/java/h2/h2.jar org.h2.tools.Server
```

## Windows

**This section needs to be tested and completed with any missing details.**

- Unzip the h2 file downloaded from <https://www.h2database.com/html/download.html>.
- Find and run the `h2.bat` file in the `bin/` directory
- From the tray menu, right click at the `H2 Database Engine`, then select `Create new database`
- Insert the path to the new database, username and password. Use fully qualified path (starting with `C:\Users\....`). You need to insert the same path later in DBConnection.java
- Click Create database and ensure there are no exceptions.
- Visit <http://localhost:8082/>. Set `Saved settings` to `Generic H2 (Server)`. Insert `jdbc:h2:tcp://localhost/C:\Users\...` into `JDBC URL` (make sure to specify the same fully-qualified path you used earlier). Insert the username and password you selected earlier and press `Connect`.
- Once you see the database screen, copy the statement from `create_table.sql` into the SQL statement field and execute it.
- Open the `DBConnection.java` file in `src/` and insert the `JDBC URL`, username and password you used earlier as parameters for the `DriverManager.getConnection` call.

