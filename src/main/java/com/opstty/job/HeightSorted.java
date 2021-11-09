package com.opstty.job;

import com.opstty.mapper.HSMapper;
import com.opstty.mapper.KindsMapper;
import com.opstty.reducer.HSReducer;
import com.opstty.reducer.KindsReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

public class HeightSorted {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Show wiki on how to use the command if the number of necessary parameters are not enough
        if (otherArgs.length < 2) {
            System.err.println("Usage: height sorted <in> <out>");
            System.exit(2);
        }

        BasicConfigurator.configure();
        Job job = Job.getInstance(conf, "HeightSorted"); // Job name
        job.setJarByClass(HeightSorted.class);
        // Set Mapper to species_mapper
        job.setMapperClass(HSMapper.class);
        // Set Reducer to default_reducer : no particular function to apply
        job.setReducerClass(HSReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
