package org.example;

public class FileDataSource implements DataSource {
    private String filename;
    private String data;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) {
        this.data = data;
        System.out.println("Writing to file [" + filename + "]: " + data);
    }

    @Override
    public String readData() {
        return data;
    }
}