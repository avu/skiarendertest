#ifndef __render__
#define __render__

#include <iostream>
extern "C" {
void skrender(char *font, char *str, int color, int fontSize, bool antiAliasing, int fontStyle, int x, int y, int *arr,
              int width, int height);
}
#endif /* defined(__render__) */
