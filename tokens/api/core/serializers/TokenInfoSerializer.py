from rest_framework import serializers
from tokens.models import Token
class TokenInfo(serializers.ModelSerializer):
    class Meta:
        model = Token
        fields = ("id", "token", "token_info", "consumer")
