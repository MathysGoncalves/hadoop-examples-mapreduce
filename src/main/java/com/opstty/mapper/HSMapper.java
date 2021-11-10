package com.opstty.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HSMapper extends Mapper<Object, Text, DoubleWritable, Text> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String str = value.toString();
        String[] split = str.split(";");

        for(int index = 0; index < split.length; index++) {
            if(index == 2 && !split[0].contains("GEOPOINT") && (split[6].length() != 0)) {
                context.write(new DoubleWritable(Double.parseDouble(split[6])), new Text(split[3]));
            }
        }
    }
}