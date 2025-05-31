#include <iostream>
using namespace std;

int main() {
    int a = 5;
    int b = 0;
    cout << "Division: " << a / b << endl; // Bug: division by zero
    return 0;
}