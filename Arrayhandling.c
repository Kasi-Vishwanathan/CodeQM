#include <stdio.h>

int main() {
int i, n;
int arr[5];
printf("Enter number of elements (max 5): ");
scanf("%d", &n);

for(i=0; i<=n; i++) {  // BUG: i<=n instead of i<n, possible out of bounds
    printf("Enter element %d: ", i);
    scanf("%d", &arr[i]);
}

printf("You entered:\n");
for(i=0; i<n; i++) {
    printf("%d ", arr[i]);
}
printf("\n");

return 0;
}
