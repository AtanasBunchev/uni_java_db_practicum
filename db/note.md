The .sql files should be run on a new h2 instance

To setup h2, run the following prompt (modify the path to h2.jar):

```
java -cp /usr/share/java/h2/h2.jar org.h2.tools.Shell
```
Then insert
URL: `jdbc:h2:~/test/db`
username: `sa`
password: `pwd2`

