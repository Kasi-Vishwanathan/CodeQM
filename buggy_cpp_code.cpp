#include <iostream>
using namespace std;

class Box {
private:
    int length;
    int width;
    int height;

public:
    // Parameterized constructor
    Box(int l, int w, int h) : length(l), width(w), height(h) {}

    // Default constructor initializes to zero
    Box() : length(0), width(0), height(0) {}

    // Setter method for dimensions
    void set_dimensions(int l, int w, int h) {
        length = l;
        width = w;
        height = h;
    }

    // Getter methods
    int getLength() const { return length; }
    int getWidth() const { return width; }
    int getHeight() const { return height; }

    // Calculate volume
    int volume() const {
        return length * width * height;
    }

    // Print volume
    void print_volume() const {
        cout << "Volume: " << volume() << endl;
    }
};

// Pass by const reference to avoid copy
void show_dimensions(const Box& b) {
    cout << "Dimensions: " << b.getLength() << " x " 
         << b.getWidth() << " x " << b.getHeight() << endl;
}

int main() {
    Box b1(2, 3, 4); // Properly initialized with constructor

    // Access via getters
    cout << "Length: " << b1.getLength() << endl;
    cout << "Width: " << b1.getWidth() << endl;
    cout << "Height: " << b1.getHeight() << endl;

    b1.print_volume();
    show_dimensions(b1);

    Box b2; // Default constructor initializes to 0
    cout << "Volume of default-initialized box: " << b2.volume() << endl;

    return 0;
}