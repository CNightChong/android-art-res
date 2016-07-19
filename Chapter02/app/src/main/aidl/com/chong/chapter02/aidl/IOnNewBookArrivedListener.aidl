package com.chong.chapter02.aidl;

import com.chong.chapter02.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
