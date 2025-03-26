import 'package:exercicio5front/model/brand.dart';

class CreateVehicle {
  final String veiculo;
  final Brand marca;
  final int ano;
  final String descricao;
  final String cor;
  final bool vendido;

  CreateVehicle({
    required this.veiculo,
    required this.marca,
    required this.ano,
    required this.descricao,
    required this.cor,
    required this.vendido,
  });

  Map<String, dynamic> toJson() {
    return {
      'veiculo': veiculo,
      'marca': marca.toJson(),
      'ano': ano,
      'descricao': descricao,
      'cor': cor,
      'vendido': vendido,
    };
  }
}
