#include <stdio.h>

main() {   // implicit int main(), old style
char name[10];
printf("Enter name: ");
gets(name);  // unsafe gets()
printf("Name is: %s\n", name);

return 0;
}
