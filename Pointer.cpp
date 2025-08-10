#include <iostream>
using namespace std;

int main() {
    int *ptr = nullptr;
    cout << "Dereferencing pointer..." << endl;
    cout << *ptr << endl;  // Runtime error - segmentation fault

    return 0;
}
