package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Calendar;

public class OldestMapper extends Mapper<Object, Text, Text, IntWritable> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        String str = value.toString();
        String[] split = str.split(";");

        for(int index = 0; index < split.length; index++) {
            if(index == 2 && !split[0].contains("GEOPOINT") && (split[5].length() != 0)){
                int year_plantation = Integer.parseInt(split[5]);
                int current_year = Calendar.getInstance().get(Calendar.YEAR);
                IntWritable year = new IntWritable(current_year - year_plantation);
                Text district = new Text(split[1]);

                context.write(district, year);
            }
        }
    }
}

