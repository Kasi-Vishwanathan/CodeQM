#include <iostream>
#include <cstring> // Using C-style string handling

class Student {
public:
    char name[20]; // Outdated: fixed-size char array
    int age;

    void print() {
        std::cout << "Name: " << name << std::endl;
        std::cout << "Age: " << age << std::endl;
    }
};

int main() {
    Student s;
    std::cout << "Enter name: ";
    std::cin >> s.name;

    std::cout << "Enter age: ";
    std::cin >> s.age;

    if (s.age < 0)
        std::cout << "Invalid age!" << std::endl;
    else
        s.print();

    int nums[5];
    nums[5] = 10; // Logical error: out-of-bounds access

    return; // Missing return value in non-void function
}
pp