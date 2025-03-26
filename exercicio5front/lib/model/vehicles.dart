import 'package:exercicio5front/model/brand.dart';

class Vehicle {
  final int id;
  final String veiculo;
  final Brand marca;
  final int ano;
  final String descricao;
  final String cor;
  final bool vendido;
  final DateTime created;
  final DateTime updated;

  Vehicle({
    required this.id,
    required this.veiculo,
    required this.marca,
    required this.ano,
    required this.descricao,
    required this.cor,
    required this.vendido,
    required this.created,
    required this.updated,
  });

  factory Vehicle.fromJson(Map<String, dynamic> json) {
    return Vehicle(
      id: json['id'],
      veiculo: json['veiculo'],
      marca: BrandExtension.fromJson(json['marca']),
      ano: json['ano'],
      descricao: json['descricao'],
      cor: json['cor'],
      vendido: json['vendido'],
      created: DateTime.parse(json['created']),
      updated: DateTime.parse(json['updated']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'veiculo': veiculo,
      'marca': marca.toJson(),
      'ano': ano,
      'descricao': descricao,
      'cor': cor,
      'vendido': vendido,
    };
  }
}
