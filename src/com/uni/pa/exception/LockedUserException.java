package com.uni.pa.exception;

public class LockedUserException extends Exception{

    public LockedUserException(){

    }

    public LockedUserException(String message){
        super(message);}

    public LockedUserException(Exception ex){
        super(ex);}

    public LockedUserException(String message, Exception ex){
        super(message, ex);}
}
