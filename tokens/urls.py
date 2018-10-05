from django.conf.urls import url
from .api.TokenView import TokenCrud, TokenRead

urlpatterns = [
    url(r"^(P<type>)/P<token>/", TokenRead.as_view()),
    url(r'$', TokenCrud.as_view())
]