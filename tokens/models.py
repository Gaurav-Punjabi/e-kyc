from django.db import models

# Create your models here.

class Token(models.Model):
    id = models.AutoField(primary_key=True)
    token = models.CharField(unique=True, max_length=255)
    # the user who owns this token
    user_id = models.IntegerField()

    # the user who requested this token
    consumer = models.IntegerField()

    def token_info(self):
        return TokenPermissions.objects.filter()

class TokenPermissions(models.Model):
    """
    This model indicates the permissions that this token has
        0 - read photo
        1 - identity
        2 - address
        3 - proof of identity
        4 - proof of address
    """
    id = models.AutoField(primary_key=True)
    token = models.ForeignKey(Token, related_name="owningToken", on_delete="PROTECT")
    perm_val = models.IntegerField()




