cmake_minimum_required(VERSION 3.2)
project(skiarender)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${PROJECT_SOURCE_DIR}/../../bin/osx")
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${PROJECT_SOURCE_DIR}/../../bin/osx")

include_directories(SYSTEM include/skia/core)
include_directories(SYSTEM include/skia/config)
include_directories(SYSTEM include/skia)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES render.cpp)
add_library(skrender SHARED ${SOURCE_FILES} )

if(APPLE)
   include_directories ( /System/Library/Frameworks )
   find_library(COCOA_LIBRARY Cocoa)
   mark_as_advanced (COCOA_LIBRARY)
   set(EXTRA_LIBS ${COCOA_LIBRARY})
endif(APPLE)

add_library(libskia_core STATIC IMPORTED)
set_property(TARGET libskia_core PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_core.a)

add_library(libskia_ports STATIC IMPORTED)
set_property(TARGET libskia_ports PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_ports.a)

add_library(libskia_sfnt STATIC IMPORTED)
set_property(TARGET libskia_sfnt PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_sfnt.a)

add_library(libskia_skgpu STATIC IMPORTED)
set_property(TARGET libskia_skgpu PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_skgpu.a)

add_library(libskia_utils STATIC IMPORTED)
set_property(TARGET libskia_utils PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_utils.a)

add_library(libskia_opts STATIC IMPORTED)
set_property(TARGET libskia_opts PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_opts.a)

add_library(libskia_opts_ssse3 STATIC IMPORTED)
set_property(TARGET libskia_opts_ssse3 PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_opts_ssse3.a)

add_library(libskia_opts_sse41 STATIC IMPORTED)
set_property(TARGET libskia_opts_sse41 PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_opts_sse41.a)

add_library(libskia_effects STATIC IMPORTED)
set_property(TARGET libskia_effects PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_effects.a)

add_library(libskia_images STATIC IMPORTED)
set_property(TARGET libskia_images PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libskia_images.a)

add_library(libSkKTX STATIC IMPORTED)
set_property(TARGET libSkKTX PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libSkKTX.a)

add_library(libetc1 STATIC IMPORTED)
set_property(TARGET libetc1 PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libetc1.a)
add_library(libsk_tool_utils STATIC IMPORTED)
set_property(TARGET libsk_tool_utils PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libsk_tool_utils.a)
add_library(libflags_common STATIC IMPORTED)
set_property(TARGET libflags_common PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libflags_common.a)

add_library(libresources STATIC IMPORTED)
set_property(TARGET libresources PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libresources.a)

add_library(libflags STATIC IMPORTED)
set_property(TARGET libflags PROPERTY
             IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/lib/osx/libflags.a)

set(EXTRA_LIBS ${EXTRA_LIBS} libskia_core libskia_ports libskia_sfnt libskia_skgpu
    libskia_utils libskia_opts libskia_opts_ssse3 libskia_opts_sse41 libskia_effects libskia_images libSkKTX libetc1
    libsk_tool_utils libflags_common libresources libflags)
target_link_libraries(skrender ${EXTRA_LIBS})


