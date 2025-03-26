import 'dart:js_util';

import 'package:exercicio5front/controllers/base_controller.dart';
import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/model/create_vehicle.dart';
import 'package:exercicio5front/provider/vehicle_provider.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class CreateVehicleController extends BaseController {
  VehicleProvider vehicleProvider = VehicleProvider();

  final TextEditingController veiculoController = TextEditingController();
  final TextEditingController anoController = TextEditingController();
  final TextEditingController descricaoController = TextEditingController();
  final TextEditingController corController = TextEditingController();
  final TextEditingController marcaController =
      TextEditingController(text: "audi");

  Map<String, String> errors = {};

  void createVehicle() async {
    String veiculo = veiculoController.text;
    int ano = int.parse(anoController.text);
    String descricao = descricaoController.text;
    String cor = corController.text;
    String marca = marcaController.text;

    if (veiculo.isEmpty) {
      errors["veiculo"] = "Veiculo deve ter um nome";
    } else {
      delete(errors, "veiculo");
    }

    if (ano <= 1886 || ano >= DateTime.now().year) {
      errors["ano"] = "Ano deve ser maior que 1886 e menor que ano atual";
    } else {
      delete(errors, "ano");
    }

    if (descricao.isEmpty) {
      errors["descricao"] = "Veiculo deve ter uma descricao";
    } else {
      delete(errors, "descricao");
    }

    if (cor.isEmpty) {
      errors["cor"] = "Veiculo deve ter uma cor";
    } else {
      delete(errors, "cor");
    }

    if (marca.isEmpty) {
      errors["marca"] = "Veiculo deve ter uma marca";
    } else {
      delete(errors, "marca");
    }

    if (errors.isNotEmpty) {
      return;
    }
    final newVehicle = CreateVehicle(
        ano: ano,
        cor: cor,
        descricao: descricao,
        veiculo: veiculo,
        vendido: false,
        marca: Brand.values.firstWhere((element) => element.name == marca));
    await vehicleProvider.createVehicle(newVehicle);
    Get.offNamed("/home");
  }
}
