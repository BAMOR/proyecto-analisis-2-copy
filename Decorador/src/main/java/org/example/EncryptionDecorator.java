package org.example;

public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String data) {
        return "encrypted(" + data + ")";
    }

    private String decode(String data) {
        return data.replace("encrypted(", "").replace(")", "");
    }
}