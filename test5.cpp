#include <iostream>
#include <vector>
#include <stdexcept>
#include <numeric>

using std::cout;
using std::endl;

// Use constexpr for compile-time computation
constexpr int add(int a, int b) noexcept {
    return a + b;
}

// Use auto&& for generic range-based loops
void printArray(const std::vector<int>& arr) {
    for (auto&& num : arr) {
        cout << num << " ";
    }
    cout << '\n'; // Use '\n' instead of endl for performance
}

// Add error handling for negative input
int factorial(int n) {
    if (n < 0) {
        throw std::invalid_argument("Factorial of negative number is undefined");
    }
    if (n == 0) {
        return 1;
    }
    return n * factorial(n - 1);
}

// Use std::iota for initialization
std::vector<int> createArray(int size) {
    std::vector<int> arr(size);
    std::iota(arr.begin(), arr.end(), 1); // Fill with 1, 2, 3,...
    for (auto&& num : arr) {
        num *= 10; // Multiply each element by 10
    }
    return arr;
}

int main() {
    // Test add function
    cout << "Sum: " << add(5, 10) << '\n';

    // Create and print array
    auto myArray = createArray(5);
    printArray(myArray);

    // Test factorial with error handling
    try {
        cout << "Factorial of 5: " << factorial(5) << '\n';
    } catch (const std::exception& e) {
        cout << "Error: " << e.what() << '\n';
    }

    return 0;
}
#include <iostream>
#include <vector>
#include <fstream>
#include <utility>  // For std::swap

constexpr int getValue() {
    return 10;
}

int main() {
    std::vector<int> nums = {1, 2, 3, 4, 5};

    // Modern range-based for loop with const reference
    int sum = 0;
    for (const auto& num : nums) {
        sum += num;
    }
    std::cout << "Sum: " << sum << '\n';

    // File handling using RAII (no explicit close needed)
    {
        std::ofstream file("output.txt");
        if (file.is_open()) {
            file << "Writing to file\n";
        } else {
            std::cerr << "Failed to open file\n";
        }
    }  // File closed automatically here

    // Proper initialization
    int a = 0;
    std::cout << "Initialized a: " << a << '\n';

    // Simplified single-iteration loop
    for (int i = 0; i < 1; ++i) {
        std::cout << "Looping...\n";
    }

    // Type-safe division check
    int x = 0;
    if (x != 0) {
        int res = sum / x;
        std::cout << "Result: " << res << '\n';
    } else {
        std::cerr << "Division by zero!\n";
    }

    // Constexpr function usage
    const int val = getValue();
    std::cout << "Value: " << val << '\n';

    // Modern swap using standard library
    int p = 5, q = 10;
    p = 7;
    std::swap(p, q);
    std::cout << "p: " << p << ", q: " << q << '\n';

    return 0;
}
#include <iostream>
#include <vector>
#include <fstream>
#include <utility>  // For std::swap

int getValue() {
    return 10;
}

int main() {
    std::vector<int> nums = {1, 2, 3, 4, 5};

    // Use auto for sum initialization
    auto sum = 0;
    for (const auto& num : nums) {  // Add const and reference for clarity
        sum += num;
    }
    std::cout << "Sum: " << sum << '\n';  // Use '\n' instead of endl

    // Remove explicit close (RAII handles it)
    std::ofstream file("output.txt");
    if (file.is_open()) {
        file << "Writing to file\n";
    } else {
        std::cerr << "Failed to open file\n";
    }

    int a = 0;
    std::cout << "Initialized a: " << a << '\n';

    // Simplified loop with comment explaining possible use-case
    std::cout << "Looping once...\n";  // Single execution replaces loop with break

    // Avoid magic numbers for clarity
    int denominator = 0;
    if (denominator != 0) {
        int res = sum / denominator;
        std::cout << "Result: " << res << '\n';
    } else {
        std::cerr << "Division by zero!\n";
    }

    int val = getValue();
    std::cout << "Value: " << val << '\n';

    // Use std::swap for idiomatic swapping
    int p = 5, q = 10;
    p = 7;  // Modified p before swap
    std::swap(p, q);  // Corrected to standard method
    std::cout << "p: " << p << ", q: " << q << '\n';

    return 0;
}
#include <vector>
#include <zlib.h>
#include <stdexcept>

int compress(std::vector<Bytef>& dest, const Bytef* source, uLong sourceLen) {
    z_stream stream{};
    stream.next_in = const_cast<Bytef*>(source);
    stream.avail_in = sourceLen;
    stream.zalloc = Z_NULL;
    stream.zfree = Z_NULL;
    stream.opaque = Z_NULL;

    const int initResult = deflateInit2(&stream, Z_DEFAULT_COMPRESSION, 
                                      Z_DEFLATED, 15, 8, Z_DEFAULT_STRATEGY);
    if (initResult != Z_OK) {
        return initResult;
    }

    const auto cleanup = [&] { deflateEnd(&stream); };
    try {
        dest.resize(1024);  // Initial buffer size
        int deflateResult;
        
        do {
            stream.next_out = dest.data() + stream.total_out;
            stream.avail_out = static_cast<uInt>(dest.size() - stream.total_out);

            deflateResult = deflate(&stream, Z_FINISH);
            
            if (deflateResult == Z_OK) {
                // Double buffer size when full
                dest.resize(dest.size() * 2);
            } else if (deflateResult != Z_STREAM_END) {
                cleanup();
                return deflateResult;
            }
        } while (deflateResult != Z_STREAM_END);

        dest.resize(stream.total_out);
        cleanup();
        return Z_OK;
    } catch (...) {
        cleanup();
        throw;
    }
}