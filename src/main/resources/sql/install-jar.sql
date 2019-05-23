-- Procedure to install custom jar
call SQLJ.INSTALL_JAR('/opt/jars/splice-training-0.0.1-SNAPSHOT.jar', 'SPLICE.TRAINING_JAR', 0);

-- Procedure to update an existing custom jar
call SQLJ.REPLACE_JAR('/opt/jars/splice-training-0.0.1-SNAPSHOT.jar', 'SPLICE.TRAINING_JAR');

-- Add the jar to the derby classpath
call SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath', 'SPLICE.TRAINING_JAR');