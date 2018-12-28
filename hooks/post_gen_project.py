#!/usr/bin/env python

import os

PROJECT_DIR = os.path.realpath(os.path.curdir)
MAIN_DIR = os.path.join(PROJECT_DIR, "src", "main")
TEST_DIR = os.path.join(PROJECT_DIR, "src", "test")
KOTLIN = "kotlin"
JAVA = "java"

import stat


def active_language():
    return JAVA if "{{ cookiecutter.use_kotlin }}" != "y" else KOTLIN


def inactive_language():
    return KOTLIN if "{{ cookiecutter.use_kotlin }}" != "y" else JAVA


def rm_src_file(package, name):
    ext = ".kt" if active_language() == KOTLIN else ".java"
    path = os.path.join(
        MAIN_DIR,
        active_language(),
        "{{ cookiecutter.package_path }}",
        package,
        name + ext,
    )
    os.remove(path)


def rm_tree(top):
    for root, dirs, files in os.walk(top, topdown=False):
        for name in files:
            filename = os.path.join(root, name)
            os.chmod(filename, stat.S_IWUSR)
            os.remove(filename)
        for name in dirs:
            os.rmdir(os.path.join(root, name))
    os.rmdir(top)


if __name__ == "__main__":
    rm_tree(os.path.join(MAIN_DIR, inactive_language()))
    rm_tree(os.path.join(TEST_DIR, inactive_language()))

    # logger
    if "{{ cookiecutter.use_logger }}" != "y":
        rm_tree(os.path.join(MAIN_DIR, "resources"))
        rm_src_file("command", "LogCommand")

    # swerve
    if "{{ cookiecutter.use_thirdcoast_swerve }}" != "y":
        rm_src_file("command", "TeleOpDriveCommand")
        rm_src_file("command", "ZeroGyroCommand")
        rm_src_file("subsystem", "DriveSubsystem")

    # controls
    if "{{ cookiecutter.use_driver_controls }}" != "y":
        rm_src_file("control", "DriverControls")

    if "{{ cookiecutter.use_game_controls }}" != "y":
        rm_src_file("control", "GameControls")

    if (
        "{{ cookiecutter.use_driver_controls }}" != "y"
        and "{{ cookiecutter.use_game_controls }}" != "y"
    ):
        rm_tree(
            os.path.join(
                MAIN_DIR,
                active_language(),
                "{{ cookiecutter.package_path }}",
                "control",
            )
        )
