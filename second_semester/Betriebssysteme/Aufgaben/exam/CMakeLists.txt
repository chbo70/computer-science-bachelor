cmake_minimum_required(VERSION 3.22)
project(exam C)

set(CMAKE_C_STANDARD 11)

add_executable(exam
        rpc_server.c)
