#!/usr/bin/env python

import os

PROJECT_DIR = os.path.realpath(os.path.curdir)
MAIN_DIR = os.path.join(PROJECT_DIR, "src", "main")
TEST_DIR = os.path.join(PROJECT_DIR, "src", "test")

import stat

def rmtree(top):
    for root, dirs, files in os.walk(top, topdown=False):
        for name in files:
            filename = os.path.join(root, name)
            os.chmod(filename, stat.S_IWUSR)
            os.remove(filename)
        for name in dirs:
            os.rmdir(os.path.join(root, name))
    os.rmdir(top)


if __name__ == "__main__":
    language = "java" if "{{ cookiecutter.use_kotlin }}" == "y" else "kotlin"
    rmtree(os.path.join(MAIN_DIR, language))
    rmtree(os.path.join(TEST_DIR, language))

    if "{{ cookiecutter.use_logger }}" != "y":
        rmtree(os.path.join(MAIN_DIR, "resources"))
