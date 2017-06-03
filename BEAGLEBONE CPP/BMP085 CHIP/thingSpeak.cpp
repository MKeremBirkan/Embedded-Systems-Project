#include <iostream>
#include <sstream>
#include <fstream>
#include <stdlib.h>
#include <stdio.h>
#include "network/SocketClient.h"
using namespace std;
using namespace exploringBB;

int main(int argc, char *argv[]){

	
        system("echo bmp085 0x77 > /sys/class/i2c-adapter/i2c-1/new_device");
        //printf("INSTALL COMPLETE\n");

	ifstream infile,infile2;
	infile.open("/sys/bus/i2c/drivers/bmp085/1-0077/temp0_input");
	infile2.open("/sys/bus/i2c/drivers/bmp085/1-0077/pressure0_input");
	int temp,press;//temperature
	infile  >> temp;
	infile2 >> press;
	infile.close();
	infile2.close();
	temp = temp/10;//Celcius Unit
	press = press/100;//hectoPascal Unit
	//printf("-------------TEMP : %d\n",temp);

	ostringstream head, data;
   	//cout << "Starting ThingSpeak Example" << endl;
   	SocketClient sc("api.thingspeak.com",80);
   	data << "field1=" << temp << "&field2=" << press << endl;//argv[1]
   	sc.connectToServer();
   	head << "POST /update HTTP/1.1\n"
        << "Host: api.thingspeak.com\n"
        << "Connection: close\n"
        << "X-THINGSPEAKAPIKEY: 8XZYFJX5AT6LE1YC\n"
        << "Content-Type: application/x-www-form-urlencoded\n"
        << "Content-Length:" << string(data.str()).length() << "\n\n";
   	sc.send(string(head.str()));
   	sc.send(string(data.str()));
   	//string rec = sc.receive(1024);
   	//cout << "[" << rec << "]" << endl;
   	//cout << "End of ThingSpeak Example" << endl;

	
}
