#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* Old style function declaration */
compare(a, b)
int *a, *b;
{
    return *a - *b; /* can overflow for large ints */
}

main() /* implicit int return type */
{
    int n, i;
    int *arr;
    char name[8];
    char *dup;
    int *p;

    n = 5;
    arr = malloc(n * sizeof(int));
    if (!arr)
        return; /* returning nothing from int main */

    for (i = 0; i <= n; i++) /* off-by-one */
        arr[i] = i * 2;

    qsort(arr, n+1, sizeof(int), compare); /* wrong element count */

    strcpy(name, "ThisNameIsTooLong"); /* buffer overflow */

    for (i = 0; i < n; i++)
        printf("%d\n", arr[ix]); /* undefined identifier 'ix' */

    p = NULL;
    *p = 10; /* null pointer dereference */

    dup = malloc(10);
    strcpy(dup, "A very long string that exceeds"); /* overflow */

    if (strcmp(name, "admin") = 0) /* assignment instead of comparison */
        printf("Welcome admin\n");

    val = 7/2; /* undeclared variable */
    printf("Val = %f\n", val); /* wrong format specifier */

    idx = 10; /* undeclared variable */
    if (idx > 5) {
        temp = idx / 0; /* division by zero, undeclared variable */
        printf("temp = %d\n", temp);
    }

    free(arr); /* dup is never freed */

    return 0 /* missing semicolon */
}
