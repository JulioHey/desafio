import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/model/create_vehicle.dart';
import 'package:exercicio5front/provider/vehicle_provider.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class EditVehicleController extends GetxController {
  VehicleProvider vehicleProvider = VehicleProvider();

  final _isLoaded = false.obs;
  int vehicleId = 0;

  final TextEditingController veiculoController = TextEditingController();
  final TextEditingController anoController = TextEditingController();
  final TextEditingController descricaoController = TextEditingController();
  final TextEditingController corController = TextEditingController();
  final TextEditingController marcaController =
      TextEditingController(text: "audi");
  final Rx<bool> sold = false.obs;

  Map<String, String> errors = {};
  final Rx<bool> poped = true.obs;

  bool get loading => _isLoaded.value;

  @override
  void onInit() {
    loadVehicleData();
    super.onInit();
  }

  void loadVehicleData() async {
    final Map<String, String?> args = Get.parameters;

    vehicleId = int.parse(args["id"] ?? "");

    final vehicle = await vehicleProvider.getVehicle(vehicleId);
    veiculoController.text = vehicle.veiculo;
    anoController.text = vehicle.ano.toString();
    descricaoController.text = vehicle.descricao;
    corController.text = vehicle.cor;
    marcaController.text = vehicle.marca.name;
    sold.value = vehicle.vendido;
    _isLoaded.value = true;
  }

  void saveVehicle() {
    String veiculo = veiculoController.text;
    int ano = int.parse(anoController.text);
    String descricao = descricaoController.text;
    String cor = corController.text;
    String marca = marcaController.text;

    if (veiculo.isEmpty) {
      errors["veiculo"] = "Veiculo deve ter um nome";
    } else {
      errors["veiculo"] = "";
    }

    if (ano <= 1886 || ano >= DateTime.now().year) {
      errors["ano"] = "Ano deve ser maior que 1886 e menor que ano atual";
    } else {
      errors["ano"] = "";
    }

    if (descricao.isEmpty) {
      errors["descricao"] = "Veiculo deve ter uma descricao";
    } else {
      errors["descricao"] = "";
    }

    if (cor.isEmpty) {
      errors["cor"] = "Veiculo deve ter uma cor";
    } else {
      errors["cor"] = "";
    }

    if (marca.isEmpty) {
      errors["marca"] = "Veiculo deve ter uma marca";
    } else {
      errors["marca"] = "";
    }
    final newVehicle = CreateVehicle(
        ano: ano,
        cor: cor,
        descricao: descricao,
        veiculo: veiculo,
        vendido: sold.value,
        marca: Brand.values.firstWhere((element) => element.name == marca));
    vehicleProvider.updateVehicle(vehicleId, newVehicle);
  }
}
