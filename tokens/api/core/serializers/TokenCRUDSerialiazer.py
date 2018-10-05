from django.core.exceptions import SuspiciousOperation
from rest_framework import serializers
from tokens.models import Token, TokenPermissions
import time
import hashlib
class TokenCRUD(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    consumer = serializers.IntegerField(min_value=1)
    user_id = serializers.IntegerField(min_value=1)
    permissions = serializers.ListField(child=serializers.IntegerField(max_value=4, min_value=0))

    def update(self, instance, validated_data)->Token:
        id = validated_data.get("id")
        current_milli_time = lambda: int(round(time.time() * 1000))
        user_id = validated_data.get("user_id")
        perms = validated_data.get("permissions")
        consumer = validated_data.get("consumer")

        try:
            currentToken = Token.objects.get(id=id)
            if currentToken.consumer!= consumer or currentToken.user_id!= user_id:
                raise SuspiciousOperation("Cant update this token")
        except Exception as e:
            raise SuspiciousOperation("Cant update this token")

        currentToken.token_info().delete()
        currentToken.token =hashlib.sha256(str(user_id+current_milli_time+consumer+hash(perms))).hexdigest()
        self.createTokenPerms(currentToken, perms)
        currentToken.save()
        return currentToken

    def create(self, validated_data)->Token:
        # todo place rest call to check the existence of these ids
        # honestly if these requests come from the User service this call does not have to be placed
        current_milli_time = lambda: int(round(time.time() * 1000))

        perms = validated_data.get("permissions")
        consumer = validated_data.get("consumer")
        user_id = validated_data.get("user_id")

        currentToken = Token.objects.create(user_id=user_id, consumer=consumer, token=hashlib.sha256(str(user_id+current_milli_time+consumer+hash(perms))).hexdigest())

        self.createTokenPerms(currentToken, perms)
        currentToken.save()
        return currentToken

    @staticmethod
    def createTokenPerms(currentToken, perms):
        for perm in perms:
            TokenPermissions.objects.create(token=currentToken, perm_val=perm).save()







