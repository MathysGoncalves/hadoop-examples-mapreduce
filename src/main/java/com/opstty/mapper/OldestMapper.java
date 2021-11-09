package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OldestMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    private int line = 0;

    public void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        if (line != 0){
            try{
                String[] fields = value.toString().split(";");
                int district = Integer.parseInt(fields[1]);
                int year = Integer.parseInt(fields[5]);
                context.write(new IntWritable(year), new IntWritable(district));
            }catch(NumberFormatException ex){
            }
        }
        line++;
    }
}


