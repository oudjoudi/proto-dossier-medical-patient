mvn clean install -P cloud,!embedded,!prod -DskipTests=true

af login jmaupoux@gmail.com

af tunnel postgres-9-1

af update dmp