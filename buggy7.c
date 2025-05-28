/* buggy7.c */
#include <stdint.h>
#include <stddef.h>

uint32_t adler32(uint32_t adler, const uint8_t *buf, size_t len) {
    uint32_t s1 = adler & 0xFFFF;
    uint32_t s2 = (adler >> 16) & 0xFFFF;
    size_t n;

    // Handle cases with no data to process
    if (len == 0) {
        return (s2 << 16) | s1; // Return current adler value
    }

    // If buf is NULL but len is not zero, return initial value (1)
    if (buf == NULL) {
        return 1;
    }

    // Process each byte in the buffer
    for (n = 0; n < len; n++) {
        s1 = (s1 + buf[n]) % 65521;
        s2 = (s2 + s1) % 65521;
    }

    return (s2 << 16) | s1;
}