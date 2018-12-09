#!/usr/bin/env python

import os
import shutil

PROJECT_DIR = os.path.realpath(os.path.curdir)
MAIN_DIR = os.path.join(PROJECT_DIR, 'src', 'main')
TEST_DIR = os.path.join(PROJECT_DIR, 'src', 'test')

if __name__ == "__main__":
    language = 'java' if '{{ cookiecutter.use_kotlin }}' == 'y' else 'kotlin'
    shutil.rmtree(os.path.join(MAIN_DIR, language))
    shutil.rmtree(os.path.join(TEST_DIR, language))