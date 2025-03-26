import 'package:exercicio5front/controllers/vehicles_by_decade.dart';
import 'package:exercicio5front/pages/base_page.dart';
import 'package:exercicio5front/pages/create_vehicle.dart';
import 'package:exercicio5front/pages/widgets/vehicles_list.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class VehiclesByDecade extends GetView<VehiclesByDecadeController> {
  Widget build(BuildContext context) {
    return BasePage(
      content: Column(
        children: [
          DropdownSelectButton<int>(
            key: Key("marca"),
            items: const [
              1880,
              1890,
              1900,
              1910,
              1920,
              1930,
              1940,
              1950,
              1960,
              1970,
              1980,
              1990,
              2000,
              2010,
              2020
            ],
            label: "Decada",
            onChanged: (value) => controller.changeDecade(value!),
            selectedItem: controller.decade,
          ),
          Obx(() => VehicleList(
                vehicles: controller.vehiclesByDecade,
              )),
          IconButton(
            onPressed: () => {Get.offNamed('/create')},
            icon: const Icon(
              Icons.add,
            ),
          )
        ],
      ),
    );
  }
}
