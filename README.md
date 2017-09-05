# impala-spark-jdbc-kerberos

Following configs are required.

spark.executor.extraJavaOptions=-Djava.security.auth.login.config=jaas.conf -Djavax.security.auth.useSubjectCredsOnly=false

spark.yarn.dist.files=/path/to/jaas.conf,/path/to/your.keytab



Since, impala jdbc drivers are not in public repos you will have to download them to your local repo and configure it. 
