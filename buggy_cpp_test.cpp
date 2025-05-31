#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

int getValue() {
    return 10;
}

int main() {
    vector<int> nums = {1, 2, 3, 4, 5};

    // Corrected sum calculation
    int sum = 0;
    for (size_t i = 0; i < nums.size(); ++i) {
        sum += nums[i];
    }
    cout << "Sum: " << sum << endl;

    // Proper file handling
    ofstream file("output.txt");
    if (file.is_open()) {
        file << "Writing to file\n";
        file.close();
    } else {
        cerr << "Failed to open file\n";
    }

    // Bounds-checked access
    if (nums.size() > 10) {
        cout << "nums[10]: " << nums[10] << endl;
    } else {
        cout << "nums[10] is out of bounds\n";
    }

    // Initialized variable
    int a = 0;
    cout << "Initialized a: " << a << endl;

    // Fixed loop with correct break
    while (true) {
        cout << "Looping..." << endl;
        break;
    }

    // Safe division
    int x = 1;
    int res = sum / x;
    cout << "Result: " << res << endl;

    // Correct function call
    int val = getValue();
    cout << "Value: " << val << endl;

    // Proper variable assignment
    int p = 5, q = 10;
    p = 7;
    int temp = p;
    p = q;
    q = temp;
    cout << "p: " << p << ", q: " << q << endl;

    return 0;
}