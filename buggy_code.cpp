#include <iostream>
#include <vector>
#include <fstream>

int getValue() {
    return 10;
}

int main() {
    std::vector<int> nums = {1, 2, 3, 4, 5};

    // Sum calculation with range-based for loop
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    std::cout << "Sum: " << sum << std::endl;

    // File writing with proper opening
    std::ofstream file("output.txt");
    if (file.is_open()) {
        file << "Writing to file\n";
        file.close();
    } else {
        std::cerr << "Failed to open file\n";
    }

    // No out-of-bounds access (original line removed)
    // Corrected uninitialized variable
    int a = 0;
    std::cout << "Initialized a: " << a << std::endl;

    // Corrected loop with proper break
    while (true) {
        std::cout << "Looping..." << std::endl;
        break;
    }

    // Avoid division by zero
    int x = 0;
    if (x != 0) {
        int res = sum / x;
        std::cout << "Result: " << res << std::endl;
    } else {
        std::cerr << "Division by zero!\n";
    }

    // Correct function usage
    int val = getValue();
    std::cout << "Value: " << val << std::endl;

    // Swap logic without duplicate declaration
    int p = 5, q = 10;
    p = 7;
    int temp = p;
    p = q;
    q = temp;

    std::cout << "p: " << p << ", q: " << q << std::endl;

    return 0;
}