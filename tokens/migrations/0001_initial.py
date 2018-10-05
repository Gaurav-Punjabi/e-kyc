# Generated by Django 2.1.2 on 2018-10-05 14:48

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Token',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('token', models.CharField(max_length=255, unique=True)),
                ('user_id', models.IntegerField()),
                ('consumer', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='TokenPermissions',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('perm_val', models.IntegerField()),
                ('token', models.ForeignKey(on_delete='PROTECT', related_name='owningToken', to='tokens.Token')),
            ],
        ),
    ]
