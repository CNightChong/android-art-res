package com.chong.chapter02.binderpool;

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}