package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class OldestReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private int tree_age = Integer.MIN_VALUE;
    private Text district = new Text();

    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        for(IntWritable value: values){
            if(value.get() > tree_age){
                tree_age = value.get();
                district.set(key);
            }
        }
    }

    @Override
    public void cleanup(Context context) throws IOException, InterruptedException {
        context.write(district, new IntWritable(tree_age));
    }
}