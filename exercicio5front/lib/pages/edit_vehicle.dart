import 'package:exercicio5front/controllers/edit_vehicle.dart';
import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/pages/base_page.dart';
import 'package:exercicio5front/pages/create_vehicle.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

class EditVehiclePage extends GetView<EditVehicleController> {
  const EditVehiclePage({super.key});

  @override
  Widget build(BuildContext context) {
    return PopScope(
      onPopInvoked: (didPop) {
        controller.poped.value = true;
      },
      canPop: true,
      child: BasePage(
        content: Obx(() {
          // if (controller.poped.value) controller.loadVehicleData();
          return Visibility(
            visible: controller.loading,
            replacement: const CircularProgressIndicator(),
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                children: [
                  const SizedBox(height: 16),
                  TextField(
                    key: const Key("veiculo"),
                    controller: controller.veiculoController,
                    decoration: InputDecoration(
                      labelText: 'Veiculo',
                      errorText: controller.errors["veiculo"],
                    ),
                  ),
                  const SizedBox(height: 16),
                  DropdownSelectButton<String>(
                    key: const Key("marca"),
                    items: Brand.values.map((e) => e.name).toList(),
                    label: "Marca",
                    onChanged: (value) =>
                        controller.marcaController.text = value!,
                    selectedItem: controller.marcaController.text,
                  ),
                  const SizedBox(height: 16),
                  TextField(
                    key: const Key("descricao"),
                    controller: controller.descricaoController,
                    decoration: InputDecoration(
                      labelText: 'Descricao',
                      errorText: controller.errors["descricao"],
                    ),
                  ),
                  const SizedBox(height: 16),
                  TextField(
                    key: const Key("cor"),
                    controller: controller.corController,
                    decoration: InputDecoration(
                      labelText: 'Cor',
                      errorText: controller.errors["cor"],
                    ),
                  ),
                  const SizedBox(height: 16),
                  TextField(
                    key: const Key("ano"),
                    controller: controller.anoController,
                    keyboardType: TextInputType.number,
                    inputFormatters: [FilteringTextInputFormatter.digitsOnly],
                    decoration: InputDecoration(
                      labelText: 'Ano',
                      errorText: controller.errors["ano"],
                    ),
                  ),
                  Row(
                    children: [
                      const Text("Vendido"),
                      Switch(
                        value: controller.sold.value,
                        activeColor: Colors.red,
                        onChanged: (bool value) {
                          controller.sold.value = value;
                        },
                      ),
                    ],
                  ),
                  ElevatedButton(
                    onPressed: () {
                      controller.saveVehicle();
                    },
                    child: const Text("Salvar mudanças"),
                  ),
                ],
              ),
            ),
          );
        }),
      ),
    );
  }
}
