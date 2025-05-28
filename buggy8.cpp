#include <iostream>
using namespace std;

class Box {
public:
    int length;
    int width;
    int height;

    void set_dimensions(int l, int w, int h) {
        length = l;
        width = w;
        height = h;
    }

    int volume() {
        return length * width * height;
    }

    void print_volume() {
        cout << "Volume: " << volume() << endl;
    }
};

void show_dimensions(Box b) {
    // Bug: using default copy by value can be expensive for big objects
    cout << "Dimensions: " << b.length << " x " << b.width << " x " << b.height << endl;
}

int main() {
    Box b1;
    b1.set_dimensions(2, 3, 4);

    // Bug: direct access to private members
    cout << "Length: " << b1.length << endl;
    cout << "Width: " << b1.width << endl;
    cout << "Height: " << b1.height << endl;

    b1.print_volume();
    show_dimensions(b1);

    // Bug: forgot to initialize another box
    Box b2;
    cout << "Volume of uninitialized box: " << b2.volume() << endl;

    return 0;
}