from rest_framework.views import Response, APIView
from .core.TokenService import TokenService
from tokenstore.settings import SECRET_KEY

def validate_request(request):
    return request.META.get("token") == SECRET_KEY

invalidRequest = {"error":"no/invalid auth token"}

class TokenRead(APIView):
    def get(self, request,type, token):

        if not validate_request(request):
            return Response(status=402, data=invalidRequest)

        if type == "token":
            return TokenService(request).getTokenInfo(token)
        elif token == "all":
            return TokenService(request).getAllTokens()

class TokenCrud(APIView):
    def post(self, request):
        if not validate_request(request):
            return Response(status=402, data=invalidRequest)

        return TokenService(request).createToken()

    def put(self, request):

        if not validate_request(request):
            return Response(status=402, data=invalidRequest)

        return TokenService(request).updateToken()

    def delete(self, request):
        if not validate_request(request):
            return Response(status=402, data=invalidRequest)

        return TokenService(request).deleteToken()
