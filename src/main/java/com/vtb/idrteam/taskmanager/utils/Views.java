package com.vtb.idrteam.taskmanager.utils;

public final class Views {
    public interface Id {}

    public interface Small extends Id{}

    public interface BigUser extends Small{}

    public interface FullUser extends Small{}

    public interface BigProject extends Small{}

    public interface FullProject extends Small{}

    public interface FullTask extends Small{}
}
