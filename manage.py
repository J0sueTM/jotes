#!/usr/bin/env python

# file: manage.py
# date: October 29, 2021
# description: Django's command-line utility for administrative tasks.

import os
import sys

# Run administrative tasks.
def main():
  os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'src.settings')

  try:
    from django.core.management import execute_from_command_line
  except ImportError as exc:
    raise ImportError(
      "Couldn't import Django. Are you sure it's installed and "
      "available on your PYTHONPATH environment variable? Did you "
      "forget to activate a virtual environment?"
    ) from exc
  execute_from_command_line(sys.argv)

if __name__ == '__main__':
    main()
