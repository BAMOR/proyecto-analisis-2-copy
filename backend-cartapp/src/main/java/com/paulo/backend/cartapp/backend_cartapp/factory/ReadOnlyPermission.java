package com.paulo.backend.cartapp.backend_cartapp.factory;

public class ReadOnlyPermission implements DatabasePermission {
    @Override
    public boolean canRead() {
        return true;
    }

    @Override
    public boolean canWrite() {
        return false;
    }

    @Override
    public String getPermissionLevel() {
        return "READ_ONLY";
    }
}