package com.chong.chapter02.aidl;

import com.chong.chapter02.aidl.Book;
import com.chong.chapter02.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}