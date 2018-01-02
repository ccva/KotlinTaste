// IPushServiceInterface.aidl
package com.va.kotlintaste;

// Declare any non-default types here with import statements

interface IPushServiceInterface {

       void registerPushService();

       void unRegisterPushService();

       void insert();

       void query();

       void clear();

       void update();

       void notification();

}
