import 'package:exercicio5front/controllers/vehicles_by_brand.dart';
import 'package:exercicio5front/model/brand.dart';
import 'package:exercicio5front/pages/base_page.dart';
import 'package:exercicio5front/pages/create_vehicle.dart';
import 'package:exercicio5front/pages/widgets/vehicles_list.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class VehiclesByBrandPage extends GetView<VehicleByBrandController> {
  @override
  Widget build(BuildContext context) {
    return BasePage(
      content: Column(
        children: [
          DropdownSelectButton<String>(
            key: Key("marca"),
            items: Brand.values.map((e) => e.name).toList(),
            label: "Marca",
            onChanged: (value) => controller.changeBrand(
                Brand.values.firstWhere((element) => element.name == value!)),
            selectedItem: controller.brand.name,
          ),
          Obx(() => VehicleList(
                vehicles: controller.vehiclesByBrand,
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
