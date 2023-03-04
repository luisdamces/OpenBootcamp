# Generated by Django 4.1.3 on 2022-11-22 04:17

from django.db import migrations, models
import django.db.models.deletion
import django_countries.fields


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Poblacion",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("pais", django_countries.fields.CountryField(max_length=2)),
            ],
        ),
        migrations.CreateModel(
            name="Salario",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("bruto_anual", models.IntegerField()),
                ("paga_extra", models.BooleanField(default=True)),
            ],
        ),
        migrations.CreateModel(
            name="Puesto",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("cargo", models.CharField(default="empleado", max_length=50)),
                ("descripcion", models.TextField(blank=True, max_length=500)),
                (
                    "salario",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="empleados.salario",
                    ),
                ),
            ],
        ),
        migrations.CreateModel(
            name="Fabrica",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("nombre", models.CharField(default="Multinacional", max_length=100)),
                ("direccion", models.CharField(max_length=100)),
                ("cp", models.CharField(default="CPA2005RZA", max_length=10)),
                (
                    "poblacion",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="empleados.poblacion",
                    ),
                ),
            ],
        ),
        migrations.CreateModel(
            name="Empleado",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("nombres", models.CharField(default="Juan Pablo", max_length=100)),
                ("apellido", models.CharField(default="Camussi", max_length=100)),
                ("dni", models.IntegerField()),
                ("email", models.EmailField(max_length=40)),
                ("direccion", models.CharField(max_length=100)),
                (
                    "fabrica",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="empleados.fabrica",
                    ),
                ),
                (
                    "puesto",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="empleados.puesto",
                    ),
                ),
            ],
        ),
    ]