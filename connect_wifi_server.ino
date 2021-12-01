//#include <WiFi.h>

#include <ESP8266WiFi.h>
#define relay 16
#define led 2
#define led1 5
#define led2 4
#define led3 0

const char* ssid ="FIRDAUS";
const char* password ="YABUNAYA";
//const char* ssid = "UNDIKA";
//const char* password = "taipusss";

WiFiServer server(8080);

int i=0;

void setup() 
{
  // put your setup code here, to run once:
  pinMode(relay,OUTPUT);
  digitalWrite(relay,HIGH);
  pinMode(led1,OUTPUT);
  digitalWrite(led1,LOW);
  pinMode(led2,OUTPUT);
  digitalWrite(led2,LOW);
  pinMode(led3,OUTPUT);
  digitalWrite(led3,LOW);
//  pinMode(led,OUTPUT);
//  digitalWrite(led,LOW);
  Serial.begin(115200);
    
  WiFi.begin(ssid, password);
  while(WiFi.status()!=WL_CONNECTED){
    Serial.println("Proses koneksi ke wifi....");
    delay(500);
  }
  Serial.println("Wifi Terkoneksi");
  Serial.print("IP Address = ");
  Serial.println(WiFi.localIP());
  server.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
  WiFiClient client1=server.available();

  if(client1)
  {
    while (client1.connected())
    {
      while (client1.available()>0)
      {
        char x = client1.read();
        if(x == 'a')
        {
          digitalWrite(relay,HIGH);
          //server.write('A');
        }
        else if(x == 'b')
        {
          digitalWrite(relay,LOW);
        }

        if(x == 'c')
        {
          digitalWrite(led1,HIGH);
          //server.write('A');
        }
        else if(x == 'd')
        {
          digitalWrite(led1,LOW);
        }

        if(x == 'e')
        {
          digitalWrite(led2,HIGH);
          //server.write('A');
        }
        else if(x == 'f')
        {
          digitalWrite(led2,LOW);
        }

        if(x == 'g')
        {
          digitalWrite(led3,HIGH);
          //server.write('A');
        }
        else if(x == 'h')
        {
          digitalWrite(led3,LOW);
        }
        Serial.println(x);
      }
      delay(10);
    }
  }
  client1.stop();
  //Serial.println("Client disconnected");
//    if (client1.available()>0)
//    {
//      char x = client1.read();
//      if(x == 'a')
//      {
//        digitalWrite(led,HIGH);
//        server.write('A');
//      }
//      else if(x == 'b')
//      {
//        digitalWrite(led,LOW);
//      }
//      Serial.println(x); 
//    }
//    delay(1000); 
    
}
