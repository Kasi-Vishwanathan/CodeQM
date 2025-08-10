#include <stdio.h>
#include <string.h>

int main() {
char *str1 = "hello";
char str2[5];
strcpy(str2, str1); // copying 5 chars into 5-char buffer (no space for '\0')
printf("Copied string: %s\n", str2);

char *ptr = NULL;
printf("Character: %c\n", *ptr);  // Null pointer dereference

return 0;
}
