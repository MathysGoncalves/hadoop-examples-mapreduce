package com.opstty.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MostReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private int max = Integer.MIN_VALUE;
    private Text district = new Text();

    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        if (sum > max) {
            this.district.set(key);
            this.max = sum;
        }
    }

    @Override
    public void cleanup(Context context) throws IOException, InterruptedException {
        context.write(district, new IntWritable(max));
    }
}