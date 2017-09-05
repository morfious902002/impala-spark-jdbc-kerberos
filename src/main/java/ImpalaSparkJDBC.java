import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by morfious902002 on 9/5/2017.
 */
public class ImpalaSparkJDBC {
    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("Usage: ImpalaSparkJDBC <url> <tableName>");
            System.out.println("       (secure)   jdbc:impala://impala-host:21050/;AuthMech=1;KrbRealm=realm;KrbHostFQDN=krbHost;KrbServiceName=impala");
            System.out.println("       (insecure) jdbc:hive2://impala-host:21050/;auth=noSasl");
            System.exit(1);
        }

        Properties prop = new Properties();
        prop.setProperty("driver","com.cloudera.impala.jdbc41.Driver");

        System.setProperty("java.security.auth.login.config", "jaas.conf");
        System.setProperty("sun.security.jgss.debug","true");
        System.setProperty("javax.security.auth.useSubjectCredsOnly","false");

        SparkConf sparkConf = new SparkConf().setAppName("ImpalaJDBC");
        SparkContext sc = new SparkContext(sparkConf);
        SQLContext sqlContext = SQLContext.getOrCreate(sc);
        sqlContext.read().jdbc(args[0], args[1], prop).show();
    }
}
