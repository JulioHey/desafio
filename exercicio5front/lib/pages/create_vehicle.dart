import 'package:exercicio5front/controllers/create_vehicle.dart';
import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/pages/base_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

class DropdownSelectButton<T> extends StatelessWidget {
  final List<T> items;
  final T? selectedItem;
  final String label;
  final ValueChanged<T?> onChanged;

  const DropdownSelectButton({
    super.key,
    required this.items,
    required this.selectedItem,
    required this.label,
    required this.onChanged,
  });

  @override
  Widget build(BuildContext context) {
    return DropdownButtonFormField<T>(
      value: selectedItem,
      decoration:
          InputDecoration(labelText: label, border: const OutlineInputBorder()),
      items: items.map((T item) {
        return DropdownMenuItem<T>(
          value: item,
          child: Text(item.toString()),
        );
      }).toList(),
      onChanged: onChanged,
    );
  }
}

class CreateVehiclePage extends GetView<CreateVehicleController> {
  const CreateVehiclePage({super.key});

  @override
  Widget build(BuildContext context) {
    return BasePage(
        content: Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(children: [
        const SizedBox(
          height: 16,
        ),
        TextField(
          key: const Key("veiculo"),
          controller: controller.veiculoController,
          decoration: InputDecoration(
            labelText: 'Veiculo',
            errorText: controller.errors["veiculo"],
          ),
        ),
        const SizedBox(
          height: 16,
        ),
        DropdownSelectButton<String>(
          key: const Key("marca"),
          items: Brand.values.map((e) => e.name).toList(),
          label: "Marca",
          onChanged: (value) => controller.marcaController.text = value!,
          selectedItem: controller.marcaController.text,
        ),
        const SizedBox(
          height: 16,
        ),
        TextField(
          key: const Key("descricao"),
          controller: controller.descricaoController,
          decoration: InputDecoration(
            labelText: 'Descricao',
            errorText: controller.errors["descricao"],
          ),
        ),
        const SizedBox(
          height: 16,
        ),
        TextField(
          key: const Key("cor"),
          controller: controller.corController,
          decoration: InputDecoration(
            labelText: 'Cor',
            errorText: controller.errors["cor"],
          ),
        ),
        const SizedBox(
          height: 16,
        ),
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
        ElevatedButton(
          onPressed: () {
            controller.createVehicle();
          },
          child: const Text("Criar novo veículo"),
        )
      ]),
    ));
  }
}
