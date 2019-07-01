package com.bayudwiyansatria.io;

import com.bayudwiyansatria.utils.Utils;
import com.opencsv.*;

import java.io.*;
import java.util.List;

public class Files extends Array {
    private String[][] readCSV_OpenCSV_Array(String filename) {
        List<String[]> allRows = null;
        filename = filename + ".csv";
        try {
            FileReader filereader = new FileReader(filename);
            CSVReader reader = new CSVReader(filereader);
            allRows = reader.readAll();
        } catch (Exception exception){
            new Utils().warning(exception.toString());
        }

        String[][] data  = new String[allRows.size()][allRows.get(0).length];

        for(int i=0; i<allRows.size();i++){
            for(int j=0; j<allRows.get(0).length; j++){
                data[i][j] = allRows.get(i)[j];
            }
        }

        return data;

    }

    public List<String[]> readCSV_OpenCSV_List(String filename) {
        List<String[]> allRows = null;
        filename = filename + ".csv";
        try {
            FileReader filereader = new FileReader(filename);
            CSVReader reader = new CSVReader(filereader);
            allRows = reader.readAll();
        } catch (Exception exception){
            new Utils().warning(exception.toString());
        }

        return allRows;

    }

    private String[][] readCSV(String filename, String titlemode) {
        filename = filename + ".csv";
        String initData = "";
        String[] reader = null;
        String[][] data = null;
        String[][] parsedData;
        String[] mode = titlemode.split("title:");
        File readFile = new File(filename);

        try {
            String readByte;
            for(BufferedReader _reader = new BufferedReader(new java.io.FileReader(readFile));
                (readByte = _reader.readLine()) != null;
                initData = initData + readByte + "\n") {
            }
            reader = initData.split("\n");
        } catch (Exception exception) {
            new Utils().warning(exception.toString());
        }

        int i;
        if (mode[1].equalsIgnoreCase("no")) {
            data = new String[reader.length][reader[0].length()];

            for(i = 0; i < reader.length; ++i) {
                data[i] = reader[i].split(",");
            }
        } else if (mode[1].equalsIgnoreCase("col")) {
            data = new String[reader.length - 1][reader[0].length()];

            for(i = 1; i < reader.length; ++i) {
                data[i - 1] = reader[i].split(",");
            }
        } else {
            int j;
            if (mode[1].equalsIgnoreCase("row")) {
                parsedData = new String[reader.length][reader[0].length()];

                for(i = 0; i < reader.length; ++i) {
                    parsedData[i] = reader[i].split(",");
                }

                data = new String[reader.length][parsedData[0].length - 1];

                for(i = 0; i < reader.length; ++i) {
                    for(j = 1; j < parsedData[0].length; ++j) {
                        data[i][j - 1] = parsedData[i][j];
                    }
                }
            } else if (mode[1].equalsIgnoreCase("colrow")) {
                parsedData = new String[reader.length][reader[0].length()];

                for(i = 0; i < reader.length; ++i) {
                    parsedData[i] = reader[i].split(",");
                }

                data = new String[reader.length - 1][parsedData[0].length - 1];

                for(i = 1; i < reader.length; ++i) {
                    for(j = 1; j < parsedData[0].length; ++j) {
                        data[i - 1][j - 1] = parsedData[i][j];
                    }
                }
            } else if (mode[1].equalsIgnoreCase("rowcol")) {
                parsedData = new String[reader.length][reader[0].length()];

                for(i = 0; i < reader.length; ++i) {
                    parsedData[i] = reader[i].split(",");
                }

                data = new String[reader.length - 1][parsedData[0].length - 1];

                for(i = 1; i < reader.length; ++i) {
                    for(j = 1; j < parsedData[0].length; ++j) {
                        data[i - 1][j - 1] = parsedData[i][j];
                    }
                }
            } else {
                new Utils().warning("Title format is unknown.\nAvailable titles are = no, col, row, colrow or rowcol");
            }
        }

        return data;
    }

    public int[][] readCSV_int(String filename, String titlemode) {
        String[][] read = this.readCSV(filename, titlemode);
        return new Utils().String_to_int(read);
    }

    public int[][] readCSV_int(String filename) {
        String[][] read = this.readCSV(filename, "title:no");
        return new Utils().String_to_int(read);
    }

    public double[][] readCSV_double(String filename, String titlemode) {
        String[][] read = this.readCSV(filename, titlemode);
        return new Utils().String_to_double(read);
    }

    public double[][] readCSV_double(String filename) {
        String[][] read = this.readCSV(filename, "title:no");
        return new Utils().String_to_double(read);
    }

    public String[][] readCSV_String(String filename, String titlemode) {
        return this.readCSV(filename, titlemode);
    }

    public String[][] readCSV_String(String filename) {
        return this.readCSV(filename, "title:no");
    }

    public List readCSV_List(String filename) {
        List read = this.readCSV_OpenCSV_List(filename);
        return read;
    }




    public void saveCSV(int[] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException var8) {
                new Utils().warning("Can not find the file!");
            }

            int i = 0;

            String string;
            for(string = ""; i < data.length; ++i) {
                if (i != data.length - 1) {
                    string = string + data[i] + ",";
                } else {
                    string = string + data[i];
                }
            }

            if (writer != null) {
                writer.print(string);
            }
            if (writer != null) {
                writer.flush();
            }
        } catch (Exception e) {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void saveCSV(double[] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException var8) {
                new Utils().warning("Can not find the file!");
            }

            int i = 0;

            String string;
            for(string = ""; i < data.length; ++i) {
                if (i != data.length - 1) {
                    string = string + data[i] + ",";
                } else {
                    string = string + data[i];
                }
            }

            if (writer != null) {
                writer.print(string);
            }
            if (writer != null) {
                writer.flush();
            }
        } catch (Exception e) {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void saveCSV(String[] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException e) {
                new Utils().warning("Can not find the file");
            }

            int i = 0;

            String string;
            for(string = ""; i < data.length; ++i) {
                if (i != data.length - 1) {
                    string = string + data[i] + ",";
                } else {
                    string = string + data[i];
                }
            }

            if (writer != null) {
                writer.print(string);
            }
            if (writer != null) {
                writer.flush();
            }
        } catch (Exception e) {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void saveCSV(int[][] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException var10) {
                new Utils().warning("Can not find the file!");
            }

            for (int[] datum : data) {
                int j = 0;

                String string;
                for (string = ""; j < data[0].length; ++j) {
                    if (j != data[0].length - 1) {
                        string = string + datum[j] + ",";
                    } else {
                        string = string + datum[j];
                    }
                }

                if (writer != null) {
                    writer.println(string);
                }
            }

            if (writer != null) {
                writer.flush();
            }
        } catch (Exception var11) {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void saveCSV(double[][] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException var10) {
                new Utils().warning("Can not find the file!");
            }

            for (double[] datum : data) {
                int j = 0;

                String string;
                for (string = ""; j < data[0].length; ++j) {
                    if (j != data[0].length - 1) {
                        string = string + datum[j] + ",";
                    } else {
                        string = string + datum[j];
                    }
                }

                if (writer != null) {
                    writer.println(string);
                }
            }

            if (writer != null) {
                writer.flush();
            }
        } catch (Exception var11) {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void saveCSV(String[][] data, String filename) {
        PrintWriter writer = null;
        File file = new File(filename + ".csv");

        try {
            try {
                writer = new PrintWriter(new FileWriter(file));
            } catch (FileNotFoundException var10) {
                new Utils().warning("Can not find the file!");
            }

            for (String[] datum : data) {
                int j = 0;

                String string;
                for (string = ""; j < data[0].length; ++j) {
                    if (j != data[0].length - 1) {
                        string = string + datum[j] + ",";
                    } else {
                        string = string + datum[j];
                    }
                }

                if (writer != null) {
                    writer.println(string);
                }
            }

            if (writer != null) {
                writer.flush();
            }
        } catch (Exception var11) {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void saveDST(int[] data, String filename) {
        try {
            ObjectOutputStream _outputStream = new ObjectOutputStream(new FileOutputStream(filename + ".dst"));
            _outputStream.writeObject(data);
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

    }

    public void saveDST(double[] data, String filename) {
        try {
            ObjectOutputStream _outputStream = new ObjectOutputStream(new FileOutputStream(filename + ".dst"));
            _outputStream.writeObject(data);
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

    }

    public void saveDST(int[][] data, String filename) {
        try {
            ObjectOutputStream _outputStream = new ObjectOutputStream(new FileOutputStream(filename + ".dst"));
            _outputStream.writeObject(data);
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

    }

    public void saveDST(double[][] data, String filename) {
        try {
            ObjectOutputStream _outputStream = new ObjectOutputStream(new FileOutputStream(filename + ".dst"));
            _outputStream.writeObject(data);
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

    }

    public int[] readDST_1D_int(String filename) {
        int[] data = null;

        try {
            ObjectInputStream _inputStream = new ObjectInputStream(new FileInputStream(filename + ".dst"));
            data = (int[])_inputStream.readObject();
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

        return data;
    }

    public int[][] readDST_2D_int(String filename) {
        int[][] data = null;

        try {
            ObjectInputStream _inputStream = new ObjectInputStream(new FileInputStream(filename + ".dst"));
            data = (int[][])_inputStream.readObject();
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

        return data;
    }

    public double[] readDST_1D_double(String filename) {
        double[] data = null;

        try {
            ObjectInputStream _inputStream = new ObjectInputStream(new FileInputStream(filename + ".dst"));
            data = (double[])_inputStream.readObject();
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

        return data;
    }

    public double[][] readDST_2D_double(String filename) {
        double[][] data = null;

        try {
            ObjectInputStream _inputStream = new ObjectInputStream(new FileInputStream(filename + ".dst"));
            data = (double[][])_inputStream.readObject();
        } catch (Exception e) {
            new Utils().warning(e.toString());
        }

        return data;
    }


    public void convertCSV_to_DST_int(String filename, String titlemode) {
        boolean validation = false;
        byte bytes = -1;
        switch(titlemode.hashCode()) {
            case 0:
                if (titlemode.equals("title:col")) {
                    bytes = 1;
                }
                break;
            case 1:
                if (titlemode.equals("title:row")) {
                    bytes = 2;
                }
                break;
            case 2:
                if (titlemode.equals("title:rowcol")) {
                    bytes = 4;
                }
                break;
            case 3:
                if (titlemode.equals("title:no")) {
                    bytes = 0;
                }
                break;
            case 4:
                if (titlemode.equals("title:colrow")) {
                    bytes = 3;
                }
        }

        switch(bytes) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                validation = true;
                break;
        }

        if (validation) {
            try {
                int[][] dataset = this.readCSV_int(filename, titlemode);
                this.saveDST(dataset, filename);
            } catch (Exception var6) {
                new Utils().warning("Can not find the file!");
            }
        } else {
            new Utils().warning("Title format is unknown.\nAvailable titles are = no, col, row, colrow or rowcol");
        }

    }
}
