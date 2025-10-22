package com.paulo.backend.cartapp.backend_cartapp.factory;

public class ReadWritePermission implements DatabasePermission {
    @Override
    public boolean canRead() {
        return true;
    }

    @Override
    public boolean canWrite() {
        return true;
    }

    @Override
    public String getPermissionLevel() {
        return "READ_WRITE";
    }
}