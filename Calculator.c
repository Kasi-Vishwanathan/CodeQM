#include <stdio.h>
#include <string.h>

int main() {
int a,b;
char op;
char buf[5];
printf("Enter first number: ");
scanf("%d",&a);

printf("Enter operator (+,-,*,/): ");
scanf(" %c", &op);

printf("Enter second number: ");
scanf("%d",&b);

if(op == '+') {
    printf("Result: %d\n", a+b);
} else if(op == '-') {
    printf("Result: %d\n", a-b);
} else if(op == '*') {
    printf("Result: %d\n", a*b);
} else if(op == '/') {
    printf("Result: %d\n", a/b);  // no zero check, runtime error possible
} else {
    printf("Invalid operator\n");
}

printf("Enter some text: ");
gets(buf);  // gets is unsafe and deprecated, risk of buffer overflow

printf("You entered: %s\n", buf);

return 0;
}
