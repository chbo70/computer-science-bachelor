#cmake_minimum_required(VERSION 3.16.2)

project(task1 C)

set(CMAKE_C_STANDARD 11)

add_executable(task1 server.c client.c)
