package com.paulo.backend.cartapp.backend_cartapp.factory;

public class WriteOnlyPermission implements DatabasePermission {
    @Override
    public boolean canRead() {
        return false;
    }

    @Override
    public boolean canWrite() {
        return true;
    }

    @Override
    public String getPermissionLevel() {
        return "WRITE_ONLY";
    }
}