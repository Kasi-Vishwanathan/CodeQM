#include <iostream>
#include <cstring>
using namespace std;

int main() {
    char name[10];
    cout << "Enter your name: ";
    cin >> name;  // no length check, buffer overflow risk

    char greeting[10];
    strcpy(greeting, "Hello ");
    strcat(greeting, name);  // possible buffer overflow

    cout << greeting << endl;

    return 0;
}
