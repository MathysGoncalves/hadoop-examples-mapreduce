package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OldestReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
    public Boolean first = true;

    public void reduce(IntWritable year, Iterable<IntWritable> districts, Context context)
            throws IOException,InterruptedException{
        if (first){
            for(IntWritable district : districts){
                try{
                    context.write(year, district);
                }catch(Exception ex){
                }
            }
        }
        first = false;
    }
}