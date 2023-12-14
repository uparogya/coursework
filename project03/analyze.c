#include <stdio.h>
#include <stdlib.h>

int main() {
    int is_eof = 0;

    int number_of_requests = 0;
    int cd_info = 0;
    int cd_succ = 0;
    int cd_redi = 0;
    int cd_cerr = 0;
    int cd_serr = 0;

    unsigned long bytes_total = 0;
    unsigned long bytes_https = 0;
    unsigned long bytes_http  = 0;

    while (1){
        int port;
        int status;
        long bytes;
        is_eof = scanf("%d %d %lu\n",&port,&status,&bytes);

        if(is_eof == EOF) break;

        bytes_total += bytes;
        if(port == 443){
            bytes_https += bytes;
        }else{
            bytes_http += bytes;
        }

        if( (int)(status/500) == 1 ){
            cd_serr++;
        }else if( (int)(status/400) == 1 ){
            cd_cerr++;
        }else if( (int)(status/300) == 1 ){
            cd_redi++;
        }else if( (int)(status/200) == 1 ){
            cd_succ++;
        }else if( (int)(status/100) == 1 ){
            cd_info++;
        }

        number_of_requests++;
    }

    printf("Requests:\n");
    printf("   1xx  :  %d\n",cd_info);
    printf("   2xx  :  %d\n",cd_succ);
    printf("   3xx  :  %d\n",cd_redi);
    printf("   4xx  :  %d\n",cd_cerr);
    printf("   5xx  :  %d\n",cd_serr);
    printf("   Total:  %d\n",number_of_requests);
    printf("Bytes transferred:\n");
    printf("   http :  %lu\n",bytes_http);
    printf("   https:  %lu\n",bytes_https);
    printf("   Total:  %lu\n",bytes_total);

    return 0;
}
