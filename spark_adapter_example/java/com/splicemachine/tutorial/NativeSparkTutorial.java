package com.splicemachine.tutorial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import com.splicemachine.derby.impl.SpliceSpark;
import com.splicemachine.spark.splicemachine.SplicemachineContext;

import scala.collection.JavaConverters;

public class NativeSparkTutorial {

    public static void main(String[] args) throws Exception {

        if (args.length != 4) {
            System.err.println("Incorrect number of params ");
            System.exit(1);
        }

        final String regionServer = args[0];
        final String port = args[1];
        final String user = args[2];
        final String password = args[3];

        String dbUrl = "jdbc:splice://" + regionServer + ":" + port + "/splicedb;user=" + user + ";" + "password=" + password;

        SparkConf conf = new SparkConf();
        SparkSession spark = SparkSession.builder().appName("NativeSparktutor").config(conf).getOrCreate();
        SplicemachineContext splicemachineContext = setupSpliceContext(dbUrl,spark);
        try {
        	tutor(splicemachineContext, spark);
            spark.stop();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);

    }
    
    private static void tutor(SplicemachineContext splicemachineContext,SparkSession spark) {
    	List<Row> cars = new ArrayList<Row>();
    	StructType schema = DataTypes.createStructType(new StructField[] {
                DataTypes.createStructField("SERIAL",  DataTypes.IntegerType, true),
                DataTypes.createStructField("MAKE", DataTypes.StringType, true),
                DataTypes.createStructField("MODEL", DataTypes.StringType, true)
        });
    	
    	cars.add(RowFactory.create(1, "Toyota", "Camry"));
    	cars.add(RowFactory.create(2, "Honda", "Accord"));
    	cars.add(RowFactory.create(3, "Subaru", "Impreza"));
    	cars.add(RowFactory.create(4, "Chevy", "Volt"));
    	
    	
    	Dataset<Row> carsDF = spark.createDataFrame(cars, schema);
    	carsDF.show();
    	
    	System.out.println("start create table ...");
    	splicemachineContext.createTable("test.car", schema, 
    			JavaConverters.asScalaIteratorConverter(Arrays.asList("PRIMARY KEY (SERIAL)").iterator()).asScala().toSeq(), "");
    	System.out.println("done create table ... ");
    	
    	StructType outputSchema = splicemachineContext.getSchema("test.car");
    	outputSchema.printTreeString();
    	
    	System.out.println("start insert ...");
    	splicemachineContext.insert(carsDF, "test.car");
    	System.out.println("done insert ... ");
    	
    	System.out.println("start select ...");
    	Dataset<Row> selectedCarsDF = splicemachineContext.df("select * from test.car");
    	System.out.println("done select ...");
    	
    	selectedCarsDF.count();
    	selectedCarsDF.show();
    	
    	splicemachineContext.dropTable("test.car");
    	System.out.println("Table dropped...");
    			
    }

    private static SplicemachineContext setupSpliceContext(String dbUrl,SparkSession sparkSession) {
        System.out.println("creatingSpliceMachineContext");
        SpliceSpark.setContext(sparkSession.sparkContext());
        SplicemachineContext splicemachineContext = new SplicemachineContext(dbUrl);
        return splicemachineContext;
    }


}