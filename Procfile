web: java -Dserver.port=$PORT $JAVA_OPTS -jar tastr-fatjar/target/tastr.jar --db.url=$DB_URL --db.username=$DB_USERNAME --db.password=$DB_PASSWORD
release: java $JAVA_OPTS -jar tastr-infrastructure/target/tastr-migration.jar $DB_URL $DB_USERNAME $DB_PASSWORD public
