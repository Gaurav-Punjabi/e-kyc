from django.core.management import BaseCommand
from tokens.models import Token,TokenPermissions
from hashlib import sha256
from random import randint

class Command(BaseCommand):

	def handle(self, *args, **options):
		self.handle_noargs()

	def handle_noargs(self ):
		for i in range(1, 5):
			val = i+10
			t = val + i
			l = str();
			l = l+str(i)
			l = l+str(i+10)
			sha = sha256(l.encode("utf-8")).hexdigest()

			currentToken = Token.objects.create(user_id=i, consumer=val, token=sha)
			for j in range(0, 3):
				TokenPermissions.objects.create(token=currentToken, perm_val=randint(0,4)).save()
			currentToken.save()

		self.stdout.write("done writing");