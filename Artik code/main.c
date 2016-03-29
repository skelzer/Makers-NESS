#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>
#include <errno.h>
#include <termios.h>
#include <unistd.h>
#include <fcntl.h>

  char *portname = "/dev/ttySAC3";
  int fd;
  char buf [100];
  
  int estado=0;

struct string {
  char *ptr;
  size_t len;
};

int set_interface_attribs (int fd, int speed, int parity)
{
        struct termios tty;
        memset (&tty, 0, sizeof tty);
        if (tcgetattr (fd, &tty) != 0)
        {
                printf ("error from tcgetattr");
                return -1;
        }

        cfsetospeed (&tty, speed);
        cfsetispeed (&tty, speed);

        tty.c_cflag = (tty.c_cflag & ~CSIZE) | CS8;     // 8-bit chars
        // disable IGNBRK for mismatched speed tests; otherwise receive break
        // as \000 chars
        tty.c_iflag &= ~IGNBRK;         // disable break processing
        tty.c_lflag = 0;                // no signaling chars, no echo,
                                        // no canonical processing
        tty.c_oflag = 0;                // no remapping, no delays
        tty.c_cc[VMIN]  = 0;            // read doesn't block
        tty.c_cc[VTIME] = 5;            // 0.5 seconds read timeout

        tty.c_iflag &= ~(IXON | IXOFF | IXANY); // shut off xon/xoff ctrl

        tty.c_cflag |= (CLOCAL | CREAD);// ignore modem controls,
                                        // enable reading
        tty.c_cflag &= ~(PARENB | PARODD);      // shut off parity
        tty.c_cflag |= parity;
        tty.c_cflag &= ~CSTOPB;
        tty.c_cflag &= ~CRTSCTS;

        if (tcsetattr (fd, TCSANOW, &tty) != 0)
        {
                printf ("error from tcsetattr");
                return -1;
        }
        return 0;
}

void
set_blocking (int fd, int should_block)
{
        struct termios tty;
        memset (&tty, 0, sizeof tty);
        if (tcgetattr (fd, &tty) != 0)
        {
                printf ("error from tggetattr");
                return;
        }

        tty.c_cc[VMIN]  = should_block ? 1 : 0;
        tty.c_cc[VTIME] = 5;            // 0.5 seconds read timeout

        if (tcsetattr (fd, TCSANOW, &tty) != 0)
                printf ("error setting term attributes");
}

void init_string(struct string *s) {
  s->len = 0;
  s->ptr = malloc(s->len+1);
  if (s->ptr == NULL) {
    fprintf(stderr, "malloc() failed\n");
    exit(EXIT_FAILURE);
  }
  s->ptr[0] = '\0';
}

size_t writefunc(void *ptr, size_t size, size_t nmemb, struct string *s)
{
  size_t new_len = s->len + size*nmemb;
  s->ptr = realloc(s->ptr, new_len+1);
  if (s->ptr == NULL) {
    fprintf(stderr, "realloc() failed\n");
    exit(EXIT_FAILURE);
  }
  memcpy(s->ptr+s->len, ptr, size*nmemb);
  s->ptr[new_len] = '\0';
  s->len = new_len;

  return size*nmemb;
}

char* direccion(int state)
{
    switch (state){
    case 0:
            return "http://192.168.2.22:8080/Artik/squery?id=1&s=0&m=0";
            break;
    case 1:
            return "http://192.168.2.22:8080/Artik/squery?id=1&s=0&m=0";
            break;
    case 2:
            return "http://192.168.2.22:8080/Artik/squery?id=1&s=0&m=0";
            break;
    case 3:
            return "http://192.168.2.22:8080/Artik/squery?id=1&s=0&m=0";
            break;
    }


}

int comienzaciclo(){
	write (fd, "@START30000330\n", 15);           // send 7 character

	usleep ((15 + 25) * 100);             // sleep enough to transmit the 7 plus
                                     // receive 25:  approx 100 uS per char transmit
	strcpy(buf,"");
	int n = read (fd, buf, sizeof buf);  // read up to 100 characters if ready to read
	if(buf[1]=='S'){
			if(buf[6]=='O'){			
				printf("Recirculamos\n");
				estado=2;
				}
			else if(buf[6]=='C'){
				estado=1;
			}
		}
	
}

int main(void)
{
	fd	= open (portname, O_RDWR | O_NOCTTY | O_SYNC);
  CURL *curl;
  CURLcode res;

	
	if (fd < 0)
	{
       printf ("error  opening ");
        return 0;
	}
	
	set_interface_attribs (fd, B115200, 0);  // set speed to 115,200 bps, 8n1 (no parity)
	set_blocking (fd, 0);                // set no blocking
	

  while(1){
        sleep(3);
  curl = curl_easy_init();
  if(curl) {
    struct string s;
	
	init_string(&s);
    curl_easy_setopt(curl, CURLOPT_URL, direccion(estado));
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writefunc);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &s);
    res = curl_easy_perform(curl);
    if(s.ptr[0]=='t'){
        comienzaciclo();
    }
	
	usleep ((15 + 25) * 100);             // sleep enough to transmit the 7 plus
                                     // receive 25:  approx 100 uS per char transmit
	strcpy(buf,"");
	int n = read (fd, buf, sizeof buf);  // read up to 100 characters if ready to read
	if(buf[1]=='S'){
		comienzaciclo();
	}
	else if(buf[1]=='E'){
		if(buf[2]='N'){
			estado=1;
		}
		else if(buf[2]='R'){
			estado=3;
		}
	}
    printf("%s\nYIPIEEEEEEE!!!", s.ptr);
    free(s.ptr);
	if(buf[1])

    /* always cleanup */
    curl_easy_cleanup(curl);
  }
  }
  return 0;
}

