#include "SkUserConfig.h"
#include <SkBitmapDevice.h>
#include <SkTypeface.h>
#include <sk_tool_utils.h>
#include "SkBitmap.h"
#include "SkDevice.h"
#include "SkPaint.h"
#include "SkRect.h"
#include "SkPaint.h"

#include "render.h"


extern "C" {

void skrender(char *font, char *str, int color, int fontSize, bool antiAliasing, int fontStyle, int x, int y, int *arr,
              int width, int height)
{
    SkBitmap bitmap;
    bitmap.setInfo(SkImageInfo::MakeN32(width, height, SkAlphaType::kOpaque_SkAlphaType));
    bitmap.setPixels(arr);
    SkCanvas canvas(bitmap);
    SkPaint paint;
    paint.setARGB((color&0xff000000) >> 24, (color&0xff0000) >> 16, (color&0xff00) >> 8, (color&0xff));
    paint.setTextSize(fontSize);
    paint.setAntiAlias(antiAliasing);
    paint.setLCDRenderText(true);
    paint.setSubpixelText(true);
    paint.setLinearText(true);
    paint.setDevKernText(true);
    //paint.setHinting(SkPaint::Hinting::kSlight_Hinting);
    //paint.setHinting(SkPaint::Hinting::kFull_Hinting);
    //paint.setHinting(SkPaint::Hinting::kFull_Hinting);

    //sk_tool_utils::set_portable_typeface(&paint, font, (SkTypeface::Style) fontStyle);
    SkTypeface *typeface = SkTypeface::CreateFromName(font, (SkTypeface::Style) fontStyle);
    paint.setTypeface(typeface);

    canvas.drawText(str, strlen(str), x, y, paint);

        /*
    SkCanvas* canvas = SkCanvas::NewRasterDirectN32(width, height, (SkPMColor *) arr, width*height);
    SkPaint paint;
    SkRect r;

    paint.setARGB(255, 255, 255, 255);
    r.set(10, 10, 20, 20);
    canvas->drawRect(r, paint);
    canvas->flush();*/

/*
    paint.setARGB(255, 255, 0, 0);
    r.offset(5, 5);
    canvas.drawRect(r, paint);

    paint.setARGB(255, 0, 0, 255);
    r.offset(5, 5);
    canvas.drawRect(r, paint);
    canvas.flush();
    */
}

}
