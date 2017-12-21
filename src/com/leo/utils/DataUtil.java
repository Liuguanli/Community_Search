package com.leo.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/18.
 */
public class DataUtil {

    /**
     * 以行为单位读取文件，用来预处理数据集loc-brightkite_totalCheckins和loc-brightkite_edges
     * loc-brightkite_totalCheckins 记录了位置
     * loc-brightkite_edges 记录了图结构
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        List<String> edges = new LinkedList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                edges.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return edges;
    }

    public static void extractData() {
        File file = new File("loc-brightkite_totalCheckins.txt");
        File file1 = new File("loc-brightkite_totalCheckins_extract.txt");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(file1));
            String tempString = null;
            String index = "-1";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                String[] attrs = tempString.split("\t");
                if (!attrs[0].equals(index)) {
                    StringBuilder builder = new StringBuilder();
                    if (attrs[2].equals("0.0") && attrs[3].equals("0.0")) {
                        continue;
                    }
                    builder.append(attrs[0]).append("\t").append(attrs[2]).append("\t").append(attrs[3]);
                    writer.write(builder.toString());
                    writer.newLine();
                    index = attrs[0];
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
