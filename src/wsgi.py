# file: src/wsgi.py
# date: October 29, 2021
# description: Exposes the WSGI callable as a module-level variable named ``application``.
#
# note: For more information on this file, see
#       https://docs.djangoproject.com/en/3.2/howto/deployment/wsgi/

import os

from django.core.wsgi import get_wsgi_application

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'jotes.settings')

application = get_wsgi_application()
