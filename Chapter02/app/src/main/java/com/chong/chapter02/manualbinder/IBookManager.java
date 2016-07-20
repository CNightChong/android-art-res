package com.chong.chapter02.manualbinder;

import java.util.List;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * 手动实现Binder
 * 声明一个AIDL性质的接口
 * IInterface接口中有asBinder()方法
 */
public interface IBookManager extends IInterface {

    /**
     * Binder描述符，Binder的唯一标识
     */
    static final String DESCRIPTOR = "com.chong.chapter02.manualbinder.IBookManager";

    /**
     * id用于标识getBookList()方法
     */
    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 0);
    /**
     * id用于标识addBook()方法
     */
    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1);

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;
}
