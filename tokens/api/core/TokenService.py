from tokens.models import Token
from .serializers.TokenInfoSerializer import TokenInfo
from rest_framework.views import Response
from .serializers.TokenCRUDSerialiazer import TokenCRUD

class TokenService:
    def __init__(self, request):
        self.request = request

    def getTokenInfo(self, token)->Response:
        token = Token.objects.get(token= token)
        ser = TokenInfo(token)
        if ser.is_valid():
            return Response(status=200, data=ser.data)

        return Response(status=404)

    def getAllTokens(self)->Response:
        tokens = Token.objects.filter(consumer=self.request.data.get("consumer"))
        ser = TokenInfo(tokens, many=True)
        return Response(data=ser.data, status=200)

    def createToken(self)->Response:
        ser = TokenCRUD(data=self.request.data)
        if ser.is_valid():
            ser.save()
            return Response(status=200)
        return Response(status=402)

    def deleteToken(self)->Response:
        try:
            token = Token.objects.get(token=self.request.data.get("token"));
            token.delete()
            return Response(status=200)
        except Exception as e:
            return Response(status=500)

    def updateToken(self):
        try:
            currentToken = Token.objects.get(id=self.request.data.get("id"))
            ser = TokenCRUD(data=self.request.data, instance = currentToken)
            if ser.is_valid():
                ser.save()
                return Response(status=200)
        except Exception as e:
            return Response(status=400)


