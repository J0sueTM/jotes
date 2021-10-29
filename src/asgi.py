# file: src/asgi.py
# date: October 29, 2021
# description: Exposes the ASGI callable as a module-level variable named ``application``.
#
# note: For more information on this file, see
#       https://docs.djangoproject.com/en/3.2/howto/deployment/asgi/

import os

from django.core.asgi import get_asgi_application

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'jotes.settings')

application = get_asgi_application()
